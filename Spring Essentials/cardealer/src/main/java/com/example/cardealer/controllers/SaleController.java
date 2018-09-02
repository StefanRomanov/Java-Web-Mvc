package com.example.cardealer.controllers;

import com.example.cardealer.entities.models.dto.LogCreateModel;
import com.example.cardealer.entities.models.dto.SaleAddDto;
import com.example.cardealer.entities.models.views.*;
import com.example.cardealer.services.CarService;
import com.example.cardealer.services.CustomerService;
import com.example.cardealer.services.LogService;
import com.example.cardealer.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/sales")
public class SaleController extends BaseController{

    private SaleService saleService;
    private CarService carService;
    private CustomerService customerService;
    private LogService logService;

    @Autowired
    public SaleController(SaleService saleService, CarService carService, CustomerService customerService, LogService logService) {
        this.saleService = saleService;
        this.carService = carService;
        this.customerService = customerService;
        this.logService = logService;
    }

    @GetMapping(value = {"","/"})
    public ModelAndView allSales(HttpSession session){
        List<SaleExportModel> models = this.saleService.allSales();

        return super.view("/main/all-sales",models);
    }

    @GetMapping(value = "/{id}")
    public ModelAndView sale(@PathVariable("id") Long id, HttpSession session){
        SaleExportModel model = this.saleService.saleById(id);
        if(model == null){
            return super.redirect("/");
        }

        return super.view("/main/all-sales",model);
    }

    @GetMapping(value = "/discounted")
    public ModelAndView allDiscountedSales(HttpSession session){
        List<SaleExportModel> models = this.saleService.discountedSales();

        return super.view("/main/all-sales",models);
    }

    @GetMapping(value = "/discounted/{percent}")
    public ModelAndView allDiscountedByPercent(@PathVariable("percent") String percent , HttpSession session){
        List<SaleExportModel> models = this.saleService.discountedByConcretePercent(percent);

        return super.view("/main/all-sales",models);
    }

    @GetMapping(value = "/add")
    public ModelAndView add(HttpSession session){
        if(session.getAttribute("user")== null){
            return super.redirect("/");
        }
        List<Object> models = new ArrayList<>();
        List<CarsAllExportModel> cars = this.carService.allCars();
        List<CustomerAscDescModel> customers = this.customerService.getAllAscending();
        models.add(customers);
        models.add(cars);

        return super.view("/main/sales-add",models);
    }

    @PostMapping(value = "/add")
    public ModelAndView addReview(@ModelAttribute SaleAddDto model, HttpSession session){
        if(session.getAttribute("user")== null){
            return super.redirect("/");
        }

        SaleReviewView sale = this.saleService.saleForReview(model);

        return super.view("/main/sales-review",sale);

    }

    @PostMapping(value = "/add/confirm")
    public ModelAndView addConfirm(HttpSession session, @ModelAttribute("viewModel") SaleAddDto model){
        if(session.getAttribute("user")== null){
            return super.redirect("/");
        }

        if(this.saleService.addSale(model)){
            LogCreateModel log = new LogCreateModel();
            log.setUsername( (String) session.getAttribute("user"));
            log.setOperation("Add");
            log.setModifiedTable("Sales");
            log.setTime(LocalDateTime.now());

            this.logService.addLog(log);
        };

        return super.redirect("/sales/");
    }
}
