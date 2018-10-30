package com.rekonsiliasi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rekonsiliasi.dao.UserDao;
import com.rekonsiliasi.model.UserInfo;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value= "/user/addSubmit", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("data") UserInfo u, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "user.add";
		}
		userDao.addUser(u);
		return "";
		
	}
	
	@RequestMapping(value="/user/add", method = RequestMethod.GET)
    public String addUser(Model model) {
    	model.addAttribute("data", new UserInfo());
    	return "user.add";
    }
	
	

}
