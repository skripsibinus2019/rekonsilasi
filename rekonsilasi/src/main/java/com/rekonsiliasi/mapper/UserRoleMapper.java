package com.rekonsiliasi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rekonsiliasi.model.UserInfo;

public class UserRoleMapper implements RowMapper<UserInfo>{
	
    public static final String BASE_SQL = //
            "Select * "//
                    + " from USER_ROLES u ";

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String username = rs.getString("username");
		String password = rs.getString("user_role");
		
		return new UserInfo(username,password);
	}

}
