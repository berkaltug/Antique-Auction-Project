package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.domain.enums.Status;

import java.math.BigDecimal;
import java.util.List;

public interface PastBidService {
    void insertPastBid(PastBid pastBid);
    List<PastBid> getPastBidsByAntique(Antique antique);
    BigDecimal getHighestBid(Antique antique);
    PastBid getHighestPastBid(Antique antique);
    void deleteAllByAntique(Antique antique);
    List<PastBid> getUserBids();
    List<PastBid> getUserBidsForAntique(Antique antique, User user);
    void markAllBidsExceptHighest(Antique antique, Status status);
    List<PastBid> getUsersLatestBidsForAntiques();
}
