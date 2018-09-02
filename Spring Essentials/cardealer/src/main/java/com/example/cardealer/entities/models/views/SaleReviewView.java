package com.example.cardealer.entities.models.views;

import java.math.BigDecimal;

public class SaleReviewView {
    private CarsAllExportModel car;
    private CustomerAscDescModel customer;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal priceWithDiscount;

    public SaleReviewView() {
    }

    public CarsAllExportModel getCar() {
        return car;
    }

    public void setCar(CarsAllExportModel car) {
        this.car = car;
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

    public CustomerAscDescModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerAscDescModel customer) {
        this.customer = customer;
    }
}
