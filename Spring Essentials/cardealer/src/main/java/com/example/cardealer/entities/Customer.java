package com.example.cardealer.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Boolean isYoungDriver;
    private Set<Sale> sales;

    public Customer() {
        this.sales = new HashSet<>();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsYoungDriver() {
        return isYoungDriver;
    }

    public void setIsYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    @OneToMany(mappedBy = "customer")
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
