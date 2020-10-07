package com.scopic.antiqueauction.domain.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class AntiqueResponse {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal latestBid;
    private List<BigDecimal> pastBids;
    private List<String> imagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLatestBid() {
        return latestBid;
    }

    public void setLatestBid(BigDecimal latestBid) {
        this.latestBid = latestBid;
    }

    public List<BigDecimal> getPastBids() {
        return pastBids;
    }

    public void setPastBids(List<BigDecimal> pastBids) {
        this.pastBids = pastBids;
    }

    public List<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(List<String> imagePath) {
        this.imagePath = imagePath;
    }
}
