package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.AntiqueImage;
import com.scopic.antiqueauction.repository.AntiqueImageRepository;
import com.scopic.antiqueauction.service.AntiqueImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntiqueImageServiceImpl implements AntiqueImageService {

    private final AntiqueImageRepository antiqueImageRepository;

    @Autowired
    public AntiqueImageServiceImpl(AntiqueImageRepository antiqueImageRepository) {
        this.antiqueImageRepository = antiqueImageRepository;
    }

    @Override
    public void addAntiqueImage(AntiqueImage antiqueImage) {
        antiqueImageRepository.save(antiqueImage);
    }

    @Override
    public List<AntiqueImage> getAntiqueImages(Antique antique) {
        return antiqueImageRepository.findAllByAntique(antique);
    }

    @Override
    public void deleteAllByAntique(Antique antique) {
        antiqueImageRepository.deleteAllByAntique(antique);
    }
}
