package com.rekonsiliasi;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rekonsiliasi.dao.DepartmentDAO;
import com.rekonsiliasi.model.Department;

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
    
    @Autowired
    private DepartmentDAO departmentDAO;
 
    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    public String welcomePage(HttpServletRequest request,Model model) {
        List<Department> list = departmentDAO.listDepartment();
//        model.addAttribute("departments", list);
        request.setAttribute("departments", list);
        return "index";
    }
	
}
