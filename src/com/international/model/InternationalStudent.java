package com.international.model;

public class InternationalStudent {

	String studentId;
	String studentName;
	char sex;
	String password;
	String status;
	int classId;
	
	private InternationalClass classes;		//定义InternationalClass类的classes对象
	
	private String reserves1;
	private String reserves2;
	private String reserves3;
	
	
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
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
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
	public InternationalClass getClasses() {
		return classes;
	}
	public void setClasses(InternationalClass classes) {
		this.classes = classes;
	}
}
