package com.scopic.antiqueauction.domain.response;

import com.scopic.antiqueauction.domain.enums.Status;

import java.math.BigDecimal;

public class PastBidResponse {

    private String antique;
    private String username;
    private BigDecimal bid;
    private String time;
    private Status status;

    public String getAntique() {
        return antique;
    }

    public void setAntique(String antique) {
        this.antique = antique;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
