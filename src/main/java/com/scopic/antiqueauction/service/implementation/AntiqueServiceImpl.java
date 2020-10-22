package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.converter.AntiqueConverter;
import com.scopic.antiqueauction.domain.converter.AntiqueListingConverter;
import com.scopic.antiqueauction.domain.converter.AntiqueResponseConverter;
import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.AntiqueImage;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.enums.Status;
import com.scopic.antiqueauction.domain.request.AntiqueRequest;
import com.scopic.antiqueauction.domain.request.BidRequest;
import com.scopic.antiqueauction.domain.response.AntiqueListingResponse;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;
import com.scopic.antiqueauction.exceptions.InvalidBidException;
import com.scopic.antiqueauction.repository.AntiqueRepository;
import com.scopic.antiqueauction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AntiqueServiceImpl implements AntiqueService {

    private final AntiqueRepository antiqueRepository;
    private final PastBidService pastBidService;
    private final AntiqueImageService antiqueImageService;
    private final FileStorageService fileStorageService;
    private final UserService userService;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final TaskService taskService;

    @Autowired
    public AntiqueServiceImpl(AntiqueRepository antiqueRepository, PastBidService pastBidService, AntiqueImageService antiqueImageService, FileStorageService fileStorageService, UserService userService, ThreadPoolTaskScheduler taskScheduler, TaskService taskService) {
        this.antiqueRepository = antiqueRepository;
        this.pastBidService = pastBidService;
        this.antiqueImageService = antiqueImageService;
        this.fileStorageService = fileStorageService;
        this.userService = userService;
        this.taskScheduler = taskScheduler;
        this.taskService = taskService;
    }

    @Override
    public Page<AntiqueListingResponse> getAllAntiques(int pageNo, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.by(direction, "price"));
        return antiqueRepository.findAll(pageable).map(antique ->
                {
                    List<String> imagePaths = antiqueImageService.getAntiqueImages(antique)
                            .stream()
                            .map(AntiqueImage::getPath)
                            .collect(Collectors.toList());
                    return AntiqueListingConverter.convert(antique, imagePaths);
                }
        );
    }

    @Override
    public Page<AntiqueListingResponse> getAllAntiquesLike(int pageNo, Sort.Direction direction, String str) {
        Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.by(direction, "price"));
        return antiqueRepository.findAllByNameContaining(str, pageable).map(antique ->
                {
                    List<String> imagePaths = antiqueImageService.getAntiqueImages(antique)
                            .stream()
                            .map(AntiqueImage::getPath)
                            .collect(Collectors.toList());
                    return AntiqueListingConverter.convert(antique, imagePaths);
                }
        );
    }

    @Override
    public Optional<AntiqueResponse> getAntiqueById(Integer id) {
        Optional<Antique> optionalAntique = antiqueRepository.findById(id);
        if (optionalAntique.isPresent()) {
            List<String> imagePaths = antiqueImageService.getAntiqueImages(optionalAntique.get())
                    .stream()
                    .map(AntiqueImage::getPath)
                    .collect(Collectors.toList());
            List<BigDecimal> bids = pastBidService.getPastBidsByAntique(optionalAntique.get())
                    .stream()
                    .map(PastBid::getBid)
                    .collect(Collectors.toList());
            return Optional.of(AntiqueResponseConverter.convert(optionalAntique.get(), bids, imagePaths));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Antique> addAntique(AntiqueRequest request) throws IOException {
        return addOrUpdateAntique(request);
    }

    @Override
    public Optional<Antique> updateAntique(AntiqueRequest request) throws IOException {
        return addOrUpdateAntique(request);
    }

    @Override
    @Transactional
    public void deleteAntiqueById(Integer id) {
        Optional<Antique> optionalAntique = antiqueRepository.findById(id);
        optionalAntique.ifPresent(antique -> {
            antiqueImageService.deleteAllByAntique(antique);
            pastBidService.deleteAllByAntique(antique);
            antiqueRepository.delete(antique);
        });
    }

    private Optional<Antique> addOrUpdateAntique(AntiqueRequest request) throws IOException {
        List<String> pathList = null;
        Optional<Antique> antique = Optional.of(antiqueRepository.save(AntiqueConverter.convert(request)));
        if (antique.isPresent()) {
            taskScheduler.schedule(taskService.newRunnable(antique.get()), Date.from(antique.get().getDeadline().atZone(ZoneId.systemDefault()).toInstant()));
            System.out.println("scheduled at :" + Date.from(antique.get().getDeadline().atZone(ZoneId.systemDefault()).toInstant()).toString());
            if (request.getImage() != null) {
                pathList = fileStorageService.storeZip(request.getImage());
                pathList.forEach(path -> {
                    AntiqueImage antiqueImage = new AntiqueImage();
                    antiqueImage.setaAntique(antique.get());
                    antiqueImage.setPath(path);
                    antiqueImageService.addAntiqueImage(antiqueImage);
                });
            }
        }
        return antique;
    }

    @Override
    public void makeBid(BidRequest request) {
        Optional<Antique> optionalAntique = antiqueRepository.findById(request.getId());
        LocalDateTime date = LocalDateTime.now();
        //check if the request bid is the highest bid and date is before deadline
        if (optionalAntique.isPresent()) {
            if (request.getBid().compareTo(pastBidService.getHighestBid(optionalAntique.get())) == 1
                    && request.getBid().compareTo(optionalAntique.get().getPrice()) == 1) {
                if (date.isAfter(optionalAntique.get().getDeadline())) {
                    throw new InvalidBidException("The auction has finished");
                }
                Antique antique = optionalAntique.get();
                PastBid pastBid = new PastBid();
                pastBid.setBid(request.getBid());
                pastBid.setAntique(antique);
                pastBid.setUser(userService.findLoggedInUser());
                pastBid.setTime(date);
                pastBid.setStatus(Status.IN_PROGRESS);
                antique.setLatestBid(request.getBid());
                pastBidService.insertPastBid(pastBid);
                antiqueRepository.save(antique);
            } else {
                throw new InvalidBidException("Your bid is invalid.Please make sure your bid is higher than current price.");
            }
        }
    }
}

