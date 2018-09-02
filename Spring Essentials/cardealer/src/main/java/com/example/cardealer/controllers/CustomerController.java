package com.example.cardealer.controllers;

import com.example.cardealer.entities.models.dto.CustomerAddDto;
import com.example.cardealer.entities.models.views.CustomerAscDescModel;
import com.example.cardealer.entities.models.views.CustomerInfoModel;
import com.example.cardealer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/customers")
public class CustomerController  extends BaseController{

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all/{condition}")
    public ModelAndView allAscending(@PathVariable("condition") String condition , HttpSession session){
        List<CustomerAscDescModel> models = new ArrayList<>();

        if(condition.equals("ascending")){
            models = this.customerService.getAllAscending();
        } else if (condition.equals("descending")){
            models = this.customerService.getAllDescending();
        } else {
            return super.redirect("/");
        }

        return super.view("/main/customers-all",models);
    }

    @GetMapping(value = "/{id}")
    public ModelAndView customerInfo(@PathVariable("id") Long id , HttpSession session){
        CustomerInfoModel model = this.customerService.customerById(id);
        if(model == null){
           return super.redirect("/");
        }

        return super.view("/main/customer-info",model);
    }

    @GetMapping(value = "/add")
    public ModelAndView addCustomer(){
        return super.view("/main/customer-add");
    }

    @PostMapping(value = "/add")
    public ModelAndView addCustomerConfirm(@ModelAttribute CustomerAddDto model , HttpSession session){
        Long result = this.customerService.customerAdd(model);
        if(result != null){
           return super.redirect("/customers/"+result);
        }
        return super.redirect("/customers/add");
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id , HttpSession session){
        CustomerAddDto model = this.customerService.getCustomerForEdit(id);
        if(model == null){
            return super.redirect("/");
        }

        return super.view("/main/customer-edit",model);
    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView editConfirm(@PathVariable("id") Long id, @ModelAttribute CustomerAddDto model , HttpSession session){
        model.setId(id);
        Long result = this.customerService.customerAdd(model);
        if(result != null){
            return super.redirect("/customers/"+result);
        }

        return super.redirect("/customers/edit/"+ id);
    }
}
