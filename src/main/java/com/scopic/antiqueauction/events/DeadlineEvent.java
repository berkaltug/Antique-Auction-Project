package com.scopic.antiqueauction.events;

import com.scopic.antiqueauction.domain.entity.Antique;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

public class DeadlineEvent extends ApplicationEvent {

    private Antique antique;

    public DeadlineEvent(Object source, Antique antique) {
        super(source);
        this.antique=antique;
    }

    public Antique getAntique() {
        return antique;
    }

    public void setAntique(Antique antique) {
        this.antique = antique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeadlineEvent that = (DeadlineEvent) o;
        return Objects.equals(antique, that.antique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(antique);
    }

    @Override
    public String toString() {
        return "DeadlineFinishedEvent{" +
                "antique=" + antique +
                '}';
    }
}
