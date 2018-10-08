package com.rekonsiliasi;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String doLogin() {
	return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request) {
	return "redirect:login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView handleRequest() {
    
    return new ModelAndView("index");
    }
	
}
