package com.international.model;

import java.util.Set;

public class InternationalClass {
	private int classId;
	private String className;
	private String grade;
	private String major;
	private String reserves1;
	private String reserves2;
	private String reserves3;
	
	private Set<InternationalStudent> interStudents;  //关联的国际班学生对象
	
	public Set<InternationalStudent> getInterStudents() {
		return interStudents;
	}
	public void setInterStudents(Set<InternationalStudent> interStudents) {
		this.interStudents = interStudents;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
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
