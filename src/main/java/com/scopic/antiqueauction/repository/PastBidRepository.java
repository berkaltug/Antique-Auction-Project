package com.scopic.antiqueauction.repository;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastBidRepository extends CrudRepository<PastBid,Integer> {
    List<PastBid> findAllByAntique(Antique antique);
    void deleteAllByAntique(Antique antique);
    List<PastBid> findAllByUser(User user);
    List<PastBid> findAllByAntiqueAndUser(Antique antique,User user);
}
