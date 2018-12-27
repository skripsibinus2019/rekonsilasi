package com.rekonsiliasi.controller;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.rekonsiliasi.dao.DepartmentDAO;
import com.rekonsiliasi.dao.LogTransactionDAO;
import com.rekonsiliasi.dao.LoginDaoImpl;
import com.rekonsiliasi.dao.MatchingRulesDAO;
import com.rekonsiliasi.dao.StatusLogDAO;
import com.rekonsiliasi.dao.UserDao;
import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.LogTransaction;
import com.rekonsiliasi.model.MatchingRules;
import com.rekonsiliasi.model.MatchingRulesViewModel;
import com.rekonsiliasi.model.StatusLog;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

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
    private MatchingRulesDAO matchingRulesDao;
    
    @Autowired
    private UserDao userDAO;
    
    @Autowired
    private LoginDaoImpl loginDaoImpl;
    
    private MatchingRulesViewModel ViewModel = new MatchingRulesViewModel();
	
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
    
    @RequestMapping(value = { "/approval/{id}/approve"}, method =  {RequestMethod.GET,RequestMethod.POST})
    public String approveRequest(Model model,@PathVariable("id")String idRequest) {
    	LogTransaction approveReq = new LogTransaction();
    	
    	approveReq = logTransactionDAO.getById(Integer.parseInt(idRequest));
    	
    	model.addAttribute("logTrans",approveReq);
    	return "rekonsiliasi.approvalConfirm";
    }
    
    @RequestMapping(value = { "approval/{id}/approve/process"}, method =  {RequestMethod.GET,RequestMethod.POST})
    public String approved() {
    	
    	//proses approve
    	
    	return "redirect:/approval";
    }
    
    @RequestMapping(value = { "/approval/{id}/reject"}, method =  {RequestMethod.GET,RequestMethod.POST})
    public String rejectRequest() {
    	
    	//proses reject
    	
    	return "";
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
    
    @RequestMapping(value = "/rekonsiliasi/{tableSourceId}/{table}", method = RequestMethod.GET)
    public String index(Model model, @PathVariable("tableSourceId")String tableSourceId, 
    		@PathVariable("table")String table ) {

    	LogTransaction data = new LogTransaction();
    	
    	if(table.equals("A")) {
    		data.setWsId(departmentDAO.findDepartment(tableSourceId).getWsId());
    		data.setTransactionDate(departmentDAO.findDepartment(tableSourceId).getTransactionDate());
    		data.setAmount(departmentDAO.findDepartment(tableSourceId).getAmount());
    		data.setTableSourceId(departmentDAO.findDepartment(tableSourceId).getId());
    		data.setTableSource("A");
    	}else if(table.equals("B")) {
    		data.setWsId(departmentDAO.findDepartment2(tableSourceId).getWsId());
    		data.setTransactionDate(departmentDAO.findDepartment2(tableSourceId).getTransactionDate());
    		data.setAmount(departmentDAO.findDepartment2(tableSourceId).getAmount());
    		data.setTableSourceId(departmentDAO.findDepartment2(tableSourceId).getId());
    		data.setTableSource("B");
    	}
    	model.addAttribute("data", data);
    	
    	return "rekonsiliasi.propose";
    }
    
    @RequestMapping(value = "/rekonsiliasi/{tableSourceId}/{table}/confirm", method = RequestMethod.POST)
    public String indexConfirm(Model model, @PathVariable("tableSourceId")String tableSourceId, 
    		@PathVariable("table")String table, @ModelAttribute(value = "data") LogTransaction data,
    		@RequestParam(value = "_batal", required = false) String isBatal) {

		model.addAttribute("data", data);
    	return "rekonsiliasi.proposeSave";
    }
    
    @RequestMapping(value = "/rekonsiliasi/{tableSourceId}/{table}/save", method = RequestMethod.POST)
    public String indexSave(Model model, @PathVariable("tableSourceId")String tableSourceId, 
    		@PathVariable("table")String table, @ModelAttribute(value = "data") LogTransaction data, HttpServletRequest request) {

    		logTransactionDAO.saveRecord(data);
    		
    		StatusLog statuslog = new StatusLog();
    		Principal principal = request.getUserPrincipal();
    		String username = principal.getName();
    		Integer userId = userDAO.getUserByUsername(username).getUserId();
    		
    		statuslog.setStatus(1);
    		statuslog.setUserId(userId);
    		statuslog.setLogTransactionId(data.getId());
    		statuslog.setNotes(data.getNotes());
    		
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
    
    @RequestMapping(value = "/rekonsiliasi/matching-rules", method = RequestMethod.GET)
    public String viewMatchingRules() {
    	return "rekonsiliasi.matchingRules";
    }
    
    @PostMapping(value = "/rekonsiliasi/submitmatch")
    public String matchingRulesSubmit(HttpServletRequest request, @RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
    	int[] matches = {0};
    	int biggest = 0;
		try {
			String uploadRootPath = request.getServletContext().getRealPath("\\static\\temp");
			Path path = Paths.get(uploadRootPath + "\\" + "temp.csv");
			if(ViewModel.getNowColumn() == null) {
	            byte[] bytes = file.getBytes();
	            Files.write(path, bytes);
		    	Reader reader;
				reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
				List<String[]> records = csvReader.readAll();
				//regex wsid
				Pattern pattern = Pattern.compile("^[Z90-6][a-zA-Z0-6]+$");
				for(int i = 0; i<9; i++) {
					for(int j = 0; j<records.get(i).length; j++ ) {
						Matcher matcher = pattern.matcher(records.get(i)[j]);
						if(matcher.find()) {
							matches[j]++;
						}
					}
				}
				
				for(int i=0; i<matches.length; i++) {
					if(matches[i]>biggest) {
						biggest = matches[i];
					}
				}
				
				if(biggest != 0) {
					Map<String, String> columnList = new HashMap<String, String>();
					for(int i = 0; i<matches.length; i++) {
						if(matches[i] == biggest) {
							columnList.put(Integer.toString(i), records.get(0)[i]);
						}
					}
					ViewModel.setColumnList(columnList);
					ViewModel.setNowColumn("wsid");
					model.addAttribute("data", ViewModel);
					return "rekonsiliasi.matchingRulesSubmit";
				}else {
					redirectAttributes.addFlashAttribute("message", 
	                        "There's No Match Value For WSID Column!");
					
					return "redirect:/rekonsiliasi/matching-rules";
				}
			}else if(ViewModel.getNowColumn() == "wsid") {
				Reader reader;
				reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
				List<String[]> records = csvReader.readAll();
				//regex amount
				Pattern pattern = Pattern.compile("^[0-9]+$");
				for(int i = 0; i<9; i++) {
					for(int j = 0; j<records.get(i).length; j++ ) {
						Matcher matcher = pattern.matcher(records.get(i)[j]);
						if(matcher.find()) {
							matches[j]++;
						}
					}
				}
				
				for(int i=0; i<matches.length; i++) {
					if(matches[i]>biggest) {
						biggest = matches[i];
					}
				}
				
				if(biggest != 0) {
					Map<String, String> columnList = new HashMap<String, String>();
					for(int i = 0; i<matches.length; i++) {
						if(matches[i] == biggest) {
							columnList.put(Integer.toString(i), records.get(0)[i]);
						}
					}
					ViewModel.setColumnList(columnList);
					ViewModel.setNowColumn("amount");
					model.addAttribute("data", ViewModel);
					return "rekonsiliasi.matchingRulesSubmit";
				}else {
					redirectAttributes.addFlashAttribute("message", 
	                        "There's No Match Value For Amount Column!");
					
					return "redirect:/rekonsiliasi/matching-rules";
				}
			}else if(ViewModel.getNowColumn() == "amount") {
				Reader reader;
				reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
				List<String[]> records = csvReader.readAll();
				//regex transaction date
				Pattern pattern = Pattern.compile("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
				for(int i = 0; i<9; i++) {
					for(int j = 0; j<records.get(i).length; j++ ) {
						Matcher matcher = pattern.matcher(records.get(i)[j]);
						if(matcher.find()) {
							matches[j]++;
						}
					}
				}
				
				for(int i=0; i<matches.length; i++) {
					if(matches[i]>biggest) {
						biggest = matches[i];
					}
				}
				
				if(biggest != 0) {
					Map<String, String> columnList = new HashMap<String, String>();
					for(int i = 0; i<matches.length; i++) {
						if(matches[i] == biggest) {
							columnList.put(Integer.toString(i), records.get(0)[i]);
						}
					}
					ViewModel.setColumnList(columnList);
					ViewModel.setNowColumn("transactionDate");
					model.addAttribute("data", ViewModel);
					return "rekonsiliasi.matchingRulesSubmit";
				}else {
					redirectAttributes.addFlashAttribute("message", 
	                        "There's No Match Value For Transaction Date Column!");
					
					return "redirect:/rekonsiliasi/matching-rules";
				}
			}else if(ViewModel.getNowColumn() == "transactionDate") {
				Reader reader;
				reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
				List<String[]> records = csvReader.readAll();

				for(int i = 0; i<records.size(); i++) {
					MatchingRules mr = new MatchingRules();
					mr.setWsId(records.get(i)[ViewModel.getWsid()]);
					mr.setAmount(Integer.parseInt(records.get(i)[ViewModel.getAmount()]));
					mr.setTransactionDate(records.get(i)[ViewModel.getTrasactionDate()]);
					matchingRulesDao.insertRecord(mr);
				}
				return "redirect:/rekonsiliasi/matching-rules/View";
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/rekonsiliasi/matching-rules/View";
    }
    
    @ModelAttribute("columnList")
	public Map<String, String> getColumnList() {
		
		return ViewModel.getColumnList();
	}
    
    @ModelAttribute("nowColumn")
	public String getNowColumn() {
		
		return ViewModel.getNowColumn();
	}
}