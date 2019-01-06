package com.rekonsiliasi.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;
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
            + " LEFT JOIN USERS u on l.userId = u.userId";
    
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
        Date createdAt = rs.getDate("createdAt");
        UserInfo User = new UserInfo();
        User.setEmail(rs.getString("email"));
        User.setFirst_name(rs.getString("first_name"));
        User.setLast_name(rs.getString("last_name"));
        User.setUsername(rs.getString("username"));
        User.setJob_title(rs.getString("job_title"));

        return new StatusLog(statusLogId,status,userId,logTransId,notes,createdAt,User);
    }
 
}