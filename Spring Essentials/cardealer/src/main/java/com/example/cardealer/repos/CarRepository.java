package com.example.cardealer.repos;

import com.example.cardealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> getAllByMakeOrderByModelAscTraveledDistanceDesc(String make);
}
