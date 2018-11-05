package com.rekonsiliasi.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rekonsiliasi.mapper.UserInfoMapper;
import com.rekonsiliasi.mapper.UserRoleMapper;
import com.rekonsiliasi.model.UserInfo;
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


	public void addRole(UserRole u) {
		String sql = "Insert into USERS_ROLE (roleName, description) "//
				+ " values (?,?) ";
		Object[] params = new Object[] { u.getRoleName(), u.getDescription() };
		this.getJdbcTemplate().update(sql, params);

	}
	
	public List<UserRole> listRole() {
        String sql = UserRoleMapper.BASE_SQL ;
 
        Object[] params = new Object[] {};
        UserRoleMapper mapper = new UserRoleMapper();
        
        List<UserRole> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
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
