package com.rekonsiliasi.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rekonsiliasi.model.UserRole;

@Repository
@Transactional
public class UserRoleDao extends JdbcDaoSupport {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Autowired
	public UserRoleDao(DataSource dataSource) {
		this.setDataSource(dataSource);
	}


	public void addUser(UserRole u) {
		String sql = "Insert into USER_ROLES (username, user_role) "//
				+ " values (?,?) ";
		Object[] params = new Object[] { u.getUsername(), u.getUser_role() };
		this.getJdbcTemplate().update(sql, params);

	}

	public void updateUser(UserRole u) {
		// TODO Auto-generated method stub

	}

	public UserRole getUserById(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeUser(String username) {
		// TODO Auto-generated method stub

	}

}
