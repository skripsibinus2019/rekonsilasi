package com.rekonsiliasi.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rekonsiliasi.model.UserInfo;

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
		String sql = "Insert into USERS (username, password) "//
				+ " values (?,?) ";
		Object[] params = new Object[] { u.getUsername(), passwordEncoder.encode(u.getPassword()) };
		this.getJdbcTemplate().update(sql, params);

	}

	public void updateUser(UserInfo u) {
		// TODO Auto-generated method stub

	}

	public UserInfo getUserById(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeUser(String username) {
		// TODO Auto-generated method stub

	}

}
