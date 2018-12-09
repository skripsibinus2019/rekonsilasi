package com.rekonsiliasi.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.rekonsiliasi.dao.DepartmentDAO;
import com.rekonsiliasi.dao.LogTransactionDAO;
import com.rekonsiliasi.dao.LoginDaoImpl;
import com.rekonsiliasi.dao.StatusLogDAO;
import com.rekonsiliasi.dao.UserDao;
import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.LogTransaction;
import com.rekonsiliasi.model.StatusLog;
import com.rekonsiliasi.model.UserInfo;

@Controller
public class MainController {

    @RequestMapping(value = "/rekonsiliasi", method = RequestMethod.GET)
    public String index() {   
        return "rekonsiliasi.index";
    }
    
    @RequestMapping(value = "/log_transaction", method = RequestMethod.GET)
    public String logTransaction() {   
        return "rekonsiliasi.report";
    }
    
    @RequestMapping(value = "/approval", method = RequestMethod.GET)
    public String approval() {   
        return "rekonsiliasi.approval";
    }
    
    @Autowired
    private DepartmentDAO departmentDAO;
    
    @Autowired
    private StatusLogDAO statusLogDAO;
    
    @Autowired
    private LogTransactionDAO logTransactionDAO;
    
    @Autowired
    private UserDao userDAO;
    
    @Autowired
    private LoginDaoImpl loginDaoImpl;
	
    @RequestMapping(value = { "/Rekonsiliasi/List" }, method =  {RequestMethod.GET,RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Department getListDepartement(HttpServletRequest request, HttpServletResponse response, Model model){
    	Department dataaas = new Department();
    	List<Department> datas = new ArrayList<Department>();
    	for (Department department : departmentDAO.listDepartment()) {
			datas.add(department);
		}
    	for (Department department : departmentDAO.listDepartment2()) {
			datas.add(department);
		}
    	
    	dataaas.setList(datas);
    	Integer asd = dataaas.getList().size();
    	
    	dataaas.setRecordsFiltered(asd);
    	dataaas.setRecordsTotal(asd);
    	dataaas.setDraw("");
    	return dataaas;
    }
    
    @RequestMapping(value = { "/approval/data" }, method =  {RequestMethod.GET,RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public LogTransaction getListApproval(HttpServletRequest request, HttpServletResponse response, Model model){
    	LogTransaction dataaas = new LogTransaction();
    	List<LogTransaction> datas = new ArrayList<LogTransaction>();
    	for (LogTransaction logTrans : logTransactionDAO.allLogTransaction()) {
			datas.add(logTrans);
		}
    	dataaas.setList(datas);
    	Integer asd = dataaas.getList().size();
    	
    	dataaas.setRecordsFiltered(asd);
    	dataaas.setRecordsTotal(asd);
    	dataaas.setDraw("");
    	return dataaas;
    }
    
    @RequestMapping(value = { "/log_transaction/data" }, method =  {RequestMethod.GET,RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public LogTransaction getListLogTransaction(HttpServletRequest request, HttpServletResponse response, Model model){
    	LogTransaction dataaas = new LogTransaction();
    	List<LogTransaction> datas = new ArrayList<LogTransaction>();
    	for (LogTransaction logTrans : logTransactionDAO.listLogTransaction()) {
			datas.add(logTrans);
		}
    	dataaas.setList(datas);
    	Integer asd = dataaas.getList().size();
    	
    	dataaas.setRecordsFiltered(asd);
    	dataaas.setRecordsTotal(asd);
    	dataaas.setDraw("");
    	return dataaas;
    }
    
    @RequestMapping(value = { "/Rekonsiliasi/find" }, method =  RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UserInfo asd(HttpServletRequest request, HttpServletResponse response, Model model){
    	
    	UserInfo asd = new UserInfo();
    	
    	asd = loginDaoImpl.findUserInfo("admin");
    	
    	return asd;
    }
    
    @RequestMapping(value = "/rekonsiliasi/{id}/{table}", method = RequestMethod.GET)
    public String index(Model model, @PathVariable("id")String id, 
    		@PathVariable("table")String table ) {

    	Department data = new Department();
    	
    	if(table.equals("A")) {
    		data = departmentDAO.findDepartment(id);
    		data.setTableSource("A");
    	}else if(table.equals("B")) {
    		data = departmentDAO.findDepartment2(id);
    		data.setTableSource("B");
    	}
    	data.setNotes_lama(data.getNotes_baru());
    	model.addAttribute("data", data);
    	
    	return "rekonsiliasi.propose";
    }
    
    @RequestMapping(value = "/rekonsiliasi/{id}/{table}/confirm", method = RequestMethod.POST)
    public String indexConfirm(Model model, @PathVariable("id")String id, 
    		@PathVariable("table")String table, @ModelAttribute(value = "data") Department department,
    		@RequestParam(value = "_batal", required = false) String isBatal) {

    	department.setTableSource(table);
		model.addAttribute("data", department);
		
		
    	return "rekonsiliasi.proposeSave";
    }
    
    @RequestMapping(value = "/rekonsiliasi/{id}/{table}/save", method = RequestMethod.POST)
    public String indexSave(Model model, @PathVariable("id")String id, 
    		@PathVariable("table")String table, @ModelAttribute(value = "data") Department department, HttpServletRequest request) {

    		department.setStatus(1);
    		departmentDAO.saveRecord(department);
    		StatusLog statuslog = new StatusLog();
    		Principal principal = request.getUserPrincipal();
    		String username = principal.getName();
    		Integer userId = userDAO.getUserByUsername(username).getUserId();
    		
    		statuslog.setStatus(department.getStatus());
    		statuslog.setUserId(userId);
    		statuslog.setLogTransactionId(department.getId());
    		statuslog.setNotes(department.getNotes_baru());
    		
    		statusLogDAO.saveRecordStatusLog(statuslog);
    		
    	
    	return "redirect:/rekonsiliasi";
    }
    
    @RequestMapping(value = "/rekonsiliasi/status", method = RequestMethod.GET)
    public String showStatusRekonsiliasi(Model model, Principal principal) {
    	model.addAttribute("username", principal.getName());
        //masukin return ke page status.
    	return "";
    }
    
    @RequestMapping(value = "/rekonsiliasi/approval", method = RequestMethod.GET)
    public String approval(Model model, Principal principal) {
    	model.addAttribute("username", principal.getName());
    	
    	
        return "rekonsiliasi.approval";
    }
    
    @RequestMapping(value = "/rekonsiliasi/report", method = RequestMethod.GET)
    public String viewReportLogTransaction(Model model, Principal principal) {
    	model.addAttribute("username", principal.getName());
    	
    	return "rekonsiliasi.report";
    }
    
}