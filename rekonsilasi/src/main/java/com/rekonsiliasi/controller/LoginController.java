package com.rekonsiliasi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String doLogin(@RequestParam(value="error", required = false) String error) {
    public ModelAndView doLogin(@RequestParam(value="error", required = false) String error) {
	
    	ModelAndView model = new ModelAndView();
    	if(error != null) {
    		model.addObject("error", "The username of password is incorrect");
    	}
    	
    	model.setViewName("user.login");
    	
//    	return "user.login";
    	return model;
    }

    @RequestMapping(value= {"/","/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("user.home");
    	return model;
    }
    
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request) {
	return "redirect:login";
    }

}
