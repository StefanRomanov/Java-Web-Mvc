package com.example.cardealer.repos;

import com.example.cardealer.entities.Car;
import com.example.cardealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    List<Part> getAllByCarsIn(Car car);
    Set<Part> getAllByIdIn(List<Long> ids);
}
