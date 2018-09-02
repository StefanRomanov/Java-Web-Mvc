package com.example.cardealer.entities.models.views;

import java.math.BigDecimal;

public class PartExportModel {
    private String name;
    private BigDecimal price;

    public PartExportModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}