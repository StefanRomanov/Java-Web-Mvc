package com.excersise.virus.controllers;

import com.excersise.virus.entities.models.UserLoginModel;
import com.excersise.virus.entities.models.UserRegisterModel;
import com.excersise.virus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserController extends BaseController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return super.view("/user/register",new UserRegisterModel());
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("viewModel") UserRegisterModel model, BindingResult bindingResult){

        String test = "";
        if(bindingResult.hasErrors() || !this.userService.register(model)){
            return super.view("/user/register", model);
        }

        return super.redirect("/");
    }

    @GetMapping(value = "/login")
    public ModelAndView login(){
        return super.view("/user/login",new UserLoginModel());
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout( HttpSession session){
        session.invalidate();
        return super.redirect("/");
    }
}
