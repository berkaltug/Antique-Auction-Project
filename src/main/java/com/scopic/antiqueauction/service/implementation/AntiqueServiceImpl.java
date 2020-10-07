package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.converter.AntiqueConverter;
import com.scopic.antiqueauction.domain.converter.AntiqueResponseConverter;
import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.AntiqueImage;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.request.AntiqueRequest;
import com.scopic.antiqueauction.domain.request.BidRequest;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;
import com.scopic.antiqueauction.exceptions.InvalidBidException;
import com.scopic.antiqueauction.repository.AntiqueRepository;
import com.scopic.antiqueauction.service.AntiqueImageService;
import com.scopic.antiqueauction.service.AntiqueService;
import com.scopic.antiqueauction.service.FileStorageService;
import com.scopic.antiqueauction.service.PastBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AntiqueServiceImpl implements AntiqueService {

    private AntiqueRepository antiqueRepository;
    private PastBidService pastBidService;
    private AntiqueImageService antiqueImageService;
    private FileStorageService fileStorageService;

    @Autowired
    public AntiqueServiceImpl(AntiqueRepository antiqueRepository, PastBidService pastBidService,AntiqueImageService antiqueImageService,FileStorageService fileStorageService) {
        this.antiqueRepository = antiqueRepository;
        this.pastBidService = pastBidService;
        this.antiqueImageService = antiqueImageService;
        this.fileStorageService=fileStorageService;
    }

    @Override
    public Page<Antique> getAllAntiques(int pageNo, Sort.Direction direction) {
        Pageable page= PageRequest.of(pageNo,10,Sort.by(direction,"price"));
        return antiqueRepository.findAll(page);
    }

    @Override
    public Optional<AntiqueResponse> getAntiqueById(Integer id) {
        Optional<Antique> optionalAntique = antiqueRepository.findById(id);
        if(optionalAntique.isPresent()){
            List<String> imagePaths=antiqueImageService.getAntiqueImages(optionalAntique.get())
                    .stream()
                    .map(antiqueImage -> antiqueImage.getPath())
                    .collect(Collectors.toList());
            List<BigInteger> bids=pastBidService.getPastBidsByAntique(optionalAntique.get())
                    .stream()
                    .map(pastBid -> pastBid.getBid())
                    .collect(Collectors.toList());
            return  Optional.of(AntiqueResponseConverter.convert(optionalAntique.get(),bids,imagePaths));
        }else{
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
    public void deleteAntiqueById(Integer id) {
        antiqueRepository.deleteById(id);
    }

    private Optional<Antique> addOrUpdateAntique(AntiqueRequest request) throws IOException {
        List<String> pathList=null;
        Optional<Antique> antique=Optional.of(antiqueRepository.save(AntiqueConverter.convert(request)));
        if (antique.isPresent() && request.getImage() != null) {
            pathList= fileStorageService.storeZip(request.getImage());
            pathList.forEach(path-> {
                AntiqueImage antiqueImage=new AntiqueImage();
                antiqueImage.setaAntique(antique.get());
                antiqueImage.setPath(path);
                antiqueImageService.addAntiqueImage(antiqueImage);
            });
        }
        return antique;
    }
    @Override
    public void makeBid(BidRequest request){
        Optional<Antique> optionalAntique=antiqueRepository.findById(request.getId());
        //check if the request bid is the highest bid
        if(optionalAntique.isPresent()) {
            if (request.getBid().compareTo(pastBidService.getHighestBid(optionalAntique.get())) == 1
                    && request.getBid().compareTo(optionalAntique.get().getPrice()) == 1) {
                Antique antique = optionalAntique.get();
                PastBid pastBid = new PastBid();
                pastBid.setBid(request.getBid());
                pastBid.setAntique(antique);
                antique.setLatestBid(request.getBid());
                pastBidService.insertPastBid(pastBid);
                antiqueRepository.save(antique);
            } else {
                throw new InvalidBidException("Your bid is invalid.Please make sure your bid is higher than current price.");
            }
        }
    }
}

