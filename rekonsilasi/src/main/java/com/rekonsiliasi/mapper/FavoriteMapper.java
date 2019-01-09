package com.rekonsiliasi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rekonsiliasi.model.Favorite;
import com.rekonsiliasi.model.LogTransaction;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

public class FavoriteMapper implements RowMapper<Favorite>{
	
    public static final String BASE_SQL = //
            "Select * "//
          + " from Favorite f "
          + " LEFT JOIN USERS u ON u.userId = f.userId"
          + " LEFT JOIN Log_Transaction l ON l.logTransId = f.logTransId"
          + " LEFT JOIN StatusLog s ON  s.logTransId = f.logTransId" + 
          " Where s.createdAt = (Select Max(createdAt) From StatusLog sl Where sl.logTransId = l.logTransId)";

	@Override
	public Favorite mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Integer favId = rs.getInt("favId");
		Integer logTransId = rs.getInt("logTransId");
		Integer userId = rs.getInt("userId");
		
		LogTransaction logTransaction = new LogTransaction();
		logTransaction.setId(rs.getLong("logTransId"));
		logTransaction.setAmount(rs.getInt("amount"));
		logTransaction.setWsId(rs.getString("wsid"));
		logTransaction.setTransactionDate(rs.getString("transactionDate"));
		logTransaction.setStatus(rs.getInt("status"));
		logTransaction.setNotes(rs.getString("notes"));

		UserInfo userInfo = new UserInfo();
		
		return new Favorite(favId, logTransId, userId, logTransaction, userInfo);
	}

}
