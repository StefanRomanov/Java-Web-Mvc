package com.example.cardealer.controllers;

import com.example.cardealer.entities.models.dto.PartAddDto;
import com.example.cardealer.services.PartService;
import com.example.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/parts")
public class PartController  extends BaseController{

    private PartService partService;
    private SupplierService supplierService;

    @Autowired
    public PartController(PartService partService, SupplierService supplierService) {
        this.partService = partService;
        this.supplierService = supplierService;
    }

    @GetMapping(value = {"","/"})
    public ModelAndView all(HttpSession session){
        List<PartAddDto> models = this.partService.getAllParts();
        return super.view("/main/parts-all",models);
    }

    @GetMapping(value = "/add")
    public ModelAndView add(HttpSession session){
        List<String> names = this.supplierService.getAllSupplierNames();
        return super.view("/main/parts-add",names);
    }

    @PostMapping(value = "/add")
    public ModelAndView addConfirm(@ModelAttribute PartAddDto model, HttpSession session){
        this.partService.addPart(model);

        return super.redirect("/parts");
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, HttpSession session){
        List<Object> models = new ArrayList<>();
        List<String> names = this.supplierService.getAllSupplierNames();
        PartAddDto part = this.partService.getOneById(id);

        if(part == null){
            return super.redirect("/parts");
        }
        models.add(part);
        models.add(names);

        return super.view("/main/parts-edit", models);

    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView editConfirm(@ModelAttribute PartAddDto model, @PathVariable("id") Long id , HttpSession session){
        model.setId(id);
        this.partService.addPart(model);

        return super.redirect("/parts");
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id , HttpSession session){
        List<Object> models = new ArrayList<>();
        List<String> names = this.supplierService.getAllSupplierNames();
        PartAddDto part = this.partService.getOneById(id);

        if(part == null){
            return super.redirect("/parts");
        }
        models.add(part);
        models.add(names);

        return super.view("/main/parts-delete", models);
    }

    @PostMapping(value = "/delete/{id}")
    public ModelAndView deleteConfirm(@PathVariable("id") Long id , HttpSession session){
        this.partService.deletePart(id);

        return super.redirect("/parts");
    }
}
