package com.excersise.virus.controllers;
import org.springframework.web.servlet.ModelAndView;

abstract class BaseController {

    private static final String BASE_PAGE_LAYOUT = "/fragments/base-layout";
    private static final String PROPERTY_VIEW_NAME = "viewName";
    private static final String PROPERTY_VIEW_MODEL = "viewModel";
    private static final String REDIRECT_KEYWORD = "redirect:";

    public ModelAndView view(String viewName, Object viewModel){

        ModelAndView mv = new ModelAndView();
        mv.setViewName(BASE_PAGE_LAYOUT);
        mv.addObject(PROPERTY_VIEW_NAME, viewName);
        mv.addObject(PROPERTY_VIEW_MODEL, viewModel);

        return mv;
    }

    final ModelAndView view(String viewName){
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
