package com.example.cardealer.repos;

import com.example.cardealer.entities.Customer;
import com.example.cardealer.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    List<Sale> getAllByCustomer(Customer customer);
}
