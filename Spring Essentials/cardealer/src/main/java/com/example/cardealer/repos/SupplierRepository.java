package com.example.cardealer.repos;

import com.example.cardealer.entities.Customer;
import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.models.dto.SupplierAddModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    List<Supplier> getAllByIsImporter(Boolean isImporter);
    Supplier getFirstByName(String name);
}
