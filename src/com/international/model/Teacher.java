package com.international.model;

public class Teacher {
	String teacherId;	//老师工号
	String teacherName;	//真实姓名
	String password;	//密码
	String sex;			//性别
	
	String reserves1;		//备用字段
	String reserves2;
	String reserves3;
	
	public Teacher() {
		
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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
