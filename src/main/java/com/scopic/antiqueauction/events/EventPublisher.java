package com.scopic.antiqueauction.events;

import com.scopic.antiqueauction.domain.entity.PastBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher publisher;
    @Autowired
    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishBidMadeEvent(PastBid bid){
        publisher.publishEvent(new BidMadeEvent(this,bid));
    }
}
