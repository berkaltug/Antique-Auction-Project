package com.scopic.antiqueauction.domain.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Antique {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private BigInteger price;
    private BigInteger latestBid;
    private String imagePath;

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Antique antique = (Antique) o;
        return Objects.equals(id, antique.id) &&
                Objects.equals(name, antique.name) &&
                Objects.equals(description, antique.description) &&
                Objects.equals(price, antique.price) &&
                Objects.equals(latestBid, antique.latestBid) &&
                Objects.equals(imagePath, antique.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, latestBid, imagePath);
    }

    @Override
    public String toString() {
        return "Antique{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", latestBid=" + latestBid +
                '}';
    }
}
