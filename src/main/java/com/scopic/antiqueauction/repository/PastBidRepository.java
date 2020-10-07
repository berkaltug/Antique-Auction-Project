package com.scopic.antiqueauction.repository;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastBidRepository extends JpaRepository<PastBid,Integer> {
    List<PastBid> findAllByAntique(Antique antique);
    void deleteAllByAntique(Antique antique);
}
