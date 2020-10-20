package com.scopic.antiqueauction.repository.eventhandler;

import com.scopic.antiqueauction.domain.entity.PastBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class PastBidEventHandler {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public PastBidEventHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @HandleAfterCreate
    public void newBid(PastBid pastBid){
        messagingTemplate.convertAndSend("/topic/new-bid/" + pastBid.getId(),pastBid);
    }

}
