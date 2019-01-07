package com.rekonsiliasi.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Favorite {
	
	private Integer favId;
	private Integer userId;
	private Integer logTransId;
	private LogTransaction logTransaction;
	private UserInfo userInfo;
	
	public LogTransaction getLogTransaction() {
		return logTransaction;
	}
	public void setLogTransaction(LogTransaction logTransaction) {
		this.logTransaction = logTransaction;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getFavId() {
		return favId;
	}
	public void setFavId(Integer favId) {
		this.favId = favId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLogTransId() {
		return logTransId;
	}
	public void setLogTransId(Integer logTransId) {
		this.logTransId = logTransId;
	}
	
	public Favorite(Integer favId, Integer logTransId, Integer userId, LogTransaction logTransaction, UserInfo userInfo) {
		super();
		this.favId = favId;
		this.logTransId = logTransId;
		this.userId = userId;
		this.logTransaction = logTransaction;
		this.userInfo = userInfo;
	}
	
	public Favorite() {
		
	}
	
	
}
