package com.rekonsiliasi.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRole {
	
	private Integer roleId;
	@NotNull
	private String roleName;
	@NotNull
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

	public UserRole(Integer roleId, String roleName, String description) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
	}
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
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
