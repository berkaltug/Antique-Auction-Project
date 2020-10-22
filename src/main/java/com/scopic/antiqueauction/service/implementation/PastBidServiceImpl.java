package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.domain.enums.Status;
import com.scopic.antiqueauction.events.EventPublisher;
import com.scopic.antiqueauction.repository.PastBidRepository;
import com.scopic.antiqueauction.service.PastBidService;
import com.scopic.antiqueauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PastBidServiceImpl implements PastBidService {
    private final PastBidRepository pastBidRepository;
    private final EventPublisher eventPublisher;
    private final UserService userService;
    @Autowired
    public PastBidServiceImpl(PastBidRepository pastBidRepository, EventPublisher eventPublisher, UserService userService) {
        this.pastBidRepository = pastBidRepository;
        this.eventPublisher = eventPublisher;
        this.userService = userService;
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
    public PastBid getHighestPastBid(Antique antique){
        List<PastBid> pastBids=pastBidRepository.findAllByAntique(antique);
        pastBids.sort(Comparator.comparing(PastBid::getBid).reversed());
        return pastBids.get(0);
    }
    @Override
    public void markAllBidsExceptHighest(Antique antique, Status status){
        List<PastBid> pastBids=pastBidRepository.findAllByAntique(antique);
        pastBids.sort(Comparator.comparing(PastBid::getBid).reversed());
        pastBids.remove(0);
        List<PastBid> others=pastBids.stream().peek(bid-> bid.setStatus(status)).collect(Collectors.toList());
        pastBidRepository.saveAll(others);
    }

    @Override
    public void deleteAllByAntique(Antique antique) {
        pastBidRepository.deleteAllByAntique(antique);
    }

    @Override
    public List<PastBid> getUserBids() {
        return pastBidRepository.findAllByUser(userService.findLoggedInUser());
    }

    @Override
    public List<PastBid> getUserBidsForAntique(Antique antique, User user) {
        return pastBidRepository.findAllByAntiqueAndUser(antique, user);
    }

    public List<PastBid> getUsersLatestBidsForAntiques(){
        //finding users all past bids
        List<PastBid> pastBids = pastBidRepository.findAllByUser(userService.findLoggedInUser());
        //sorting it with price descending
        pastBids.sort(Comparator.comparing(PastBid::getBid).reversed());
        //grouping them according to antique so we can find latest bid for every antique
        LinkedHashMap<Antique, List<PastBid>> map = pastBids.stream().collect(Collectors.groupingBy(PastBid::getAntique, LinkedHashMap::new, Collectors.toList()));
        //making a list with highest bids of user for every antique
        return map.values().stream().map(list -> list.get(0)).collect(Collectors.toList());
    }
}
