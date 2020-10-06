package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.AntiqueImage;
import com.scopic.antiqueauction.repository.AntiqueImageRepository;
import com.scopic.antiqueauction.service.AntiqueImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AntiqueImageServiceImpl implements AntiqueImageService {

    private AntiqueImageRepository antiqueImageRepository;

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
}
