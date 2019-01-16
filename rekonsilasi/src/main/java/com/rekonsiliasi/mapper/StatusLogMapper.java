package com.rekonsiliasi.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rekonsiliasi.model.Department;
import com.rekonsiliasi.model.LogTransaction;
import com.rekonsiliasi.model.StatusLog;
import com.rekonsiliasi.model.UserInfo;

import org.springframework.jdbc.core.RowMapper;
 
public class StatusLogMapper implements RowMapper<StatusLog> {
 
    public static final String BASE_SQL = //
            "Select *"
            + " FROM StatusLog l"
            + " LEFT JOIN USERS u on l.userId = u.userId"
            + " LEFT JOIN Log_Transaction t on l.logTransId = t.logTransId";
    
//    public static final String BASE_SQL2 = //
//            "Select b.id, b.wsid, b.amount, b.transactionDate " + 
//            "From Table_B b " + 
//            "WHERE NOT EXISTS(SELECT l.tableA_id From Log_Transaction l WHERE l.tableB_id = b.id)";
 
    @Override
    public StatusLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer statusLogId = rs.getInt("statusLogId");
        Integer status = rs.getInt("status");
        Integer userId = rs.getInt("userId");
        Long logTransId = rs.getLong("logTransId");
        String notes = rs.getString("notes");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createdAt = new Date();
		try {
			createdAt = dateFormat.parse(rs.getString("createdAt"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        UserInfo User = new UserInfo();
        LogTransaction logTransaction = new LogTransaction();
        
        User.setEmail(rs.getString("email"));
        User.setFirst_name(rs.getString("first_name"));
        User.setLast_name(rs.getString("last_name"));
        User.setUsername(rs.getString("username"));
        User.setJob_title(rs.getString("job_title"));
        
        logTransaction.setAmount(rs.getLong("amount"));
        logTransaction.setTransactionDate(rs.getString("transactionDate"));
        logTransaction.setWsId(rs.getString("wsid"));
        logTransaction.setId(rs.getLong("logTransId"));
        

        Integer tableA_id = rs.getInt("tableA_id");
        Integer tableB_id = rs.getInt("tableB_id");
        if(tableA_id != 0) {
        	logTransaction.setTableSource("A");
        	logTransaction.setTableSourceId(rs.getLong("tableA_id"));
        }else if(tableB_id != 0){
        	logTransaction.setTableSource("B");
        	logTransaction.setTableSourceId(rs.getLong("tableB_id"));
        }else {
        	logTransaction.setTableSource("CSV");
        	logTransaction.setTableSourceId((long) 0);
        }
        

        return new StatusLog(statusLogId,status,userId,logTransId,notes,createdAt,User,logTransaction);
    }
 
}