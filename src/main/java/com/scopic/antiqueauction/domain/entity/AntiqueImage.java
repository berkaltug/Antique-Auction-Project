package com.scopic.antiqueauction.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AntiqueImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String path;
    @ManyToOne
    private Antique antique;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Antique getaAntique() {
        return antique;
    }

    public void setaAntique(Antique aNtique) {
        this.antique = aNtique;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AntiqueImage)) return false;
        AntiqueImage that = (AntiqueImage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(path, that.path) &&
                Objects.equals(antique, that.antique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, antique);
    }

    @Override
    public String toString() {
        return "AntiqueImage{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", antique=" + antique +
                '}';
    }
}
