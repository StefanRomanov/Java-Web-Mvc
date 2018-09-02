package com.example.cardealer.entities.models.views;

import java.util.ArrayList;
import java.util.List;

public class CarsAllExportModel {
    private Long id;
    private String make;
    private String model;
    private Long traveledDistance;
    private List<PartExportModel> parts;

    public CarsAllExportModel() {
        this.parts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTraveledDistance() {
        return traveledDistance;
    }

    public void setTraveledDistance(Long traveledDistance) {
        this.traveledDistance = traveledDistance;
    }

    public List<PartExportModel> getParts() {
        return parts;
    }

    public void setParts(List<PartExportModel> parts) {
        this.parts = parts;
    }
}
