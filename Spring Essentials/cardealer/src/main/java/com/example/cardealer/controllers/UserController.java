package com.example.cardealer.controllers;

import com.example.cardealer.entities.models.dto.UserRegisterModel;
import com.example.cardealer.repos.UserRepository;
import com.example.cardealer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/users")
public class UserController extends BaseController{

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public ModelAndView register(){
       return super.view("/main/users-register");
    }

    @PostMapping(value = "/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterModel model, HttpSession session){
        if(this.userService.addUser(model)){
            return super.redirect("/users/login");
        }

        return super.redirect("/users/register");
    }

    @GetMapping(value = "/login")
    public ModelAndView login(){
        return super.view("/main/users-login");
    }

    @PostMapping(value = "/login")
    public ModelAndView loginConfirm(@ModelAttribute UserRegisterModel model, HttpSession session){
        if(this.userService.confrimLogin(model)){
            session.setAttribute("user",model.getUsername());
            return super.redirect("/");
        }

        return super.redirect("/users/login");
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        if(session.getAttribute("user") != null){
            session.invalidate();
        }

        return super.redirect("/");
    }
}
