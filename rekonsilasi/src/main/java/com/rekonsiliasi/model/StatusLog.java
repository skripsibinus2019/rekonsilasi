package com.rekonsiliasi.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusLog {
 
    private Integer statusLogId;
    private Integer status;
    private Integer userId;
    private Integer logTransactionId;
    private List<StatusLog> list;
    
    private String notes;
    private Date createdAt;
    private String tableSourceId;
    
    private String draw;
    private Integer recordsFiltered;
    private Integer recordsTotal;
    
    
    
    public Integer getStatusLogId() {
		return statusLogId;
	}

	public void setStatusLogId(Integer statusLogId) {
		this.statusLogId = statusLogId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLogTransactionId() {
		return logTransactionId;
	}

	public void setLogTransactionId(Integer logTransactionId) {
		this.logTransactionId = logTransactionId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getTableSourceId() {
		return tableSourceId;
	}

	public void setTableSourceId(String tableSourceId) {
		this.tableSourceId = tableSourceId;
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
    public List<StatusLog> getList() {
		return list;
	}

	public void setList(List<StatusLog> list) {
		this.list = list;
	}

	public StatusLog(Integer statusLogId, Integer status, Integer userId, Integer logTransactionId, List<StatusLog> list,
			String notes, Date createdAt, String tableSourceId, String draw, Integer recordsFiltered,
			Integer recordsTotal) {
		super();
		this.statusLogId = statusLogId;
		this.status = status;
		this.userId = userId;
		this.logTransactionId = logTransactionId;
		this.list = list;
		this.notes = notes;
		this.createdAt = createdAt;
		this.tableSourceId = tableSourceId;
		this.draw = draw;
		this.recordsFiltered = recordsFiltered;
		this.recordsTotal = recordsTotal;
	}

	public StatusLog(Integer statusLogId, Integer status, Integer userId, Integer logTransactionId, String notes,
			Date createdAt) {
		super();
		this.statusLogId = statusLogId;
		this.status = status;
		this.userId = userId;
		this.logTransactionId = logTransactionId;
		this.notes = notes;
		this.createdAt = createdAt;
	}

	public StatusLog() {
		// TODO Auto-generated constructor stub
	}

	
	
}