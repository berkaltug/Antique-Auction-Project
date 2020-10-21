package com.scopic.antiqueauction.domain.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PastBidResponse {

    private Integer AntiqueId;
    private String username;
    private BigDecimal bid;
    private LocalDateTime time;

    public Integer getAntiqueId() {
        return AntiqueId;
    }

    public void setAntiqueId(Integer antiqueId) {
        AntiqueId = antiqueId;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
