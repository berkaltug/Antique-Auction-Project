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
    private BigDecimal bid;

    @Override
    public String toString() {
        return "PastBid{" +
                "id=" + id +
                ", antique=" + antique +
                ", bid=" + bid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PastBid)) return false;
        PastBid pastBid = (PastBid) o;
        return Objects.equals(id, pastBid.id) &&
                Objects.equals(antique, pastBid.antique) &&
                Objects.equals(bid, pastBid.bid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, antique, bid);
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

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }
}
