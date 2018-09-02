package com.example.cardealer.entities.models.dto;

public class CarAddModel {
    private String make;
    private String model;
    private Long traveledDistance;
    private String parts;

    public CarAddModel() {
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

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }
}
