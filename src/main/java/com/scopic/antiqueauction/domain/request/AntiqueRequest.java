package com.scopic.antiqueauction.domain.request;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

public class AntiqueRequest {

    private Integer id;
    private String name;
    private String description;
    private BigInteger price;
    private BigInteger latestBid;
    private MultipartFile image;

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
