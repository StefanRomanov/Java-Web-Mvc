package com.example.cardealer.entities.models.views;

import java.math.BigDecimal;

public class CustomerInfoModel {
    private String name;
    private Integer carsCount;
    private BigDecimal totalSpent;

    public CustomerInfoModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(Integer carsCount) {
        this.carsCount = carsCount;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }
}
