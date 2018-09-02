package com.excersise.virus.entities.models;

import com.excersise.virus.entities.enums.Magnitude;

import java.time.LocalDate;

public class VirusAllModel {
    private Long id;
    private String name;
    private LocalDate releasedOn;
    private Magnitude magnitude;

    public VirusAllModel() {
    }

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

    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }
}
