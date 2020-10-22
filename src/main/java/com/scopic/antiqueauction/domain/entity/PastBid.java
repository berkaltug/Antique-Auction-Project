package com.scopic.antiqueauction.domain.entity;

import com.scopic.antiqueauction.domain.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PastBid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Antique antique;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private BigDecimal bid;
    private LocalDateTime time;
    private Status status;

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PastBid pastBid = (PastBid) o;
        return Objects.equals(id, pastBid.id) &&
                Objects.equals(antique, pastBid.antique) &&
                Objects.equals(user, pastBid.user) &&
                Objects.equals(bid, pastBid.bid) &&
                Objects.equals(time, pastBid.time) &&
                status == pastBid.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, antique, user, bid, time, status);
    }

    @Override
    public String toString() {
        return "PastBid{" +
                "id=" + id +
                ", antique=" + antique +
                ", user=" + user +
                ", bid=" + bid +
                ", time=" + time +
                ", state=" + status +
                '}';
    }
}
