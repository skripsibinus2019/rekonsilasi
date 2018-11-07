package com.rekonsiliasi.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import com.rekonsiliasi.model.Department;
 
import org.springframework.jdbc.core.RowMapper;
 
public class DepartmentMapper implements RowMapper<Department> {
 
    public static final String BASE_SQL = //
            "Select a.id, a.wsid, a.amount, a.transactionDate " + 
            "From Table_A a " + 
            "WHERE NOT EXISTS(SELECT l.tableA_id From Log_Transaction l WHERE l.tableA_id = a.id)";
    
    public static final String BASE_SQL2 = //
            "Select b.id, b.wsid, b.amount, b.transactionDate " + 
            "From Table_B b " + 
            "WHERE NOT EXISTS(SELECT l.tableA_id From Log_Transaction l WHERE l.tableB_id = b.id)";
 
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String wsid = rs.getString("wsid");
        Integer amount = rs.getInt("amount");
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