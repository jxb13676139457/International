package com.international.model;

public class Exam {
	int examId;		//雅思培训考试ID
	String time;	//考试时间
	String location;//考试地点
	String type;	//雅思/托福
	String examType;//正式考试/模拟考试
	Agency agen;	//关系映射,Agency(agencyId)
	
	String reserves1;//备用字段
	String reserves2;
	String reserves3;
	
	public Exam(){
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public Agency getAgen() {
		return agen;
	}

	public void setAgen(Agency agen) {
		this.agen = agen;
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
