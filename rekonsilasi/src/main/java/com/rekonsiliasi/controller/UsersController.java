package com.rekonsiliasi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rekonsiliasi.dao.UserDao;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

@Controller
public class UsersController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = { "/user/list" }, method =  RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public UserInfo getListUsers(HttpServletRequest request, HttpServletResponse response, Model model){
		UserInfo dataaas = new UserInfo();
    	List<UserInfo> datas = new ArrayList<UserInfo>();
    	
    	for (UserInfo userInfo : userDao.listUsers()) {
			datas.add(userInfo);
		}
    	
    	dataaas.setList(datas);
    	Integer asd = dataaas.getList().size();
    	
    	dataaas.setRecordsFiltered(asd);
    	dataaas.setRecordsTotal(asd);
    	dataaas.setDraw("");
    	return dataaas;
    }
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
    public String all(Model model) {
    	return "user.index";
    }
	
	@RequestMapping(value= "/user/addSubmit", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("data") UserInfo u, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "user.add";
		}
		userDao.addUser(u);
		return "redirect:/user";
		
	}
	
	@RequestMapping(value="/user/add", method = RequestMethod.GET)
    public String addUser(Model model) {
    	model.addAttribute("data", new UserInfo());
    	return "user.add";
    }
	
	
	@RequestMapping(value="/user/assign/role", method = RequestMethod.GET)
    public String asignRole(Model model) {
    	model.addAttribute("data", new UserRole());
    	return "user.assign.role";
    }
	
	

}
