package com.rekonsiliasi.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rekonsiliasi.dao.UserDao;
import com.rekonsiliasi.model.UserInfo;

@ControllerAdvice
public class PagesControllerAdvice {
	
	@Autowired
	private UserDao userDao;
	
    @ModelAttribute("username")
    public String username(Principal principal) {
        return principal == null ? null : principal.getName();
    }
    
    @ModelAttribute("fullname")
    public String fullName(Principal principal) {
    	UserInfo data = new UserInfo();
    	if(principal == null) {
    		return null;
    	}else {
    		data = userDao.getUserByUsername(principal.getName());
    		return data.getFirst_name() + " " + data.getLast_name();
    	}
    }
    
    @ModelAttribute("jobtitle")
    public String jobTitle(Principal principal) {
    	UserInfo data = new UserInfo();
    	if(principal == null) {
    		return null;
    	}else {
    		data = userDao.getUserByUsername(principal.getName());
    		return data.getJob_title();
    	}
    }
    
    @ModelAttribute("membersince")
    public String memberSince(Principal principal) {
    	UserInfo data = new UserInfo();
    	DateFormat dateFormat = new SimpleDateFormat("MMM. yyyy");
    	if(principal == null) {
    		return null;
    	}else {
    		data = userDao.getUserByUsername(principal.getName());
    		Date date = data.getCreatedAt();
    		return dateFormat.format(date);
    	}
    }
    
    @ModelAttribute("profilepicture")
    public String profilePicture(Principal principal) {
    	UserInfo data = new UserInfo();
    	if(principal == null) {
    		return "blank.png";
    	}else {
    		data = userDao.getUserByUsername(principal.getName());
    		if(data.getProfilePicture() == null) {
    			return "blank.png";
    		}else {
    			return data.getProfilePicture();
    		}
    		
    	}
    }

}