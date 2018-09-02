package com.example.cardealer.entities.models.dto;

import java.math.BigDecimal;

public class SaleAddDto {
    private Long car;
    private Long customer;
    private BigDecimal discount;

    public SaleAddDto() {
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
