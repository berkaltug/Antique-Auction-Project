package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.AntiqueImage;

import java.util.List;

public interface AntiqueImageService {
    void addAntiqueImage(AntiqueImage antiqueImage);
    List<AntiqueImage> getAntiqueImages(Antique antique);
    void deleteAllByAntique(Antique antique);
}
