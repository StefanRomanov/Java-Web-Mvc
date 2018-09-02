package com.excersise.virus.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "capitals")
public class Capital {

    private Long id;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Set<Virus> viruses;

    public Capital() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @ManyToMany(mappedBy = "capitals")
    public Set<Virus> getViruses() {
        return viruses;
    }

    public void setViruses(Set<Virus> viruses) {
        this.viruses = viruses;
    }
}
