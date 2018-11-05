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
import com.rekonsiliasi.dao.UserRoleDao;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

@Controller
public class RoleController {
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@RequestMapping(value = { "user-management/role/list" }, method =  RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public UserRole getListUsers(HttpServletRequest request, HttpServletResponse response, Model model){
		UserRole dataaas = new UserRole();
    	List<UserRole> datas = new ArrayList<UserRole>();
    	
    	for (UserRole userRole : userRoleDao.listRole()) {
			datas.add(userRole);
		}
    	
    	dataaas.setList(datas);
    	Integer asd = dataaas.getList().size();
    	
    	dataaas.setRecordsFiltered(asd);
    	dataaas.setRecordsTotal(asd);
    	dataaas.setDraw("");
    	return dataaas;
    }
	
	@RequestMapping(value="user-management/role", method = RequestMethod.GET)
    public String all(Model model) {
    	return "user-role.index";
    }
	
	@RequestMapping(value= "user-management/role/addSubmit", method = RequestMethod.POST)
	public String addRole(@ModelAttribute("data") UserRole u, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "user-role.add";
		}
		userRoleDao.addRole(u);
		return "redirect:/role";
		
	}
	
	@RequestMapping(value="user-management/role/add", method = RequestMethod.GET)
    public String addRole(Model model) {
    	model.addAttribute("data", new UserRole());
    	return "user-role.add";
    }
	
	

}
