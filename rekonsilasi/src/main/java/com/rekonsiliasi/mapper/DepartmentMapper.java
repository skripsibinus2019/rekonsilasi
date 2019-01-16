package com.rekonsiliasi.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import com.rekonsiliasi.model.Department;
 
import org.springframework.jdbc.core.RowMapper;
 
public class DepartmentMapper implements RowMapper<Department> {
 
    public static final String BASE_SQL = //
            "Select * " + 
            "From Table_A AS a";
    
    public static final String BASE_SQL2 = //
            "Select * " + 
            "From Table_B AS b";
    
    public static final String BASE_SQL3 = //
            "Select c.id, c.wsid, c.amount, c.transactionDate " + 
            "From Table_CSV c ";
    
    public static final String BASE_SQL4 = //
    		"Select a.id, a.wsid, a.amount, a.transactionDate " + 
            "From Table_A a ";
 
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String wsid = rs.getString("wsid");
        Long amount = rs.getLong("amount");
        String transactionDate = rs.getString("transactionDate");
//        Integer status = rs.getInt("status");
        Integer status = 1;
        String tableSource = "";
        String notes = "";
//        if(rs.getString("notes") != null) {
//            notes = rs.getString("notes");
//        }
        return new Department(id, wsid, amount, transactionDate, status, tableSource, notes);
    }
 
}