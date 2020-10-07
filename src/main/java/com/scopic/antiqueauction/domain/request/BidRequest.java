package com.scopic.antiqueauction.domain.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BidRequest {
    @NotEmpty(message = "Antique id must be provided")
    private Integer id;
    @NotNull(message = "bid must not be null")
    @DecimalMin(value="0.0",inclusive = false,message = "Bid must be higer than zero")
    @Digits(integer=7,fraction = 2,message = "Bid must have max 7 digit integer and 2 digit fraction")
    private BigDecimal bid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }
}
