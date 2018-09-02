package com.example.cardealer.services;

import com.example.cardealer.entities.Car;
import com.example.cardealer.entities.Part;
import com.example.cardealer.entities.models.dto.CarAddModel;
import com.example.cardealer.entities.models.views.CarsAllExportModel;
import com.example.cardealer.entities.models.views.PartExportModel;
import com.example.cardealer.repos.CarRepository;
import com.example.cardealer.repos.LogRepository;
import com.example.cardealer.repos.PartRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private LogRepository logRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper, LogRepository logRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.logRepository = logRepository;
    }


    @Override
    public List<CarsAllExportModel> allCarsByMake(String make) {
        List<Car> cars = this.carRepository.getAllByMakeOrderByModelAscTraveledDistanceDesc(make);
        Type type = new TypeToken<List<CarsAllExportModel>>(){}.getType();

        return this.modelMapper.map(cars,type);
    }

    @Override
    public CarsAllExportModel carWithParts(Long id) {
        Car car = this.carRepository.getOne(id);
        if(car == null){
            return null;
        } else {
            List<Part> parts = this.partRepository.getAllByCarsIn(car);
            Type type = new TypeToken<List<PartExportModel>>(){}.getType();
            List<PartExportModel> partModels = this.modelMapper.map(parts,type);
            CarsAllExportModel model = this.modelMapper.map(car,CarsAllExportModel.class);
            model.setParts(partModels);

            return model;
        }
    }

    @Override
    public List<CarsAllExportModel> allCars() {
        List<Car> cars = this.carRepository.findAll();
        Type type = new TypeToken<List<CarsAllExportModel>>(){}.getType();

        return this.modelMapper.map(cars,type);
    }

    @Override
    public Boolean addCar(CarAddModel model) {
        long[] numbers =  Arrays.stream(model.getParts().split(" ")).mapToLong(Long::parseLong).toArray();
        List<Long> partIds = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        Set<Part> parts = this.partRepository.getAllByIdIn(partIds);

        Car car = this.modelMapper.map(model, Car.class);
        car.setParts(parts);

        for (Part part : parts) {
            part.getCars().add(car);
        }
        Car result = this.carRepository.saveAndFlush(car);

        return result != null;
    }
}
