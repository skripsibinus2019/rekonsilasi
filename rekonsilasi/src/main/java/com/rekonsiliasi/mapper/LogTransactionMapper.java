package com.rekonsiliasi.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rekonsiliasi.dao.StatusLogDAO;
import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.LogTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
 
public class LogTransactionMapper implements RowMapper<LogTransaction> {
 
    public static final String BASE_SQL = //
    		"Select * "
            + "FROM Log_Transaction l "
            + "JOIN StatusLog s "
            + "ON l.logTransId = s.logTransId "
            + "Where createdAt = (Select Max(createdAt) From StatusLog sl Where sl.logTransId = l.logTransId)";
    
    public static final String ALL_SQL = //
            "Select * "
            + "FROM Log_Transaction l "
            + "JOIN StatusLog s "
            + "ON l.logTransId = s.logTransId "
            + "Where createdAt = (Select Max(createdAt) From StatusLog sl Where sl.logTransId = l.logTransId) "
            + "AND s.status = 1";
    
    public static final String ID_SEARCH = //
    		"Select * "
            + "FROM Log_Transaction l "
            + "JOIN StatusLog s "
            + "ON l.logTransId = s.logTransId ";
    
//    public static final String BASE_SQL2 = //
//            "Select b.id, b.wsid, b.amount, b.transactionDate " + 
//            "From Table_B b " + 
//            "WHERE NOT EXISTS(SELECT l.tableA_id From Log_Transaction l WHERE l.tableB_id = b.id)";
 
    @Override
    public LogTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Integer id = rs.getInt("logTransId");
        String wsid = rs.getString("wsid");
        Long amount = rs.getLong("amount");
        String transactionDate = rs.getString("transactionDate");
        String tableSource = "";
        Long tableSourceId;
        Long tableA_id = rs.getLong("tableA_id");
        Long tableB_id = rs.getLong("tableB_id");
        if(tableA_id != 0) {
        	tableSource = "A";
        	tableSourceId = rs.getLong("tableA_id");
        }else if(tableB_id != 0){
        	tableSource = "B";
        	tableSourceId = rs.getLong("tableB_id");
        }else {
        	tableSource = "CSV";
        	tableSourceId = (long) 0;
        }
        
        Integer status = rs.getInt("status");
        Long id = rs.getLong("logTransId");
        String namaStatus = namaStatus(status);
        String notes = rs.getString("notes");
        
        return new LogTransaction(id,wsid, amount, transactionDate, tableSource, tableSourceId, namaStatus, notes);
    }
 
	public String namaStatus(Integer status) {
		
		if(status == 1) {
			return "Pending";
		}else if(status == 2) {
			return "Approved";
		}else if(status == 3) {
			return "Rejected";
		}
		return "Investigate me!";
	}
}