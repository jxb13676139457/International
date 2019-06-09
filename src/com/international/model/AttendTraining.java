package com.international.model;

public class AttendTraining {
	private int attendId;   //id自增主键
	private String reserves1;
	private String reserves2;
	private String reserves3;
	
	private Training training;     //定义关联的training对象
	private InternationalStudent interStudent;      //定义关联的interStudent对象
	
	public int getAttendId() {
		return attendId;
	}
	public void setAttendId(int attendId) {
		this.attendId = attendId;
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
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public InternationalStudent getInterStudent() {
		return interStudent;
	}
	public void setInterStudent(InternationalStudent interStudent) {
		this.interStudent = interStudent;
	}

}
