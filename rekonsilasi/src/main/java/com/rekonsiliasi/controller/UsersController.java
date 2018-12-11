package com.rekonsiliasi.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rekonsiliasi.dao.UserDao;
import com.rekonsiliasi.dao.UserRoleDao;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.LogTransaction;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

@Controller
public class UsersController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@RequestMapping(value = { "user-management/user/list" }, method =  RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public UserInfo getListUsers(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("draw") String draw){
		return userDao.listUsersDatatable();
    }
	
	@RequestMapping(value="user-management/user", method = RequestMethod.GET)
    public String all(Model model) {
    	return "user.index";
    }
	
	@RequestMapping(value= "user-management/user/addSubmit", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("data") UserInfo u, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "user.add";
		}
		userDao.addUser(u);
		return "redirect:/user-management/user";
		
	}
	
	@RequestMapping(value="user-management/user/delete", method = RequestMethod.POST)
	@ResponseBody
    public void deleteUser(@RequestParam("userId") int id){
        userDao.removeUser(id);
    }
	
	@RequestMapping(value="user-management/user/add", method = RequestMethod.GET)
    public String addUser(Model model) {
    	model.addAttribute("data", new UserInfo());
    	return "user.add";
    }
	
	@GetMapping("user-management/user/edit/{id}")
    public String editUserView(Model model, @PathVariable("id") int id) {
    	model.addAttribute("data", userDao.getUserById(id));
    	
    	return "user.edit";
    }
	
	@PostMapping("user-management/user/edit/{id}")
    public String editUserSubmit(@ModelAttribute("data") @Validated UserInfo u, BindingResult bindingResult, @PathVariable("id") int id) {

		if (bindingResult.hasErrors()) {
			return "user-role.edit";
		}
		userDao.updateUser(u, id);
		return "redirect:/user-management/user";
    }
	
	
	@ModelAttribute("roleList")
	public Map<String, String> getRoleList() {
		Map<String, String> roleList = new HashMap<String, String>();
		for (UserRole userRole : userRoleDao.listRole()) { 
			roleList.put(userRole.getRoleId().toString(), userRole.getRoleName());
		}
		
		return roleList;
	}
	
	@GetMapping("user-management/user/editProfile")
    public String editProfileView(Model model) {
    	
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = user.getUsername(); //get logged in username
    	model.addAttribute("data", userDao.getUserByUsername(username));
    	
    	return "user.edit_profile";
    }
	
	@PostMapping(value = "user-management/user/editProfile", consumes = "multipart/form-data")
    public String editProfileSubmit(HttpServletRequest request, @ModelAttribute("data") @Validated UserInfo u, BindingResult bindingResult, @RequestParam MultipartFile file,
            RedirectAttributes redirectAttributes) {
		UserInfo data = new UserInfo();
    	data = userDao.getUserById(u.getUserId());
        try {

            // Get the file and save it somewhere
        	String uploadRootPath = request.getServletContext().getRealPath("\\static\\user-data\\photo-profile");
            byte[] bytes = file.getBytes();
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            Path path = Paths.get(uploadRootPath + "\\" + u.getUserId() + "." + extension);
            Files.write(path, bytes);
            

            redirectAttributes.addFlashAttribute("message", 
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");
            
            if (bindingResult.hasErrors()) {
    			return "user.edit_profile";
    		}
            if(file == null) {
            	u.setProfilePicture(data.getProfilePicture());
            }else {
            	u.setProfilePicture(u.getUserId() + "." + extension);
            }
    		userDao.editProfile(u);

        } catch (IOException e) {
            e.printStackTrace();
        }

		return "redirect:/user-management/user/editProfile";
    }

}
