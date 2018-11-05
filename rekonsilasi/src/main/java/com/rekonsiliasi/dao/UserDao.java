package com.rekonsiliasi.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.mapper.UserInfoMapper;
import com.rekonsiliasi.model.Department;
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
		String sql = "Insert into USER_ROLES (roleName, description) "//
				+ " values (?,?) ";
		Object[] params = new Object[] { u.getUsername(), passwordEncoder.encode(u.getUsername()) };
		this.getJdbcTemplate().update(sql, params);

	}
	
	public List<UserInfo> listUsers() {
        String sql = UserInfoMapper.BASE_SQL ;
 
        Object[] params = new Object[] {};
        UserInfoMapper mapper = new UserInfoMapper();
        
        List<UserInfo> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
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
