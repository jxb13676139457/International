package com.international.model;

import java.util.Date;

public class ExchangeStudent {
	//此表按照单表来操作，只用作记录
	private int id;
	private String studentNo;
	private String studentName;
	private String sex;
	private String major;
	private String className;
	private Date startTime;
	private Date endTime;
	private String exchangeCollege;
	private String reserves1;
	private String reserves2;
	private String reserves3;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getExchangeCollege() {
		return exchangeCollege;
	}
	public void setExchangeCollege(String exchangeCollege) {
		this.exchangeCollege = exchangeCollege;
	}
	public String getReserves1() {
		return reserves1;
	}
	public void setReserves1(String reserves1) {
		this.reserves1 = reserves1;
	}
	public String getReserves2() {
		return reserves2;
	}
	public void setReserves2(String reserves2) {
		this.reserves2 = reserves2;
	}
	public String getReserves3() {
		return reserves3;
	}
	public void setReserves3(String reserves3) {
		this.reserves3 = reserves3;
	}
	
	
}
