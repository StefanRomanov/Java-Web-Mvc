package com.excersise.virus.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseController {

    @GetMapping(value = {"","/"})
    public ModelAndView home(HttpSession session){

        return super.view("/home");
    }
}
