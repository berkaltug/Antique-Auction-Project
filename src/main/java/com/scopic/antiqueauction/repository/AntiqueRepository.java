package com.scopic.antiqueauction.repository;

import com.scopic.antiqueauction.domain.entity.Antique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntiqueRepository extends PagingAndSortingRepository<Antique, Integer> {
    Page<Antique> findAllByNameContaining(String str, Pageable pageable);
}
