package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.repository.AntiqueRepository;
import com.scopic.antiqueauction.service.AntiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AntiqueServiceImpl implements AntiqueService {

    private AntiqueRepository antiqueRepository;

    @Autowired
    public AntiqueServiceImpl(AntiqueRepository antiqueRepository) {
        this.antiqueRepository = antiqueRepository;
    }

    @Override
    public Page<Antique> getAllAntiques(int pageNo, Sort.Direction direction) {
        Pageable page= PageRequest.of(pageNo,10,Sort.by(direction,"price"));
        return antiqueRepository.findAll(page);
    }

    @Override
    public Optional<Antique> getAntiqueById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Antique> updateAntique(Antique antique) {
        return Optional.empty();
    }

    @Override
    public Optional<Antique> deleteAntiqueById(Integer id) {
        return Optional.empty();
    }
}
