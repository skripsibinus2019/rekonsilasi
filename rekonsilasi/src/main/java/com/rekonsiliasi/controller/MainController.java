package com.rekonsiliasi.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.rekonsiliasi.dao.DepartmentDAO;
import com.rekonsiliasi.dao.FavoriteDAO;
import com.rekonsiliasi.dao.LogTransactionDAO;
import com.rekonsiliasi.dao.LoginDaoImpl;
import com.rekonsiliasi.dao.MatchingRulesDAO;
import com.rekonsiliasi.dao.StatusLogDAO;
import com.rekonsiliasi.dao.UserDao;
import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.Favorite;
import com.rekonsiliasi.model.LogTransaction;
import com.rekonsiliasi.model.MatchingRules;
import com.rekonsiliasi.model.MatchingRulesViewModel;
import com.rekonsiliasi.model.StatusLog;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

@Controller
public class MainController {

    @Autowired
    private DepartmentDAO departmentDAO;
    
    @Autowired
    private StatusLogDAO statusLogDAO;
    
    @Autowired
    private LogTransactionDAO logTransactionDAO;
    
    @Autowired
    private MatchingRulesDAO matchingRulesDao;
    
    @Autowired
    private FavoriteDAO favoriteDAO;
    
    @Autowired
    private UserDao userDAO;
    
    @Autowired
    private LoginDaoImpl loginDaoImpl;
    
    private MatchingRulesViewModel ViewModel = new MatchingRulesViewModel();
    
    @RequestMapping(value = "/rekonsiliasi", method = RequestMethod.GET)
    public String index() {   
        return "rekonsiliasi.index";
    }
    
    @RequestMapping(value = "/log_transaction", method = RequestMethod.GET)
    public String logTransaction() {   
        return "rekonsiliasi.report";
    }
    
    @RequestMapping(value = "/log_transaction/{id}", method = RequestMethod.GET)
    public String logTransactionDetail(Model model, @PathVariable("id")Integer idRequest) { 
    	
    	List<StatusLog> detail = statusLogDAO.getStatusDAObylogTransIdList(idRequest);
    	model.addAttribute("data", detail);
    	
        return "rekonsiliasi.reportDetail";
    }
    
    @RequestMapping(value = "/approval", method = RequestMethod.GET)
    public String approval() {   
        return "rekonsiliasi.approval";
    }
	
