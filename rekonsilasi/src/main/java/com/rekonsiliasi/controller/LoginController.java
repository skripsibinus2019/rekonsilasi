package com.rekonsiliasi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class LoginController {
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String doLogin() {
	return "user/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request) {
	return "redirect:login";
    }

}
