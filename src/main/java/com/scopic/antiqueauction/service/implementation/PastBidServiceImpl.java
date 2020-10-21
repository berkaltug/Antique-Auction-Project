package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.events.EventPublisher;
import com.scopic.antiqueauction.repository.PastBidRepository;
import com.scopic.antiqueauction.service.PastBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.AfterCreateEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class PastBidServiceImpl implements PastBidService {
    private final PastBidRepository pastBidRepository;
    private final EventPublisher eventPublisher;

    public PastBidServiceImpl(PastBidRepository pastBidRepository, EventPublisher eventPublisher) {
        this.pastBidRepository = pastBidRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void insertPastBid(PastBid pastBid) {
        pastBidRepository.save(pastBid);
        eventPublisher.publishBidMadeEvent(pastBid);
    }

    @Override
    public List<PastBid> getPastBidsByAntique(Antique antique) {
        return pastBidRepository.findAllByAntique(antique);
    }

    @Override
    public BigDecimal getHighestBid(Antique antique){
        BigDecimal highest=new BigDecimal("0");
        List<PastBid> pastBids=pastBidRepository.findAllByAntique(antique);
        for (PastBid pastBid : pastBids) {
            if (pastBid.getBid().compareTo(highest) == 1) {
                highest = pastBid.getBid();
            }
        }
        return highest;
    }

    @Override
    public void deleteAllByAntique(Antique antique) {
        pastBidRepository.deleteAllByAntique(antique);
    }

    @Override
    public List<PastBid> getUserBids(User user) {
        return pastBidRepository.findAllByUser(user);
    }

    @Override
    public List<PastBid> getUserBidsForAntique(Antique antique, User user) {
        return pastBidRepository.findAllByAntiqueAndUser(antique, user);
    }
}
