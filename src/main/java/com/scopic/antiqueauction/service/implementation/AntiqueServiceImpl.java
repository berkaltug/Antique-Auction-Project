package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.converter.AntiqueResponseConverter;
import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;
import com.scopic.antiqueauction.repository.AntiqueRepository;
import com.scopic.antiqueauction.repository.PastBidRepository;
import com.scopic.antiqueauction.service.AntiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AntiqueServiceImpl implements AntiqueService {

    private AntiqueRepository antiqueRepository;
    private PastBidRepository pastBidRepository;
    @Autowired
    public AntiqueServiceImpl(AntiqueRepository antiqueRepository, PastBidRepository pastBidRepository) {
        this.antiqueRepository = antiqueRepository;
        this.pastBidRepository = pastBidRepository;
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
            List<BigInteger> bids=pastBidRepository.findAllByAntique(optionalAntique.get())
                    .stream()
                    .map(pastBid -> pastBid.getBid())
                    .collect(Collectors.toList());
            return  Optional.of(AntiqueResponseConverter.convert(optionalAntique.get(),bids));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<Antique> updateAntique(Antique antique,String imagePath) {
        antique.setImagePath(imagePath);
        return Optional.of(antiqueRepository.save(antique));
    }

    @Override
    public void deleteAntiqueById(Integer id) {
        antiqueRepository.deleteById(id);
    }
}
