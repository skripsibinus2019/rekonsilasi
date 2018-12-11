package com.rekonsiliasi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	@Qualifier("roleValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = { "user-management/role/list" }, method =  RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public UserRole getListUsers(HttpServletRequest request, HttpServletResponse response, Model model){
		return userRoleDao.listRoleDatatable();
    }
	
	@RequestMapping(value="user-management/role", method = RequestMethod.GET)
    public String all(Model model) {
    	return "user-role.index";
    }
	
	@RequestMapping(value= "user-management/role/addSubmit", method = RequestMethod.POST)
	public String addRole(@ModelAttribute("data") @Validated UserRole u, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "user-role.add";
		}
		userRoleDao.addRole(u);
		return "redirect:/user-management/role";
		
	}
	
	@RequestMapping(value="user-management/role/add", method = RequestMethod.GET)
    public String addRole(Model model) {
    	model.addAttribute("data", new UserRole());
    	return "user-role.add";
    }
	
    
	@RequestMapping(value="user-management/role/delete", method = RequestMethod.POST)
	@ResponseBody
    public void deleteRole(@RequestParam("roleId") int id){
        userRoleDao.removeUserRole(id);
    }
	
	@GetMapping("user-management/role/edit/{id}")
    public String editRoleView(Model model, @PathVariable("id") int id) {

    	model.addAttribute("data", userRoleDao.getUserRoleById(id));
    	
    	return "user-role.edit";
    }
	
	@PostMapping("user-management/role/edit/{id}")
    public String editRoleSubmit(@ModelAttribute("data") @Validated UserRole u, BindingResult bindingResult, @PathVariable("id") int id) {

		if (bindingResult.hasErrors()) {
			return "user-role.edit";
		}
		userRoleDao.updateUserRole(u, id);
		return "redirect:/user-management/role";
    }
	
	
	

}
