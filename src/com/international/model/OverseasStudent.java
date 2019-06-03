package com.international.model;

import java.util.Date;

public class OverseasStudent {
	private String studentId;
	private String studentName;
	private String sex;
	private Date outTime;
	private String outMajor;
	private String inDegree;
	private String outDegree;
	private String inSchoolarship;
	private int inAmount;
	private String outSchoolarship;
	private int outAmount;
	private String currency;
	private int subsidy;
	
	private InternationalClass classes;		//定义InternationalClass类的classes对象
	private College college;                //定义College类的college对象
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public int getInAmount() {
		return inAmount;
	}
	public void setInAmount(int inAmount) {
		this.inAmount = inAmount;
	}
	public int getOutAmount() {
		return outAmount;
	}
	public void setOutAmount(int outAmount) {
		this.outAmount = outAmount;
	}
	public int getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(int subsidy) {
		this.subsidy = subsidy;
	}
	public String getOutMajor() {
		return outMajor;
	}
	public void setOutMajor(String outMajor) {
		this.outMajor = outMajor;
	}
	public String getInDegree() {
		return inDegree;
	}
	public void setInDegree(String inDegree) {
		this.inDegree = inDegree;
	}
	public String getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(String outDegree) {
		this.outDegree = outDegree;
	}
	public String getInSchoolarship() {
		return inSchoolarship;
	}
	public void setInSchoolarship(String inSchoolarship) {
		this.inSchoolarship = inSchoolarship;
	}
	public String getOutSchoolarship() {
		return outSchoolarship;
	}
	public void setOutSchoolarship(String outSchoolarship) {
		this.outSchoolarship = outSchoolarship;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public InternationalClass getClasses() {
		return classes;
	}
	public void setClasses(InternationalClass classes) {
		this.classes = classes;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	
}
