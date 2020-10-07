package com.scopic.antiqueauction.domain.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AntiqueRequest {

    private Integer id;
    @NotBlank(message = "Name field must not be blank")
    private String name;
    @NotBlank(message = "Description field must not be blank")
    private String description;
    @NotNull(message = "price must not be null")
    @DecimalMin(value="0.0",inclusive = false,message = "price must be higer than zero")
    @Digits(integer=7,fraction = 2,message = "price must have max 7 digit integer and 2 digit fraction")
    private BigDecimal price;
    private BigDecimal latestBid;
    // image uploading should be optional i guess
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
