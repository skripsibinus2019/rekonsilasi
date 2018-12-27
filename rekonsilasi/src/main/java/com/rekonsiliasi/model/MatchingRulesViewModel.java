package com.rekonsiliasi.model;

import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;


public class MatchingRulesViewModel {
	
	private Map<String, String> columnList;
	private int wsid;
	private int amount;
	private int trasactionDate;
	private String nowColumn;

	public String getNowColumn() {
		return nowColumn;
	}

	public void setNowColumn(String nowColumn) {
		this.nowColumn = nowColumn;
	}

	public int getWsid() {
		return wsid;
	}

	public void setWsid(int wsid) {
		this.wsid = wsid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTrasactionDate() {
		return trasactionDate;
	}

	public void setTrasactionDate(int trasactionDate) {
		this.trasactionDate = trasactionDate;
	}

	@ModelAttribute("columnList")
	public Map<String, String> getColumnList() {
		return columnList;
	}

	public void setColumnList(Map<String, String> columnList) {
		this.columnList = columnList;
	}

}