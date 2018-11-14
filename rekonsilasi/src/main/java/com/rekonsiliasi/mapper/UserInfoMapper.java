package com.rekonsiliasi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.rekonsiliasi.model.UserInfo;

public class UserInfoMapper implements RowMapper<UserInfo>{
	
    public static final String BASE_SQL = //
            "Select * "//
                    + " from Users u ";

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Integer userId = rs.getInt("userId");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String email = rs.getString("email");
		String first_name = rs.getString("first_name");
		String last_name = rs.getString("last_name");
		String job_title = rs.getString("job_title");
		String profilePicture = rs.getString("profilePicture");
		Integer roleId = rs.getInt("roleId");
		Date createdAt = rs.getDate("createdAt");
		String updateAt = rs.getString("updatedAt");
		
		
		return new UserInfo(userId,username,password,email,first_name,last_name,job_title,profilePicture,roleId,createdAt,updateAt);
	}

}
