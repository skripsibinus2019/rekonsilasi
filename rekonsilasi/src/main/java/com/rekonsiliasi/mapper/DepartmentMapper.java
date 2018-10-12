package com.rekonsiliasi.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import com.rekonsiliasi.model.Department;
 
import org.springframework.jdbc.core.RowMapper;
 
public class DepartmentMapper implements RowMapper<Department> {
 
    public static final String BASE_SQL = //
            "Select d.dept_no,d.dept_name,d.location "//
                    + " from Department d ";
    
    public static final String BASE_SQL2 = //
            "Select d4.dept_no,d4.dept_name,d4.location "//
                    + " from Department4 d4 ";
 
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer deptId = 1;
        String deptNo = rs.getString("dept_no");
        String deptName = rs.getString("dept_name");
        String location = rs.getString("location");
 
        return new Department(deptId, deptNo, deptName, location);
    }
 
}