package com.rekonsiliasi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {

	private String username;
	private String password;
	private List<UserInfo> list;
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
	public List<UserInfo> getList() {
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
	

	public void setList(List<UserInfo> list) {
		this.list = list;
	}

	public UserInfo() {
		super();
	}

	public UserInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
