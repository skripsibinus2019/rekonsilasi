package com.rekonsiliasi.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.rekonsiliasi.mapper.UserInfoMapper;
import com.rekonsiliasi.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	  @Autowired
	  public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException{
	   this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	  }

	
	@Autowired
    public LoginDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	public UserInfo findUserInfo(String username) {
        String sql = UserInfoMapper.BASE_SQL //
                + " where u.username = ?";
 
        Object[] params = new Object[] { username };
         
        UserInfoMapper mapper = new UserInfoMapper();
 
        UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return userInfo;
    }
	
	private SqlParameterSource getSqlParameterByModel(String username,String password) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("username", username);
		paramSource.addValue("password", password);
		
		return paramSource;
		
	}

	@Override
	 public List<String> getUserRoles(String username) {
	  String query = "select ur.roleName as user_role from USERS_ROLE ur join USERS u on ur.roleId = u.roleId WHERE username=:username";
	  List<String> roles = namedParameterJdbcTemplate.queryForList(query, getSqlParameterByModel(username, ""), String.class);
	  return roles;
	 }

}
