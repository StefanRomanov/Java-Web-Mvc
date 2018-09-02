package com.example.cardealer.controllers;

import com.example.cardealer.entities.models.dto.CarAddModel;
import com.example.cardealer.entities.models.dto.LogCreateModel;
import com.example.cardealer.entities.models.views.CarsAllExportModel;
import com.example.cardealer.services.CarService;
import com.example.cardealer.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController{

    private CarService carService;
    private LogService logService;

    @Autowired
    public CarController(CarService carService, LogService logService) {
        this.carService = carService;
        this.logService = logService;
    }

    @GetMapping(value = "/{make}")
    public ModelAndView allCarsByMake(@PathVariable("make") String make, HttpSession session){
        List<CarsAllExportModel> models = this.carService.allCarsByMake(make);
        return super.view("/main/all-cars",models);
    }

    @GetMapping(value = "/all")
    public ModelAndView allCars(HttpSession session){
        List<CarsAllExportModel> models = this.carService.allCars();
        return super.view("/main/all-cars",models);
    }

    @GetMapping(value = "/{id}/parts")
    public ModelAndView carWithParts(@PathVariable("id") Long id , HttpSession session){
        CarsAllExportModel carModel = this.carService.carWithParts(id);
        if(carModel == null) {
            return super.redirect("/");
        }
        return super.view("/main/car-parts",carModel);
    }

    @GetMapping(value = "/add")
    public ModelAndView add(HttpSession session){
        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }

        return super.view("/main/cars-add");
    }

    @PostMapping(value = "/add")
    public ModelAndView addConfirm(HttpSession session, @ModelAttribute CarAddModel model){

        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }

        if(this.carService.addCar(model)){
            LogCreateModel log = new LogCreateModel();
            log.setUsername( (String) session.getAttribute("user"));
            log.setOperation("Add");
            log.setModifiedTable("Cars");
            log.setTime(LocalDateTime.now());

            this.logService.addLog(log);
        }

        return super.redirect("/cars/all");
    }
}
