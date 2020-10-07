package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.repository.PastBidRepository;
import com.scopic.antiqueauction.service.PastBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class PastBidServiceImpl implements PastBidService {
    private PastBidRepository pastBidRepository;
    @Autowired
    public PastBidServiceImpl(PastBidRepository pastBidRepository) {
        this.pastBidRepository = pastBidRepository;
    }

    @Override
    public void insertPastBid(PastBid pastBid) {
        pastBidRepository.save(pastBid);
    }

    @Override
    public List<PastBid> getPastBidsByAntique(Antique antique) {
        return pastBidRepository.findAllByAntique(antique);
    }

    @Override
    public BigInteger getHighestBid(Antique antique){
        BigInteger highest=new BigInteger("0");
        List<PastBid> pastBids=pastBidRepository.findAllByAntique(antique);
        for (PastBid pastBid : pastBids) {
            if (pastBid.getBid().compareTo(highest) == 1) {
                highest = pastBid.getBid();
            }
        }
        return highest;
    }
}
