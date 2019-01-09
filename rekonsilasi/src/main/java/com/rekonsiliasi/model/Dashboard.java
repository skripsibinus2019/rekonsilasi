package com.rekonsiliasi.model;


public class Dashboard {
	
	private long countA;
	private long countB;
	private long countCSV;
	
	public long getCountA() {
		return countA;
	}

	public void setCountA(long countA) {
		this.countA = countA;
	}

	public long getCountB() {
		return countB;
	}

	public void setCountB(long countB) {
		this.countB = countB;
	}

	public long getCountCSV() {
		return countCSV;
	}

	public void setCountCSV(long countCSV) {
		this.countCSV = countCSV;
	}

	public Dashboard(long countA, long countB, long countCSV) {
		super();
		this.countA = countA;
		this.countB = countB;
		this.countCSV = countCSV;
	}
	
	public Dashboard() {
		
	}
	
	
}
