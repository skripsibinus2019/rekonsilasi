package com.rekonsiliasi.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import com.rekonsiliasi.model.Department;
 
import org.springframework.jdbc.core.RowMapper;
 
public class DepartmentMapper implements RowMapper<Department> {
 
    public static final String BASE_SQL = //
            "Select a.wsid, a.amount, a.transactionDate, a.status"//
                    + " from Table_A a ";
    
    public static final String BASE_SQL2 = //
            "Select b.wsid, b.amount, b.transactionDate, b.status"//
                    + " from Table_B b ";
 
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = 1;
        String wsid = rs.getString("wsid");
        Integer amount = rs.getInt("amount");
        String transactionDate = rs.getString("transactionDate");
        Integer status = rs.getInt("status");
        String tableSource = "";
        return new Department(id, wsid, amount, transactionDate, status, tableSource);
    }
 
}