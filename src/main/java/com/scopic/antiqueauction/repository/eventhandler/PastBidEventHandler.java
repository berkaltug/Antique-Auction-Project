package com.scopic.antiqueauction.repository.eventhandler;

import com.scopic.antiqueauction.domain.entity.PastBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RepositoryEventHandler(PastBid.class)
public class PastBidEventHandler {
    private final SimpMessagingTemplate messagingTemplate;


    public PastBidEventHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @HandleAfterSave
    public void newBid(PastBid pastBid){
        System.out.println("called handler");
        messagingTemplate.convertAndSend("/topic/new-bid/" + pastBid.getId(),pastBid);
    }
    @HandleBeforeCreate
    public void newBida(PastBid pastBid){
        System.out.println("called handler");
        messagingTemplate.convertAndSend("/topic/new-bid/" + pastBid.getId(),pastBid);
    }

}
