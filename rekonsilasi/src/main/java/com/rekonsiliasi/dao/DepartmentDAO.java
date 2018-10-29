package com.rekonsiliasi.dao;
 
import java.util.List;

 
import javax.sql.DataSource;

import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.model.Department; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class DepartmentDAO extends JdbcDaoSupport {
 
    @Autowired
    public DepartmentDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public Department findDepartment(String deptNo) {
        String sql = DepartmentMapper.BASE_SQL //
                + " where a.id = ?";
 
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
        
        return list;
    }
    
    public List<Department> listDepartment2() {
        String sql = DepartmentMapper.BASE_SQL2 //
        		+ "Except " + DepartmentMapper.BASE_SQL ;
 
        Object[] params = new Object[] {};
        DepartmentMapper mapper = new DepartmentMapper();
        
        List<Department> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
 
}