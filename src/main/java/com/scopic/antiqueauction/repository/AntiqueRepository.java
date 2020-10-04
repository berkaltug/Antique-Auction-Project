package com.scopic.antiqueauction.repository;

import com.scopic.antiqueauction.domain.entity.Antique;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AntiqueRepository extends PagingAndSortingRepository<Antique, Integer> {
}
