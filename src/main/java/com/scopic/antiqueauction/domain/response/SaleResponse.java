package com.scopic.antiqueauction.domain.response;

import java.math.BigDecimal;

public class SaleResponse {

    private String antique;
    private String buyer;
    private BigDecimal price;
    private String date;

    public String getAntique() {
        return antique;
    }

    public void setAntique(String antique) {
        this.antique = antique;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
