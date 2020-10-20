package com.scopic.antiqueauction.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class PastBid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Antique antique;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private BigDecimal bid;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PastBid pastBid = (PastBid) o;
        return Objects.equals(id, pastBid.id) &&
                Objects.equals(antique, pastBid.antique) &&
                Objects.equals(user, pastBid.user) &&
                Objects.equals(bid, pastBid.bid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, antique, user, bid);
    }

    @Override
    public String toString() {
        return "PastBid{" +
                "id=" + id +
                ", antique=" + antique +
                ", user=" + user +
                ", bid=" + bid +
                '}';
    }
}
