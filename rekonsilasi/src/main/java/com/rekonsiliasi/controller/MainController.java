package com.rekonsiliasi.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rekonsiliasi.dao.DepartmentDAO;
import com.rekonsiliasi.model.Department;

@Controller
public class MainController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(HttpServletRequest request,Model model) {
        return "rekonsiliasi/index";
    }
    
    @Autowired
    private DepartmentDAO departmentDAO;
 
    @RequestMapping(value = { "/view" }, method = RequestMethod.GET)
    public String welcomePage(HttpServletRequest request,Model model) {
        return "index";
    }
	
    @RequestMapping(value = { "/list" }, method =  RequestMethod.GET)
    @ResponseBody
    public List<Department> getListDepartement(){
    	List<Department> temp = new ArrayList<Department>();
    	for (Department department : departmentDAO.listDepartment()) {
			temp.add(department);
		}
    	for (Department department : departmentDAO.listDepartment2()) {
			temp.add(department);
		}
    	
    	return temp;
    }
}
