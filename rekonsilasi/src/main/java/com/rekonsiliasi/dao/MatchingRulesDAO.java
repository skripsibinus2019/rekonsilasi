package com.rekonsiliasi.dao;
 
import java.util.List;

 
import javax.sql.DataSource;

import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.MatchingRules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class MatchingRulesDAO extends JdbcDaoSupport {
 
    @Autowired
    public MatchingRulesDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    public void insertRecord(MatchingRules mr) {
    	String sql = "Insert into Table_CSV (wsid,amount,transactiondate) "//
				+ " values (?,?,?) ";
		Object[] params = new Object[] { mr.getWsId(), mr.getAmount(), mr.getTransactionDate() };
		this.getJdbcTemplate().update(sql, params);
		
    }
 
}