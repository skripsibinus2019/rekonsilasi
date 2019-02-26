package com.rekonsiliasi.dao;
 
import java.util.List;

 
import javax.sql.DataSource;

import com.rekonsiliasi.mapper.DepartmentMapper;
import com.rekonsiliasi.model.Department; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
                + " Where a.id = ?";
 
        Object[] params = new Object[] { deptNo };
        
        DepartmentMapper mapper = new DepartmentMapper();
 
        Department dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }
    
    public Department findDepartment2(String deptNo) {
        String sql = DepartmentMapper.BASE_SQL2 //
                + " Where b.id = ?";
 
        Object[] params = new Object[] { deptNo };
         
        DepartmentMapper mapper = new DepartmentMapper();
 
        Department dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }
    
    public Department findDepartment3(String deptNo) {
        String sql = DepartmentMapper.BASE_SQL3 //
                + " where c.id = ?";
 
        Object[] params = new Object[] { deptNo };
         
        DepartmentMapper mapper = new DepartmentMapper();
 
        Department dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }
 
    public List<Department> listDepartment() {
        String sql = DepartmentMapper.BASE_SQL //
        		+ " WHERE NOT EXISTS (SELECT wsid,transactionDate,amount FROM Table_B b Where b.amount = a.amount AND b.transactionDate = a.transactionDate AND b.wsid = a.wsid ) AND NOT EXISTS(SELECT l.tableA_id From Log_Transaction l WHERE l.tableA_id = a.id)" ;
 
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
        		+ " WHERE NOT EXISTS (SELECT wsid,transactionDate,amount FROM Table_A a Where a.amount = b.amount AND a.transactionDate = b.transactionDate AND a.wsid = b.wsid ) AND NOT EXISTS(SELECT l.tableB_id From Log_Transaction l WHERE l.tableB_id = b.id)" ;
 
        Object[] params = new Object[] {};
        DepartmentMapper mapper = new DepartmentMapper();
        
        List<Department> list = this.getJdbcTemplate().query(sql, params, mapper);
        for (Department dataA : list) {
			dataA.setTableSource("B");
		}
        return list;
    }
    
    public List<Department> listDepartment3() {
        String sql = DepartmentMapper.BASE_SQL3 //
        		+ " WHERE NOT EXISTS (SELECT wsid,transactionDate,amount FROM Table_A a Where a.amount = c.amount AND a.transactionDate = c.transactionDate AND a.wsid = c.wsid )" ;
 
        Object[] params = new Object[] {};
        DepartmentMapper mapper = new DepartmentMapper();
        
        List<Department> list = this.getJdbcTemplate().query(sql, params, mapper);
        for (Department dataA : list) {
			dataA.setTableSource("CSV");
		}
        return list;
    }
 
}