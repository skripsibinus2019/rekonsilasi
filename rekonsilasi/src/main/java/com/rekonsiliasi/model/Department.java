package com.rekonsiliasi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Department {
 
    private Integer deptId;
    private String deptNo;
    private String deptName;
    private String location;
    private List<Department> list;
 
    public Department() {
 
    }
 
    public Department(Integer deptId, String deptNo, String deptName,
            String location) {
        this.deptId = deptId;
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.location = location;
    }
    
    @JsonProperty(value="data")
    public List<Department> getList() {
		return list;
	}

	public void setList(List<Department> list) {
		this.list = list;
	}

	public Integer getDeptId() {
        return deptId;
    }
 
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
 
    public String getDeptNo() {
        return deptNo;
    }
 
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
 
    public String getDeptName() {
        return deptName;
    }
 
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
 
    public String getLocation() {
        return location;
    }
 
    public void setLocation(String location) {
        this.location = location;
    }
}