package com.example.cardealer.entities.models.views;

import java.math.BigDecimal;

public class SaleExportModel {
    private CarsAllExportModel car;
    private String customerName;
    private BigDecimal price;
    private BigDecimal discount;
    private Boolean extraDiscount;
    private BigDecimal priceWithDiscount;

    public SaleExportModel() {
    }

    public CarsAllExportModel getCar() {
        return car;
    }

    public void setCar(CarsAllExportModel car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPriceWithDiscount() {
        return this.price.subtract(this.price.multiply(this.discount));
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public Boolean getExtraDiscount() {
        return extraDiscount;
    }

    public void setExtraDiscount(Boolean extraDiscount) {
        this.extraDiscount = extraDiscount;
    }
}
