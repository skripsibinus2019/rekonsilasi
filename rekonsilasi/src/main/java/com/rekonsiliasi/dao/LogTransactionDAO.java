package com.rekonsiliasi.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

 
import javax.sql.DataSource;

import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.mapper.LogTransactionMapper;
import com.rekonsiliasi.mapper.StatusLogMapper;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.LogTransaction;
import com.rekonsiliasi.model.StatusLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class LogTransactionDAO extends JdbcDaoSupport {
 
    @Autowired
    public LogTransactionDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public Department showLogTransaction(String deptNo) {
        String sql = LogTransactionMapper.BASE_SQL;
 
        Object[] params = new Object[] { deptNo };
        
        DepartmentMapper mapper = new DepartmentMapper();
 
        Department dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }
    
    public List<LogTransaction> listLogTransaction() {
        String sql = LogTransactionMapper.BASE_SQL;
 
        Object[] params = new Object[] {};
        LogTransactionMapper mapper = new LogTransactionMapper();
        
        List<LogTransaction> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    
//    public void saveRecord(LogTransaction lt) {
//    	
//    	String sql = "Insert into Log_Transaction (logTransId, tableA_id, tableB_id, wsid, amount, transactionDate) "//
//				+ " values (?,?,?,?,?,?,?) ";
//		Object[] params = new Object[] { lt.getId(), lt.getTableSourceA(), lt.getTableSourceB(), lt.getWsId(), lt.getAmount(), lt.getTransactionDate()};
//		this.getJdbcTemplate().update(sql, params);
//		
//    }
    
    public List<LogTransaction> allLogTransaction(){
    	
        String sql = LogTransactionMapper.ALL_SQL;
        
        Object[] params = new Object[] {};
        LogTransactionMapper mapper = new LogTransactionMapper();
        
        List<LogTransaction> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    
    public LogTransaction getById(Integer logTransId) {
        String sql = LogTransactionMapper.ID_SEARCH //
                + " where l.logTransId = ?";
 
        Object[] params = new Object[] { logTransId };
        
        LogTransactionMapper mapper = new LogTransactionMapper();
 
        LogTransaction dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }
    
	public void saveRecord(LogTransaction l) {
	    	if(l.getTableSource().equals("A")) {
	    		String sql = "Insert into Log_Transaction (tableA_id,wsid,amount,transactiondate) "//
					+ " values (?,?,?,?) ";
				KeyHolder keyHolder = new GeneratedKeyHolder();
				this.getJdbcTemplate().update(new PreparedStatementCreator() {
				    @Override
				    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				        statement.setInt(1, l.getTableSourceId());
				        statement.setString(2, l.getWsId());
				        statement.setInt(3, l.getAmount());
				        statement.setString(4, l.getTransactionDate());
				        return statement;
				    }
				}, keyHolder);
				
				Long id = keyHolder.getKey().longValue();
				l.setId(id);
	    	}else if(l.getTableSource().equals("B")) {
	    		String sql = "Insert into Log_Transaction (tableB_id,wsid,amount,transactiondate) "//
						+ " values (?,?,?,?) ";
					KeyHolder keyHolder = new GeneratedKeyHolder();
					this.getJdbcTemplate().update(new PreparedStatementCreator() {
					    @Override
					    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					        statement.setInt(1, l.getTableSourceId());
					        statement.setString(2, l.getWsId());
					        statement.setInt(3, l.getAmount());
					        statement.setString(4, l.getTransactionDate());
					        return statement;
					    }
					}, keyHolder);
					
					Long id = keyHolder.getKey().longValue();
					l.setId(id);
	    	}else if(l.getTableSource().equals("CSV")) {
	    		String sql = "Insert into Log_Transaction (wsid,amount,transactiondate,from_csv) "//
						+ " values (?,?,?,?) ";
					KeyHolder keyHolder = new GeneratedKeyHolder();
					this.getJdbcTemplate().update(new PreparedStatementCreator() {
					    @Override
					    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					        statement.setString(1, l.getWsId());
					        statement.setInt(2, l.getAmount());
					        statement.setString(3, l.getTransactionDate());
					        statement.setInt(4, 1);
					        return statement;
					    }
					}, keyHolder);
					
					Long id = keyHolder.getKey().longValue();
					l.setId(id);
	    	}	
	 }
 
}