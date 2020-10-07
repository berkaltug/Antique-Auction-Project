package com.scopic.antiqueauction.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private BigDecimal price;
    private BigDecimal latestBid;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Antique antique = (Antique) o;
        return Objects.equals(id, antique.id) &&
                Objects.equals(name, antique.name) &&
                Objects.equals(description, antique.description) &&
                Objects.equals(price, antique.price) &&
                Objects.equals(latestBid, antique.latestBid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, latestBid);
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
