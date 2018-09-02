package com.excersise.virus.controllers;

import com.excersise.virus.entities.annotations.PreAuthenticate;
import com.excersise.virus.entities.enums.Magnitude;
import com.excersise.virus.entities.enums.Mutation;
import com.excersise.virus.entities.models.AddVirusModel;
import com.excersise.virus.services.CapitalService;
import com.excersise.virus.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping(value = "/viruses")
public class VirusController extends BaseController{

    private VirusService virusService;
    private CapitalService capitalService;

    @Autowired
    public VirusController(VirusService service, CapitalService capitalService) {
        this.virusService = service;
        this.capitalService = capitalService;
    }

    @GetMapping(value = {"","/"})
    @PreAuthenticate(loggedIn = true)
    private ModelAndView all(HttpSession session){
        return super.view("/virus/all",this.virusService.findAll());
    }

    @GetMapping(value = "/add")
    @PreAuthenticate(loggedIn = true, inRole = "ADMIN")
    private ModelAndView add(HttpSession session){
        return super.view("/virus/add",this.loadDataToViewModel(new AddVirusModel()));
    }

    @PostMapping(value = "/add")
    @PreAuthenticate(loggedIn = true, inRole = "ADMIN")
    private ModelAndView addConfirm(@Valid @ModelAttribute("viewModel") AddVirusModel addVirusModel, BindingResult bindingResult,HttpSession session){

        if (bindingResult.hasErrors()) {
            this.loadDataToViewModel(addVirusModel);
            return super.view("/virus/add", addVirusModel);
        }

        if(!this.virusService.addVirus(addVirusModel)){
            this.loadDataToViewModel(addVirusModel);
            return super.view("/virus/add", addVirusModel);
        }

        return super.redirect("/viruses/");
    }

    @GetMapping(value = "/edit/{virusId}")
    @PreAuthenticate(loggedIn = true, inRole = "ADMIN")
    private ModelAndView edit(@PathVariable Long virusId, HttpSession session){
        AddVirusModel addVirusModel =
                this.loadDataToViewModel(this.virusService.getById(virusId), virusId);

        if (addVirusModel == null) {
            return super.redirect("/viruses");
        }

        return super.view("/virus/edit", addVirusModel);
    }

    @PostMapping("/edit/{virusId}")
    @PreAuthenticate(loggedIn = true, inRole = "ADMIN")
    public ModelAndView editPost(@PathVariable Long virusId,
                                 @Valid @ModelAttribute("viewModel") AddVirusModel addVirusModel,
                                 BindingResult bindingResult,
                                 HttpSession session) {
        if (addVirusModel == null) {
            return super.redirect("/viruses");
        }

       addVirusModel.setId(virusId);

        if (bindingResult.hasErrors() || !this.virusService.updateVirus(addVirusModel)) {
            this.loadDataToViewModel(addVirusModel, virusId);
            return super.view("/virus/edit", addVirusModel);
        }

        return super.redirect("/viruses");
    }


    @GetMapping("/delete/{virusId}")
    @PreAuthenticate(loggedIn = true, inRole = "ADMIN")
    public ModelAndView deleteGet(@PathVariable Long virusId, HttpSession session) {
        this.virusService.deleteById(virusId);
        return super.redirect("/viruses");
    }



    private AddVirusModel loadDataToViewModel(AddVirusModel addVirusModel) {
        return this.loadDataToViewModel(addVirusModel, null);
    }

    private AddVirusModel loadDataToViewModel(AddVirusModel addVirusModel, Long virusId) {
        if (addVirusModel == null) {
            return null;
        }

        addVirusModel.setCapitals(this.capitalService.simpleCapitals());

        addVirusModel.setMutations(
                Stream.of(Mutation.values())
                        .map(Enum::name)
                        .collect(Collectors.toUnmodifiableList()));

        addVirusModel.setMagnitudes(
                Stream.of(Magnitude.values())
                        .map(Enum::name)
                        .collect(Collectors.toUnmodifiableList()));

        addVirusModel.setId(virusId);

        return addVirusModel;
    }
}
