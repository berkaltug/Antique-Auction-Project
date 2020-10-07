package com.scopic.antiqueauction.repository;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.AntiqueImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AntiqueImageRepository extends JpaRepository<AntiqueImage,Integer> {
    List<AntiqueImage> findAllByAntique(Antique antique);
    void deleteAllByAntique(Antique antique);
}
