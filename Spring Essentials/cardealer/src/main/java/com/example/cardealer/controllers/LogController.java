package com.example.cardealer.controllers;

import com.example.cardealer.entities.models.dto.LogCreateModel;
import com.example.cardealer.entities.models.dto.LogSearchModel;
import com.example.cardealer.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/logs")
public class LogController extends BaseController {
    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping(value = "/all")
    public ModelAndView all(@ModelAttribute LogSearchModel model, HttpSession session){
        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }

        List<LogCreateModel> logs = new ArrayList<>();

        if(model.getUsername() != null){
            logs = this.logService.allLogsByUsername(model.getUsername());
        } else {
            logs = this.logService.allLogs();
        }

        return super.view("/main/logs-all",logs);
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(HttpSession session){
        if(session.getAttribute("user") == null){
            return super.redirect("/");
        }

        this.logService.deleteAll();

        return super.redirect("/logs/all");
    }
}
