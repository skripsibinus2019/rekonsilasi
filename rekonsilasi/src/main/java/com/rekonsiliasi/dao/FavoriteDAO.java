package com.rekonsiliasi.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.mapper.FavoriteMapper;
import com.rekonsiliasi.mapper.UserInfoMapper;
import com.rekonsiliasi.mapper.UserRoleMapper;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.Favorite;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

@Repository
@Transactional
public class FavoriteDAO extends JdbcDaoSupport {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Autowired
	public FavoriteDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}


	public void addToFavorite(Favorite f) {
		String sql = "Insert into Favorite (logTransId, userId) "//
				+ " values (?,?) ";
		Object[] params = new Object[] { f.getLogTransId(), f.getUserId() };
		this.getJdbcTemplate().update(sql, params);

	}
	
	public List<Favorite> listFavorite() {
        String sql = FavoriteMapper.BASE_SQL ;
 
        Object[] params = new Object[] {};
        FavoriteMapper mapper = new FavoriteMapper();
        
        List<Favorite> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
	

	public void removeFavorite(Integer id) {
		String sql = "delete Favorite where favId = ?";
		this.getJdbcTemplate().update(sql, new Object[]{ id });

	}
	
	public List<Favorite> getUserByUserIdlogTransId(Integer userId, Long logTransId) {
		String sql = FavoriteMapper.BASE_SQL //
                + " where f.userId = ? AND f.logTransId = ?";
 
        Object[] params = new Object[] { userId, logTransId };
         
        FavoriteMapper mapper = new FavoriteMapper();
 
        List<Favorite> fav = this.getJdbcTemplate().query(sql, params, mapper);
        if(fav.isEmpty()) {
        	return null;
        }else {
        	return fav;
        }
	}
	
	

}
