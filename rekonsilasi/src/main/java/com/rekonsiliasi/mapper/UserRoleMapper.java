package com.rekonsiliasi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

public class UserRoleMapper implements RowMapper<UserRole>{
	
    public static final String BASE_SQL = //
            "Select * "//
                    + " from USERS_ROLE u ";

	@Override
	public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String roleName = rs.getString("roleName");
		String description = rs.getString("description");
		
		return new UserRole(roleName, description);
	}

}
