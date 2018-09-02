package com.excersise.virus.entities;

import com.excersise.virus.entities.enums.Magnitude;
import com.excersise.virus.entities.enums.Mutation;
import jdk.jfr.DataAmount;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//Name – Cannot be empty, should be between 3 and 10 symbols.
//o Description – Cannot be empty, should be between 5 and 100 symbols.
// Represented as Text in the database
//o Side Effects – Should have a maximum of 50 symbols.
//o Creator – Should be either Corp or corp.
//o Is Deadly – Boolean
//o Is Curable – Boolean
//o Mutation – Cannot be null. Should hold one of the following values:
// ZOMBIE
// T_078_TYRANT
// GIANT_SPIDER
//o Turnover Rate – Number, between 0 and 100.
//o Hours Until Turn (to a mutation) – Number, between 1 and 12.
//o Magnitude – Cannot be null. Should hold one of the following values:
// Low
//
//© Software University Foundation (softuni.org). This work is licensed under the CC-BY-NC-SA license.
//Follow us: Page 2 of 8
// Medium
// High
//o Released On – Date, should be before the “today” date.
//o Capitals – A collection of Capitals.


@Entity
@Table(name = "viruses")
public class Virus {

    private Long id;
    private String name;
    private String description;
    private String sideEffects;
    private String creator;
    private Boolean isDeadly;
    private Boolean isCurable;
    private Mutation mutation;
    private Integer turnoverRate;
    private Integer hoursUntilTurn;
    private Magnitude magnitude;
    private LocalDate releasedOn;
    private Set<Capital> capitals;

    public Virus() {
        this.capitals = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Size(min = 3,max = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false,columnDefinition = "TEXT")
    @Size(min = 5,max = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Size(max = 50)
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Min(0)
    @Max(100)
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Min(1)
    @Max(12)
    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    @ManyToMany
    public Set<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Capital> capitals) {
        this.capitals = capitals;
    }
}
