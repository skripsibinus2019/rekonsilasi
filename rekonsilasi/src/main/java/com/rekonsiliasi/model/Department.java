package com.rekonsiliasi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {
 
    private Integer id;
    private String wsId;
    private Integer amount;
    private String transactionDate;
    private List<Department> list;
    private String draw;
    private Integer recordsFiltered;
    private Integer recordsTotal;
    private String tableSource;
//    "recordsFiltered": 1,
//    "recordsTotal": 1,
    
    
    
    public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
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
    public List<Department> getList() {
		return list;
	}

	public void setList(List<Department> list) {
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
	
	public Department() {
		 
    }

	public Department(Integer id, String wsId, Integer amount, String transactionDate,
			List<Department> list, String draw, Integer recordsFiltered, Integer recordsTotal) {
		super();
		this.id = id;
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.list = list;
		this.draw = draw;
		this.recordsFiltered = recordsFiltered;
		this.recordsTotal = recordsTotal;
	}

	public Department(String wsId, Integer amount, String transactionDate) {
		super();
		this.wsId = wsId;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}
	
    public Department(Integer id, String wsId, Integer amount,
            String transactionDate, Integer status, String tableSource,
            String notes) {
        this.id = id;
        this.wsId = wsId;
        this.amount = amount;
        this.transactionDate = transactionDate;

    }
	
	 
}