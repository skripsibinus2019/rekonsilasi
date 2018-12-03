package com.rekonsiliasi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogTransaction {
 
    private Integer id;
    private String wsId;
    private Integer amount;
    private String transactionDate;
    private List<LogTransaction> list;
    private String draw;
    private Integer recordsFiltered;
    private Integer recordsTotal;
    private String tableSourceA;
    private String tableSourceB;
    private String tableSourceId;
    private Integer status;
    private String namaStatus;
    
    
    
    public String getNamaStatus() {
		return namaStatus;
	}

	public void setNamaStatus(String namaStatus) {
		this.namaStatus = namaStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonProperty(value="draw")
    public String getDraw() {
		return draw;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	@JsonProperty(value="recordsFiltered")
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	@JsonProperty(value="recordsTotal")
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	



    
    @JsonProperty(value="data")
    public List<LogTransaction> getList() {
		return list;
	}

	public void setList(List<LogTransaction> list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWsId() {
		return wsId;
	}

	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getTableSourceA() {
		return tableSourceA;
	}

	public void setTableSourceA(String tableSourceA) {
		this.tableSourceA = tableSourceA;
	}

	public String getTableSourceB() {
		return tableSourceB;
	}

	public void setTableSourceB(String tableSourceB) {
		this.tableSourceB = tableSourceB;
	}

	public String getTableSourceId() {
		return tableSourceId;
	}

	public void setTableSourceId(String tableSourceId) {
		this.tableSourceId = tableSourceId;
	}

	public LogTransaction() {
		 
    }

	public LogTransaction(Integer id, String wsId, Integer amount, String transactionDate, List<LogTransaction> list,
			String draw, Integer recordsFiltered, Integer recordsTotal, String tableSourceA, String tableSourceB,
			String tableSourceId) {
		super();
		this.id = id;
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.list = list;
		this.draw = draw;
		this.recordsFiltered = recordsFiltered;
		this.recordsTotal = recordsTotal;
		this.tableSourceA = tableSourceA;
		this.tableSourceB = tableSourceB;
		this.tableSourceId = tableSourceId;
	}

	public LogTransaction(String wsId, Integer amount, String transactionDate, String tableSourceA, String tableSourceB,
			String namaStatus) {
		super();
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.tableSourceA = tableSourceA;
		this.tableSourceB = tableSourceB;
		this.namaStatus = namaStatus;
	}

	public String namaStatus(Integer status) {
		
		if(status == 1) {
			return "Pending";
		}else if(status == 2) {
			return "Approved";
		}else if(status == 3) {
			return "Rejected";
		}
		return "";
	}
	 
}