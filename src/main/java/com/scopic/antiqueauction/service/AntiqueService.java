package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.dto.AntiqueDto;
import com.scopic.antiqueauction.domain.entity.Antique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface AntiqueService {
    Page<Antique> getAllAntiques(int pageNo, Sort.Direction direction);
    Optional<Antique> getAntiqueById(Integer id);
    Optional<Antique> updateAntique(Antique antique, String imagePath);
    Optional<Antique> deleteAntiqueById(Integer id);
}
