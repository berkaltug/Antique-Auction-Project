package com.scopic.antiqueauction.domain.request;

import java.math.BigInteger;

public class BidRequest {
    private Integer id;
    private BigInteger bid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getBid() {
        return bid;
    }

    public void setBid(BigInteger bid) {
        this.bid = bid;
    }
}
