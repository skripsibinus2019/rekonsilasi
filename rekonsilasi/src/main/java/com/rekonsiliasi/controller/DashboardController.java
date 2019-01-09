package com.rekonsiliasi.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rekonsiliasi.dao.DashboardDAO;
import com.rekonsiliasi.dao.FavoriteDAO;
import com.rekonsiliasi.dao.UserDao;

@Controller
public class DashboardController {
	
	@Autowired
	private DashboardDAO dashboardDAO;
	
	@Autowired
	private FavoriteDAO favoriteDAO;
	
	@Autowired
	private UserDao userDAO;
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) { 
		model.addAttribute("data", dashboardDAO.getDashboardData());
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		Integer userId = userDAO.getUserByUsername(username).getUserId();
		model.addAttribute("fav", favoriteDAO.getUserByUserId(userId));
        return "dashboard.index";
    }
}
