package com.example.cardealer.controllers;

import org.springframework.web.servlet.ModelAndView;

abstract class BaseController {
    private static final String NAVBAR_VIEW = "/fragments/navbar-base";
    private static final String PROPERTY_VIEW_NAME = "viewName";
    private static final String PROPERTY_VIEW_MODEL = "viewModel";
    private static final String REDIRECT_KEYWORD = "redirect:";

    public ModelAndView view(String viewName, Object model){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(NAVBAR_VIEW);
        mv.addObject(PROPERTY_VIEW_NAME,viewName);
        mv.addObject(PROPERTY_VIEW_MODEL,model);

        return mv;
    }

    public ModelAndView view(String viewName){
        return this.view(viewName,null);
    }

    final ModelAndView redirect(String redirectUrl,Object viewModel) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(PROPERTY_VIEW_MODEL, viewModel);
        modelAndView.setViewName(REDIRECT_KEYWORD + redirectUrl);
        return modelAndView;
    }

    final ModelAndView redirect(String redirectUrl){
        return this.redirect(redirectUrl,null);
    }
}
