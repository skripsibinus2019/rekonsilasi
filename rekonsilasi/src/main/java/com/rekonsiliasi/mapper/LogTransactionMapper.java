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
            "Select l.logTransId, l.wsid, l.amount, l.transactionDate, l.tableA_id, l.tableB_id"
            + " FROM Log_Transaction l";
    
    public static final String ALL_SQL = //
            "Select *"
            + " FROM Log_Transaction l";
    
    @Autowired
    private StatusLogDAO statusLogDAO;
    
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
        Integer logTransId = rs.getInt("logTransId");
        
        Integer status = 0;
        try {
	        if(statusLogDAO.getStatusDAObylogTransId(logTransId).getStatus()!=null) {
	        	status = statusLogDAO.getStatusDAObylogTransId(logTransId).getStatus();
	        }else {
	        	status = 0;
	        }
        }catch(Exception e) {
        	
        }
        
        try {
          String notes = rs.getString("notes");
		} catch (Exception e) {
			// TODO: handle exception
		}

        String namaStatus = namaStatus(status);
        return new LogTransaction(wsid, amount, transactionDate, tableSourceA, tableSourceB, namaStatus);
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