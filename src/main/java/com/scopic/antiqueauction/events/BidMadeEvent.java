package com.scopic.antiqueauction.events;

import com.scopic.antiqueauction.domain.entity.PastBid;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

public class BidMadeEvent extends ApplicationEvent {
    private PastBid bid;

    public BidMadeEvent(Object source, PastBid bid) {
        super(source);
        this.bid = bid;
    }

    public PastBid getBid() {
        return bid;
    }

    public void setBid(PastBid bid) {
        this.bid = bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidMadeEvent that = (BidMadeEvent) o;
        return Objects.equals(bid, that.bid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid);
    }

    @Override
    public String toString() {
        return "BidMadeEvent{" +
                "bid=" + bid +
                '}';
    }
}
