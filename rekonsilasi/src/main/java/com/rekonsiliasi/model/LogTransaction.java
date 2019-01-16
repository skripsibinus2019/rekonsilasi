package com.rekonsiliasi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogTransaction {
 
    private Long id;
    private String wsId;
    private Long amount;
    private String transactionDate;
	private List<LogTransaction> list;
    private String draw;
    private Integer recordsFiltered;
    private Integer recordsTotal;
	private Long tableSourceId;
    private String tableSource;
    private Integer status;
    private String namaStatus;
    private String notes;
    private Integer fav;
    
    

	
    public Integer getFav() {
		return fav;
	}

	public void setFav(Integer fav) {
		this.fav = fav;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

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
	
    public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
	}
    
    @JsonProperty(value="data")
    public List<LogTransaction> getList() {
		return list;
	}

	public void setList(List<LogTransaction> list) {
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWsId() {
		return wsId;
	}

	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public Long getTableSourceId() {
		return tableSourceId;
	}

	public void setTableSourceId(Long tableSourceId) {
		this.tableSourceId = tableSourceId;
	}

	public LogTransaction() {
		 
    }

	public LogTransaction(Long id, String wsId, Long amount, String transactionDate, List<LogTransaction> list,
			String draw, Integer recordsFiltered, Integer recordsTotal, String tableSourceA, String tableSourceB,
			Long tableSourceId) {
		super();
		this.id = id;
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.list = list;
		this.draw = draw;
		this.recordsFiltered = recordsFiltered;
		this.recordsTotal = recordsTotal;
		this.tableSourceId = tableSourceId;
	}
	
	public LogTransaction(Long id,String wsId, Long amount, String transactionDate, String tableSourceA, String tableSourceB,
			String namaStatus) {
		super();
		this.id = id;
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.namaStatus = namaStatus;
	}

	public LogTransaction(String wsId, Long amount, String transactionDate, String tableSourceA, String tableSourceB,
			String namaStatus) {
		super();
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.namaStatus = namaStatus;
	}

	public LogTransaction(String wsId, Long amount, String transactionDate, String tableSource, Long tableSourceId, String tableSourceB,
			String namaStatus, String notes) {
		super();
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.namaStatus = namaStatus;
		this.notes = notes;
		this.tableSource = tableSource;
		this.tableSourceId = tableSourceId;
	}
	
	public LogTransaction(Long id,String wsId, Long amount, String transactionDate, String tableSource, Long tableSourceId,
			String namaStatus, String notes) {
		super();
		this.id = id;
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.namaStatus = namaStatus;
		this.notes = notes;
		this.tableSource = tableSource;
		this.tableSourceId = tableSourceId;
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