package com.rekonsiliasi.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {

	private Integer userId;
	@NotNull
	private String username;
	@NotNull
	private String password;
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}
	@NotNull
	private String email;
	@NotNull
	private String first_name;
	@NotNull
	private String last_name;
	@NotNull
	private String job_title;
	private Date createdAt;
	private String updateAt;
	public Integer getUserId() {
		return userId;
	}

	private String profilePicture;
	private Integer roleId;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

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

	public UserInfo(Integer userId, String username, String password, String email, String first_name, String last_name, String job_title, String profilePicture, Integer roleId, Date createdAt, String updateAt) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.job_title = job_title;
		this.profilePicture = profilePicture;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
