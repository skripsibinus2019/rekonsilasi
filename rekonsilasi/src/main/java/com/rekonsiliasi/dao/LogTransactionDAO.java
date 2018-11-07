package com.rekonsiliasi.dao;
 
import java.util.List;

 
import javax.sql.DataSource;

import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.mapper.LogTransactionMapper;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.LogTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
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
 
}