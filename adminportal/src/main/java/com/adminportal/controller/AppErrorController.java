package com.adminportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AppErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView error(Model model, HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("exception", exception==null?"N/A":exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("badRequestPage");
		return modelAndView;
    }
    
    @Override
    public String getErrorPath() {
		return "/error";
	}
}
