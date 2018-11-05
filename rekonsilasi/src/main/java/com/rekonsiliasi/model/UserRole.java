package com.rekonsiliasi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRole {
	
	private int roleId;
	private String roleName;
	private String description;
	private List<UserRole> list;
	private Integer recordsFiltered;
	
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public String getDraw() {
		return draw;
	}

	private Integer recordsTotal;
	private String draw;

	@JsonProperty(value="data")
	public List<UserRole> getList() {
		return list;
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
	

	public void setList(List<UserRole> list) {
		this.list = list;
	}

	public UserRole() {
		super();
	}

	public UserRole(String roleName, String description) {
		super();
		this.roleName = roleName;
		this.description = description;
	}
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
