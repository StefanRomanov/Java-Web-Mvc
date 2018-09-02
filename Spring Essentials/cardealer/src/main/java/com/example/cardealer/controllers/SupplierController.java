package com.example.cardealer.controllers;

import com.example.cardealer.entities.models.dto.LogCreateModel;
import com.example.cardealer.entities.models.dto.SupplierAddModel;
import com.example.cardealer.entities.models.views.SupplierExportModel;
import com.example.cardealer.services.LogService;
import com.example.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/suppliers")
public class SupplierController extends BaseController {

    private SupplierService supplierService;
    private LogService logService;

    @Autowired
    public SupplierController(SupplierService supplierService, LogService logService) {
        this.supplierService = supplierService;
        this.logService = logService;
    }

    @GetMapping(value = "/{isImporter}")
    public ModelAndView allSuppliers(@PathVariable("isImporter") String isImporter, HttpSession session){
        List<SupplierExportModel> models = new ArrayList<>();
        if(isImporter.equals("local")){
            models = this.supplierService.suppliersByIsImporter(false);
        } else if (isImporter.equals("importers")){
            models = this.supplierService.suppliersByIsImporter(true);
        } else if(isImporter.equals("all")){
            models = this.supplierService.allSuppliers();
        } else {
            return super.redirect("/");
        }

        return super.view("/main/suppliers-all",models);
    }

    @GetMapping(value = "/add")
    public ModelAndView add(HttpSession session){
        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }
        return super.view("/main/suppliers-add");
    }

    @PostMapping(value = "/add")
    public ModelAndView addConfirm(HttpSession session, @ModelAttribute SupplierAddModel model){
        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }

        if(this.supplierService.addSupplier(model)){
            LogCreateModel log = new LogCreateModel();
            log.setUsername( (String) session.getAttribute("user"));
            log.setOperation("Add");
            log.setModifiedTable("Suppliers");
            log.setTime(LocalDateTime.now());

            this.logService.addLog(log);
        }

        return super.redirect("/suppliers/all");
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, HttpSession session){
        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }

        SupplierAddModel model = this.supplierService.getOne(id);
        if(model == null){
            return super.redirect("/suppliers/all");
        }

        return super.view("/main/suppliers-edit",model);
    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView editConfirm(HttpSession session, @ModelAttribute SupplierAddModel model, @PathVariable("id") Long id){
        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }

        model.setId(id);
        if(this.supplierService.addSupplier(model)){
            LogCreateModel log = new LogCreateModel();
            log.setUsername( (String) session.getAttribute("user"));
            log.setOperation("Edit");
            log.setModifiedTable("Suppliers");
            log.setTime(LocalDateTime.now());

            this.logService.addLog(log);
        }

        return super.redirect("/suppliers/all");
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(HttpSession session, @PathVariable("id") Long id){
        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }

        this.supplierService.delete(id);

        LogCreateModel log = new LogCreateModel();
        log.setUsername( (String) session.getAttribute("user"));
        log.setOperation("Delete");
        log.setModifiedTable("Suppliers");
        log.setTime(LocalDateTime.now());

        this.logService.addLog(log);

        return super.redirect("/suppliers/all");
    }

}
