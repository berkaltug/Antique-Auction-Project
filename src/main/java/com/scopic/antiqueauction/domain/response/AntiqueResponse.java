package com.scopic.antiqueauction.domain.response;

import java.math.BigInteger;
import java.util.List;

public class AntiqueResponse {
    private Integer id;
    private String name;
    private String description;
    private BigInteger price;
    private BigInteger latestBid;
    private List<BigInteger> pastBids;
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

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public BigInteger getLatestBid() {
        return latestBid;
    }

    public void setLatestBid(BigInteger latestBid) {
        this.latestBid = latestBid;
    }

    public List<BigInteger> getPastBids() {
        return pastBids;
    }

    public void setPastBids(List<BigInteger> pastBids) {
        this.pastBids = pastBids;
    }

    public List<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(List<String> imagePath) {
        this.imagePath = imagePath;
    }
}
