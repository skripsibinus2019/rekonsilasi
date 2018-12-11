package com.rekonsiliasi.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rekonsiliasi.mapper.UserInfoMapper;
import com.rekonsiliasi.mapper.UserRoleMapper;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

@Repository
@Transactional
public class UserDao extends JdbcDaoSupport {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Autowired
	public UserDao(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void addUser(UserInfo u) {
		String sql = "Insert into USERS (username, password, email, first_name, last_name, job_title, profilePicture, roleId, createdAt) "//
				+ " values (?,?,?,?,?,?,?,?,?) ";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Object[] params = new Object[] { u.getUsername(), passwordEncoder.encode(u.getUsername()), u.getEmail(), u.getFirst_name(), u.getLast_name(), u.getJob_title(), u.getProfilePicture(), u.getRoleId(), dateFormat.format(date) };
		this.getJdbcTemplate().update(sql, params);

	}
	
	public List<UserInfo> listUsers() {
        String sql = UserInfoMapper.BASE_SQL ;
 
        Object[] params = new Object[] {};
        UserInfoMapper mapper = new UserInfoMapper();
        
        List<UserInfo> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
	
	public UserInfo listUsersDatatable() {
		UserInfo data = new UserInfo();
    	data.setList(this.listUsers());
    	Integer size = this.listUsers().size();
    	
    	data.setRecordsFiltered(size);
    	data.setRecordsTotal(size);
    	data.setDraw("");
    	return data;
	}

	public void updateUser(UserInfo u, Integer id) {
		String sql = "UPDATE USERS SET username = ?, email = ?, first_name = ?, last_name = ?, job_title = ?, profilePicture = ?, updatedAt = ? WHERE userId = ?" ;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Object[] params = new Object[] { u.getUsername(), u.getEmail(), u.getFirst_name(), u.getLast_name(), u.getJob_title(), u.getProfilePicture(), dateFormat.format(date),  id };
		this.getJdbcTemplate().update(sql, params);

	}
	
	public void editProfile(UserInfo u) {
		String sql = "UPDATE USERS SET email = ?, first_name = ?, last_name = ?, job_title = ?, profilePicture = ?, updatedAt = ? WHERE userId = ?" ;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Object[] params = new Object[] { u.getEmail(), u.getFirst_name(), u.getLast_name(), u.getJob_title(), u.getProfilePicture(), dateFormat.format(date),  u.getUserId() };
		this.getJdbcTemplate().update(sql, params);
	}


	public UserInfo getUserById(int id) {
		String sql = UserInfoMapper.BASE_SQL //
                + " where userId = ?";
 
        Object[] params = new Object[] { id };
         
        UserInfoMapper mapper = new UserInfoMapper();
 
        UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return userInfo;
	}
	
	public UserInfo getUserByUsername(String username) {
		String sql = UserInfoMapper.BASE_SQL //
                + " where username = ?";
 
        Object[] params = new Object[] { username };
         
        UserInfoMapper mapper = new UserInfoMapper();
 
        UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return userInfo;
	}

	public void removeUser(int id) {
		String sql = "delete USERS where userId=?";
		this.getJdbcTemplate().update(sql, new Object[]{ id });

	}

}
