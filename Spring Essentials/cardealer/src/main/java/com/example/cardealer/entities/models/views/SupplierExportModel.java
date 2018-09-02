package com.example.cardealer.entities.models.views;

public class SupplierExportModel {
    private Long id;
    private String name;
    private Boolean isImporter;
    private Integer numberOfParts;

    public SupplierExportModel() {
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

    public Integer getNumberOfParts() {
        return numberOfParts;
    }

    public void setNumberOfParts(Integer numberOfParts) {
        this.numberOfParts = numberOfParts;
    }

    public Boolean getIsImporter() {
        return isImporter;
    }

    public void setIsImporter(Boolean importer) {
        isImporter = importer;
    }
}
