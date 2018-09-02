package com.example.cardealer.services;

import com.example.cardealer.entities.Car;
import com.example.cardealer.entities.models.dto.CarAddModel;
import com.example.cardealer.entities.models.views.CarsAllExportModel;

import java.util.List;

public interface CarService {
    List<CarsAllExportModel> allCarsByMake(String make);

    CarsAllExportModel carWithParts(Long id);

    List<CarsAllExportModel> allCars();

    Boolean addCar(CarAddModel model);
}