    @RequestMapping(value = { "/Rekonsiliasi/List" }, method =  {RequestMethod.GET,RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Department getListDepartement(HttpServletRequest request, HttpServletResponse response, Model model,
    		@ModelAttribute(value = "search[value]") String search,
    		@ModelAttribute(value = "draw") String draw,
    		@ModelAttribute(value = "start") String start,
    		@ModelAttribute(value = "length") String length){
    	
    	Department dataaas = new Department();
    	List<Department> datas = new ArrayList<>();
    	Integer pageSize;
    	List<Department> filteredData1 = new ArrayList<>();
    	List<Department> filteredData2 = new ArrayList<>();
    	if(length != null) {
    		pageSize = Integer.parseInt(length);
    	}else {
    		pageSize = 0;
    	}
    	Integer skip;
    	if(start != null) {
    		skip = Integer.parseInt(start);
    	}else {
    		skip = 0;
    	}
    	Integer recordsTotal = datas.size();
    	
    	for (Department department : departmentDAO.listDepartment()) {
			datas.add(department);
		}
    	for (Department department : departmentDAO.listDepartment2()) {
			datas.add(department);
		}
    	
    	if(search!=null) {
	    	for (Department department : datas) {
	    		if(!search.equals("")) {
	        		if(department.getAmount().toString().contains(search)
	        				| department.getTableSource().contains(search)
	        				| department.getTransactionDate().toString().contains(search)
	        				| department.getWsId().contains(search)
	        				) {
	        			filteredData1.add(department);
	        		} else {
	        			continue;
	        		}
        		}else {
        			filteredData1.add(department);
        		}
			}
    	}
    	
    	Integer x = datas.size() % pageSize;
    	
    	for(int a = 0;a<pageSize;a++,skip++) {
    		try {
    			filteredData2.add(filteredData1.get(skip));
    		}catch(Exception e) {
    			// buat break
    			break;
    		}
    	}
    	
    	dataaas.setList(filteredData2);
    	Integer asd = dataaas.getList().size();
    	

    	if(search.equals("")) {
    		recordsTotal = datas.size();
    	}else {
    		recordsTotal = filteredData2.size();
    	}
    	dataaas.setRecordsFiltered(recordsTotal);
    	dataaas.setRecordsTotal(recordsTotal);
    	dataaas.setDraw(draw);
    	return dataaas;
    }
    
    @RequestMapping(value = { "/approval/{id}/approve"}, method =  {RequestMethod.GET,RequestMethod.POST})
    public String approveRequest(Model model,@PathVariable("id")String idRequest) {
    	
    	LogTransaction approveReq = logTransactionDAO.getById(Integer.parseInt(idRequest));
    	model.addAttribute("method", "approve");
    	model.addAttribute("id", idRequest);
    	model.addAttribute("data",approveReq);
    	String noted = approveReq.getNotes();
    	model.addAttribute("noted", noted);
    	approveReq.setNotes("");
    	
    	return "rekonsiliasi.approvalConfirm";
    }
    
    @RequestMapping(value = { "approval/{id}/approve/process"}, method =  {RequestMethod.GET,RequestMethod.POST})
    public String approved(Model model, @PathVariable("id")String IdRequest, 
    		@ModelAttribute(value = "data") LogTransaction data, HttpServletRequest request) {
    	
    	//proses approve
		StatusLog statuslog = new StatusLog();
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		Integer userId = userDAO.getUserByUsername(username).getUserId();
		
		statuslog.setStatus(2);// 2 = approved  lihat di model
		statuslog.setUserId(userId);
		statuslog.setLogTransactionId(data.getId());
		statuslog.setNotes(data.getNotes());
		
		statusLogDAO.saveRecordStatusLog(statuslog);
    	
    	return "redirect:/approval";
    }
    
    @RequestMapping(value = { "/approval/{id}/reject"}, method =  {RequestMethod.GET,RequestMethod.POST})
    public String rejectRequest(Model model,@PathVariable("id")String idRequest) {

    	LogTransaction approveReq = logTransactionDAO.getById(Integer.parseInt(idRequest));
    	model.addAttribute("method", "reject");
    	model.addAttribute("id", idRequest);
    	model.addAttribute("data",approveReq);
    	String noted = approveReq.getNotes();
    	model.addAttribute("noted", noted);
    	approveReq.setNotes("");
    	
    	return "rekonsiliasi.approvalConfirm";
    }
    
    @RequestMapping(value = { "/approval/{id}/reject/process"}, method =  {RequestMethod.GET,RequestMethod.POST})
    public String rejected(Model model, @PathVariable("id")String IdRequest, 
    		@ModelAttribute(value = "data") LogTransaction data, HttpServletRequest request) {
    	
    	//proses reject
		StatusLog statuslog = new StatusLog();
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		Integer userId = userDAO.getUserByUsername(username).getUserId();
		
		statuslog.setStatus(3);// 3 = reject  lihat di model
		statuslog.setUserId(userId);
		statuslog.setLogTransactionId(data.getId());
		statuslog.setNotes(data.getNotes());
		
		statusLogDAO.saveRecordStatusLog(statuslog);
    	
    	return "redirect:/approval";
    }
    
    @RequestMapping(value = { "/approval/data" }, method =  {RequestMethod.GET,RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public LogTransaction getListApproval(HttpServletRequest request, HttpServletResponse response, Model model,
    		@ModelAttribute(value = "search[value]") String search,
    		@ModelAttribute(value = "draw") String draw,
    		@ModelAttribute(value = "start") String start,
    		@ModelAttribute(value = "length") String length){
    	LogTransaction data = new LogTransaction();
    	List<LogTransaction> listOfData = new ArrayList<>();
    	Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		Integer userId = userDAO.getUserByUsername(username).getUserId();
		List<LogTransaction> filteredData2 = new ArrayList<>();
    	Integer pageSize;
    	if(length != null) {
    		pageSize = Integer.parseInt(length);
    	}else {
    		pageSize = 0;
    	}
    	Integer skip;
    	if(start != null) {
    		skip = Integer.parseInt(start);
    	}else {
    		skip = 0;
    	}
    	Integer recordsTotal ;
    	
    	if(search!=null) {
        	for (LogTransaction logTrans : logTransactionDAO.allLogTransaction()) {
        		if(favoriteDAO.getUserByUserIdlogTransId(userId, logTrans.getId()) != null) {
        			logTrans.setFav(0);
        		}else {
        			logTrans.setFav(1);
        		}
        		if(!search.equals("")) {
	        		if(logTrans.getNamaStatus().contains(search)
	        				| logTrans.getWsId().contains(search) 
	        				| logTrans.getTableSource().contains(search)
	        				| logTrans.getNotes().contains(search)
	        				| logTrans.getNamaStatus().contains(search)
	        				| logTrans.getAmount().toString().contains(search)
	        				| logTrans.getTransactionDate().toString().contains(search)
	        				) {
	        			listOfData.add(logTrans);
	        		} else {
	        			continue;
	        		}
        		}else {
        			listOfData.add(logTrans);
        		}
    		}
    	}
    	
    	Integer x = listOfData.size() % pageSize;
    	
    	for(int a = 0;a<pageSize;a++,skip++) {
    		try {
    			filteredData2.add(listOfData.get(skip));
    		}catch(Exception e) {
    			// buat break
    			break;
    		}
    	}
    	
    	data.setList(filteredData2);
    	Integer asd = data.getList().size();
    	

    	if(search.equals("")) {
    		recordsTotal = listOfData.size();
    	}else {
    		recordsTotal = filteredData2.size();
    	}
    	
    	data.setRecordsFiltered(recordsTotal);
    	data.setRecordsTotal(recordsTotal);
    	data.setDraw(draw);
    	return data;
    }
    

    @RequestMapping(value = { "/log_transaction/data" }, method =  {RequestMethod.GET,RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public LogTransaction getListLogTransaction(HttpServletRequest request, HttpServletResponse response, Model model,
    		@ModelAttribute(value = "search[value]") String search,
    		@ModelAttribute(value = "draw") String draw,
    		@ModelAttribute(value = "start") String start,
    		@ModelAttribute(value = "length") String length){
    	
    	LogTransaction data = new LogTransaction();
    	List<LogTransaction> listOfData = new ArrayList<>();
    	Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		Integer userId = userDAO.getUserByUsername(username).getUserId();
		List<LogTransaction> filteredData2 = new ArrayList<>();
    	Integer pageSize;
    	if(length != null) {
    		pageSize = Integer.parseInt(length);
    	}else {
    		pageSize = 0;
    	}
    	Integer skip;
    	if(start != null) {
    		skip = Integer.parseInt(start);
    	}else {
    		skip = 0;
    	}
    	Integer recordsTotal ;
    	
    	if(search!=null) {
        	for (LogTransaction logTrans : logTransactionDAO.listLogTransaction()) {
        		if(favoriteDAO.getUserByUserIdlogTransId(userId, logTrans.getId()) != null) {
        			logTrans.setFav(0);
        		}else {
        			logTrans.setFav(1);
        		}
        		if(!search.equals("")) {
	        		if(logTrans.getNamaStatus().contains(search)
	        				| logTrans.getWsId().contains(search) 
	        				| logTrans.getTableSource().contains(search)
	        				| logTrans.getNotes().contains(search)
	        				| logTrans.getNamaStatus().contains(search)
	        				| logTrans.getAmount().toString().contains(search)
	        				| logTrans.getTransactionDate().toString().contains(search)
	        				) {
	        			listOfData.add(logTrans);
	        		} else {
	        			continue;
	        		}
        		}else {
        			listOfData.add(logTrans);
        		}
    		}
    	}
    	
    	Integer x = listOfData.size() % pageSize;
    	
    	for(int a = 0;a<pageSize;a++,skip++) {
    		try {
    			filteredData2.add(listOfData.get(skip));
    		}catch(Exception e) {
    			// buat break
    			break;
    		}
    	}
    	
    	data.setList(filteredData2);
    	Integer asd = data.getList().size();
    	

    	if(search.equals("")) {
    		recordsTotal = listOfData.size();
    	}else {
    		recordsTotal = filteredData2.size();
    	}
    	
    	data.setRecordsFiltered(recordsTotal);
    	data.setRecordsTotal(recordsTotal);
    	data.setDraw(draw);
    	return data;
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
    	}else if(table.equals("CSV")) {
    		data.setWsId(departmentDAO.findDepartment3(tableSourceId).getWsId());
    		data.setTransactionDate(departmentDAO.findDepartment3(tableSourceId).getTransactionDate());
    		data.setAmount(departmentDAO.findDepartment3(tableSourceId).getAmount());
    		data.setTableSourceId(departmentDAO.findDepartment3(tableSourceId).getId());
    		data.setTableSource("CSV");
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
    
    
    @RequestMapping(value = "/matching-rules/upload", method = RequestMethod.GET)
    public String viewMatchingRules() {
    	return "rekonsiliasi.matchingRules";
    }
    
    @PostMapping(value = "/matching-rules/submitMatchingUpload")
    public String uploadMatchingCSV(HttpServletRequest request, @RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
		try {
			String uploadRootPath = request.getServletContext().getRealPath("\\static\\temp");
			Path path = Paths.get(uploadRootPath + "\\" + "temp.csv");
			byte[] bytes = file.getBytes();
            Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ViewModel.setNowColumn(null);
		return matchingRulesSubmit(request, model, redirectAttributes, ViewModel);
    }
    
    @PostMapping(value = "/matching-rules/submitMatching")
    public String matchingRulesSubmit(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes, @ModelAttribute("data") MatchingRulesViewModel data) {
    	ViewModel.setNowColumn(data.getNowColumn());
		try {
			String uploadRootPath = request.getServletContext().getRealPath("\\static\\temp");
			Path path = Paths.get(uploadRootPath + "\\" + "temp.csv");
			if(ViewModel.getNowColumn() == null) {
		    	Reader reader;
				reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReaderBuilder(reader).build();
				List<String[]> records = csvReader.readAll();
				int[] matches = new int[records.get(0).length];
		    	int biggest = 0;
				//regex wsid
				Pattern pattern = Pattern.compile("^(Z|9|[0-6])([a-zA-Z0-6]+)$");
				for(int i = 1; i<10; i++) {
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
					model.addAttribute("columnList", columnList);
					model.addAttribute("nowColumn", "wsid");
					model.addAttribute("data", ViewModel);
					return "rekonsiliasi.matchingRulesSubmit";
				}else {
					redirectAttributes.addFlashAttribute("message", 
	                        "There's No Match Value For WSID Column!");
					
					return "redirect:/matching-rules/upload";
				}
			}else if(ViewModel.getNowColumn().equals("wsid")) {
				ViewModel.setWsid(data.getWsid());
				Reader reader;
				reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReaderBuilder(reader).build();
				List<String[]> records = csvReader.readAll();
				int[] matches = new int[records.get(0).length];
		    	int biggest = 0;
				//regex amount
				Pattern pattern = Pattern.compile("^[0-9]+$");
				for(int i = 1; i<10; i++) {
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
					model.addAttribute("columnList", columnList);
					model.addAttribute("nowColumn", "amount");
					model.addAttribute("data", ViewModel);
					return "rekonsiliasi.matchingRulesSubmit";
				}else {
					redirectAttributes.addFlashAttribute("message", 
	                        "There's No Match Value For Amount Column!");
					
					return "redirect:/matching-rules/upload";
				}
			}else if(ViewModel.getNowColumn().equals("amount")) {
				ViewModel.setAmount(data.getAmount());
				Reader reader;
				reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReaderBuilder(reader).build();
				List<String[]> records = csvReader.readAll();
				int[] matches = new int[records.get(0).length];
		    	int biggest = 0;
				//regex transaction date
				Pattern pattern = Pattern.compile("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
				for(int i = 1; i<10; i++) {
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
					model.addAttribute("columnList", columnList);
					model.addAttribute("nowColumn", "transactionDate");
					model.addAttribute("data", ViewModel);
					return "rekonsiliasi.matchingRulesSubmit";
				}else {
					redirectAttributes.addFlashAttribute("message", 
	                        "There's No Match Value For Transaction Date Column!");
					
					return "redirect:/matching-rules/upload";
				}
			}else if(ViewModel.getNowColumn().equals("transactionDate")) {
				ViewModel.setTransactionDate(data.getTransactionDate());
				Reader reader;
				reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
				List<String[]> records = csvReader.readAll();
				matchingRulesDao.removeAll();
				for(int i = 0; i<records.size(); i++) {
					
					MatchingRules mr = new MatchingRules();
					mr.setWsId(records.get(i)[ViewModel.getWsid()]);
					mr.setAmount(Integer.parseInt(records.get(i)[ViewModel.getAmount()]));
					mr.setTransactionDate(records.get(i)[ViewModel.getTransactionDate()]);
					matchingRulesDao.insertRecord(mr);
				}
				return "redirect:/matching-rules/reconciliation";
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/matching-rules/reconciliation";
	}
    
    @ModelAttribute("nowColumn")
	public String getNowColumn() {
		
		return ViewModel.getNowColumn();
	}
    
    @RequestMapping(value = "/matching-rules/reconciliation", method = RequestMethod.GET)
    public String viewMatchingRulesList() {
    	return "rekonsiliasi.matchingRulesView";
    }
    
    @RequestMapping(value = { "/matching-rules/List" }, method =  {RequestMethod.GET,RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Department getListMatchingRules(HttpServletRequest request, HttpServletResponse response, Model model){
    	Department dataaas = new Department();
    	List<Department> datas = new ArrayList<Department>();
    	for (Department department : departmentDAO.listDepartment3()) {
			datas.add(department);
		}
    	
    	dataaas.setList(datas);
    	Integer asd = dataaas.getList().size();
    	
    	dataaas.setRecordsFiltered(asd);
    	dataaas.setRecordsTotal(asd);
    	dataaas.setDraw("");
    	return dataaas;
    }
    
    @RequestMapping(value = { "/activity/list" }, method =  RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public StatusLog getListActivity(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("draw") String draw){
		return statusLogDAO.listStatusLog();
    }
    
    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public String activityView() {   
        return "activity.index";
    }
    
    @RequestMapping(value = "/favorite/{id}", method = RequestMethod.GET)
    public String addToFavorite(HttpServletRequest request, @PathVariable("id")Integer idRequest) { 
    	
    	Favorite favorite = new Favorite();
    	favorite.setLogTransId(idRequest);
    	Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		Integer userId = userDAO.getUserByUsername(username).getUserId();
    	favorite.setUserId(userId);
    	favoriteDAO.addToFavorite(favorite);
    	
        return "redirect:/log_transaction";
    }
    
    @RequestMapping(value = "/favoriteDelete/{id}", method = RequestMethod.GET)
    public String deleteFavorite(HttpServletRequest request, @PathVariable("id")Integer idRequest) { 
    	favoriteDAO.removeFavorite(idRequest);
        return "redirect:/dashboard";
    }
    
    @RequestMapping(value = "/export/log_transaction", method = RequestMethod.GET)
    public String exportLogTrans() { 
    	
        return "export.index";
    }

	@RequestMapping(value = "export/log_transaction/submit", method = RequestMethod.POST)
    public String SubmitExport(HttpServletRequest request, @RequestParam("type") int type, @RequestParam("From") String from, @RequestParam("To") String to) throws IOException, DocumentException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Report");
		
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

    	    // Create a Row
    	Row headerRow = sheet.createRow(0);
    	Cell cell = headerRow.createCell(0);
        cell.setCellValue("WSID");
        cell.setCellStyle(headerCellStyle);
        
        cell = headerRow.createCell(1);
        cell.setCellValue("AMOUNT");
        cell.setCellStyle(headerCellStyle);
        
        cell = headerRow.createCell(2);
        cell.setCellValue("TRANSACTION DATE");
        cell.setCellStyle(headerCellStyle);
        
        cell = headerRow.createCell(3);
        cell.setCellValue("TABLE SOURCE");
        cell.setCellStyle(headerCellStyle);
        
        cell = headerRow.createCell(4);
        cell.setCellValue("NOTES");
        cell.setCellStyle(headerCellStyle);
        
        cell = headerRow.createCell(5);
        cell.setCellValue("STATUS");
        cell.setCellStyle(headerCellStyle);
        
        int rowNum = 1;
        for (LogTransaction logTrans : logTransactionDAO.listLogTransactionByDate(from, to)) {
            Row row = sheet.createRow(rowNum++);
            if(logTrans.getWsId() != null) {
            	row.createCell(0).setCellValue(logTrans.getWsId());
            }
            
            if(logTrans.getAmount() != null) {
            	row.createCell(1).setCellValue(logTrans.getAmount());
            }
            
            if(logTrans.getTransactionDate() != null) {
            	row.createCell(2).setCellValue(logTrans.getTransactionDate());
            }
            
            if(logTrans.getTableSource() != null) {
            row.createCell(3).setCellValue(logTrans.getTableSource());
            }
            
            if(logTrans.getNotes() != null) {
            row.createCell(4).setCellValue(logTrans.getNotes());
            }
            
            if(logTrans.getNamaStatus() != null) {
            row.createCell(5).setCellValue(logTrans.getNamaStatus());
            }
      }

        // Resize all columns to fit the content size
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
          sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut;
		String uploadRootPath = request.getServletContext().getRealPath("\\static\\temp");
		fileOut = new FileOutputStream(uploadRootPath + "\\" + "export.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		
		FileInputStream input_document = new FileInputStream(new File(uploadRootPath + "\\" + "export.xlsx"));
        // Read workbook into HSSFWorkbook
        XSSFWorkbook my_xls_workbook = new XSSFWorkbook(input_document); 
        // Read worksheet into HSSFSheet
        XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); 
        // To iterate over the rows
        Iterator<Row> rowIterator = my_worksheet.iterator();
        //We will create output PDF document objects at this point
        Document iText_xls_2_pdf = new Document();
        PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream(uploadRootPath + "\\" + "export.pdf"));
        iText_xls_2_pdf.open();
        //we have two columns in the Excel sheet, so we create a PDF table with two columns                
        PdfPTable my_table = new PdfPTable(6);
        //cell object to capture data
        PdfPCell table_cell;
        //Loop through rows.
        while(rowIterator.hasNext()) {
                Row row = rowIterator.next(); 
                Iterator<Cell> cellIterator = row.cellIterator();
                        while(cellIterator.hasNext()) {
                                Cell cells = cellIterator.next(); //Fetch CELL
                                switch(cells.getCellType()) { //Identify CELL type
                                        
                                case Cell.CELL_TYPE_STRING:
                                	//Push the data from Excel to PDF Cell
                                    table_cell=new PdfPCell(new Phrase(cell.getStringCellValue()));
                                    //feel free to move the code below to suit to your needs
                                    my_table.addCell(table_cell);
                                   break;
                                }
                                //next line
                        }
        
        }
        
        //Finally add the table to PDF document
        iText_xls_2_pdf.add(my_table);                       
        iText_xls_2_pdf.close();                
        //we created our pdf file..
        input_document.close(); //close xlsx

        return "redirect:/static/temp/export.xlsx";
    }
    
    
}