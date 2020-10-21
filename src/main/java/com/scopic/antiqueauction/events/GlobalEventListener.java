package com.scopic.antiqueauction.events;

import com.scopic.antiqueauction.domain.converter.PastBidResponseConverter;
import com.scopic.antiqueauction.domain.entity.PastBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
@Component
public class  GlobalEventListener{

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public GlobalEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleBidMadeEvent(BidMadeEvent bidMadeEvent){
        PastBid bid = bidMadeEvent.getBid();
        messagingTemplate.convertAndSend("/antique-topic/bid/"+bid.getAntique().getId(), PastBidResponseConverter.convert(bid));
    }
}
