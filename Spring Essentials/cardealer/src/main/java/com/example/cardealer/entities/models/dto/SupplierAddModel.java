package com.example.cardealer.entities.models.dto;

public class SupplierAddModel {
    private Long id;
    private String name;
    private Boolean isImporter;

    public SupplierAddModel() {
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

    public Boolean getIsImporter() {
        return isImporter;
    }

    public void setIsImporter(Boolean importer) {
        isImporter = importer;
    }
}
