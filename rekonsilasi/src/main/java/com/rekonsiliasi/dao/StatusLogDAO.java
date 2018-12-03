package com.rekonsiliasi.dao;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

 
import javax.sql.DataSource;

import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.mapper.StatusLogMapper;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.StatusLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class StatusLogDAO extends JdbcDaoSupport {
 
    @Autowired
    public StatusLogDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public StatusLog getStatusDAObylogTransId(Integer logTransId) {
        String sql = StatusLogMapper.BASE_SQL //
                + " where l.logTransId = ?";
 
        Object[] params = new Object[] { logTransId };
        
        StatusLogMapper mapper = new StatusLogMapper();
 
        StatusLog dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }
    
    public Department findDepartment2(String deptNo) {
        String sql = DepartmentMapper.BASE_SQL2 //
                + " where b.id = ?";
 
        Object[] params = new Object[] { deptNo };
         
        DepartmentMapper mapper = new DepartmentMapper();
 
        Department dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }
 
    public List<Department> listDepartment() {
        String sql = DepartmentMapper.BASE_SQL //
        		+ "Except " + DepartmentMapper.BASE_SQL2 ;
 
        Object[] params = new Object[] {};
        DepartmentMapper mapper = new DepartmentMapper();
        
        List<Department> list = this.getJdbcTemplate().query(sql, params, mapper);
        for (Department dataA : list) {
			dataA.setTableSource("A");
		}
        return list;
    }
    
    public List<Department> listDepartment2() {
        String sql = DepartmentMapper.BASE_SQL2 //
        		+ "Except " + DepartmentMapper.BASE_SQL ;
 
        Object[] params = new Object[] {};
        DepartmentMapper mapper = new DepartmentMapper();
        
        List<Department> list = this.getJdbcTemplate().query(sql, params, mapper);
        for (Department dataA : list) {
			dataA.setTableSource("B");
		}
        return list;
    }
    
    public void saveRecordStatusLog(StatusLog d) {
    	
    	String sql = "Insert into StatusLog (status,userId,logTransId,notes,createdAt) "//
				+ " values (?,?,?,?,?) ";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Object[] params = new Object[] { d.getStatus(), d.getUserId(), d.getLogTransactionId(), d.getNotes(), dateFormat.format(date) };
		this.getJdbcTemplate().update(sql, params);
		
    }
 
}