package com.rekonsiliasi.model;

import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;


public class MatchingRulesViewModel {
 
	private Map<String, String> columnList;

	public Map<String, String> getColumnList() {
		return columnList;
	}

	public void setColumnList(Map<String, String> columnList) {
		this.columnList = columnList;
	}

}