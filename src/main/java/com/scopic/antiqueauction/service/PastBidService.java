package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;

import java.util.List;

public interface PastBidService {
    void insertPastBid(PastBid pastBid);
    List<PastBid> getPastBidsByAntique(Antique antique);
}