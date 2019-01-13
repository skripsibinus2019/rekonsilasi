package com.rekonsiliasi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rekonsiliasi.model.Dashboard;
import com.rekonsiliasi.model.Favorite;
import com.rekonsiliasi.model.LogTransaction;
import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;

public class DashboardMapper implements RowMapper<Dashboard>{
	
    public static final String BASE_SQL = //
            "Select TOP 5 (Select count(id) from Table_A) countA, (Select count(id) from Table_B) countB, (Select count(id) from Table_CSV) countCSV";

	@Override
	public Dashboard mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Long countA = rs.getLong("countA");
		Long countB = rs.getLong("countB");
		Long countCSV = rs.getLong("countCSV");
		
		return new Dashboard(countA, countB, countCSV);
	}

}
