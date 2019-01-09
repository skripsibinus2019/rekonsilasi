package com.rekonsiliasi.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rekonsiliasi.mapper.DashboardMapper;
import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.mapper.FavoriteMapper;
import com.rekonsiliasi.mapper.StatusLogMapper;
import com.rekonsiliasi.mapper.UserInfoMapper;
import com.rekonsiliasi.mapper.UserRoleMapper;
import com.rekonsiliasi.model.Dashboard;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.Favorite;
import com.rekonsiliasi.model.StatusLog;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

@Repository
@Transactional
public class DashboardDAO extends JdbcDaoSupport {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Autowired
	public DashboardDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}


	
    public Dashboard getDashboardData() {
        String sql = DashboardMapper.BASE_SQL;
 
        Object[] params = new Object[] {};
        
        DashboardMapper mapper = new DashboardMapper();
 
        Dashboard dash = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dash;
    }
	
	

}
