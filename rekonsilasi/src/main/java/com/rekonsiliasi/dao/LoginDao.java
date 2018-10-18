package com.rekonsiliasi.dao;

import java.util.List;

import com.rekonsiliasi.model.UserInfo;

public interface LoginDao {
	
	public UserInfo findUserInfo(String username);
	
	public List<String> getUserRoles(String username);

}
