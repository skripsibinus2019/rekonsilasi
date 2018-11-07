package com.rekonsiliasi.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.LogTransaction;

import org.springframework.jdbc.core.RowMapper;
 
public class LogTransactionMapper implements RowMapper<LogTransaction> {
 
    public static final String BASE_SQL = //
            "Select l.wsid, l.amount, l.transactionDate, l.tableA_id, l.tableB_id"
            + " FROM Log_Transaction l";
    
//    public static final String BASE_SQL2 = //
//            "Select b.id, b.wsid, b.amount, b.transactionDate " + 
//            "From Table_B b " + 
//            "WHERE NOT EXISTS(SELECT l.tableA_id From Log_Transaction l WHERE l.tableB_id = b.id)";
 
    @Override
    public LogTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Integer id = rs.getInt("logTransId");
        String wsid = rs.getString("wsid");
        Integer amount = rs.getInt("amount");
        String transactionDate = rs.getString("transactionDate");
//        Integer status = rs.getInt("status");
        String tableSourceA = rs.getString("tableA_id");
        String tableSourceB = rs.getString("tableB_id");
//        if(rs.getString("notes") != null) {
//            notes = rs.getString("notes");
//        }
        
        return new LogTransaction(wsid, amount, transactionDate, tableSourceA, tableSourceB, null);
    }
 
}