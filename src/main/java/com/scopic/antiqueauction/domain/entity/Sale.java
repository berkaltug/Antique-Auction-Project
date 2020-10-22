package com.scopic.antiqueauction.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Antique antique;

    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;
    private LocalDateTime date;

    public Sale(Antique antique, BigDecimal price, User buyer, LocalDateTime date) {
        this.antique = antique;
        this.price = price;
        this.buyer = buyer;
        this.date = date;
    }

    public Sale() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Antique getAntique() {
        return antique;
    }

    public void setAntique(Antique antique) {
        this.antique = antique;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) &&
                Objects.equals(antique, sale.antique) &&
                Objects.equals(price, sale.price) &&
                Objects.equals(buyer, sale.buyer) &&
                Objects.equals(date, sale.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, antique, price, buyer, date);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", antique=" + antique +
                ", price=" + price +
                ", buyer=" + buyer +
                ", date=" + date +
                '}';
    }
}
