package com.excersise.virus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/map")
public class MapController extends BaseController {

    @GetMapping(value = {"","/"})
    public ModelAndView getMap(HttpSession session){
        return super.view("/fragments/map");
    }
}
