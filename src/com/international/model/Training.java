package com.international.model;

public class Training {

	private int trainingId;
	private int courseHours;
	private String startTime;
	private String endTime;
	private int courseFee;
	private Agency agencies;		//雅思培训计划和雅思机构为多对一的关系
	private AttendTraining attendTraining;   //雅思培训计划和参与雅思培训计划为多对一的关系
	
	private String reserves1;
	private String reserves2;
	private String reserves3;

	public int getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}
	public int getCourseHours() {
		return courseHours;
	}
	public void setCourseHours(int courseHours) {
		this.courseHours = courseHours;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
	public Agency getAgencies() {
		return agencies;
	}
	public void setAgencies(Agency agencies) {
		this.agencies = agencies;
	}
	public AttendTraining getAttendTraining() {
		return attendTraining;
	}
	public void setAttendTraining(AttendTraining attendTraining) {
		this.attendTraining = attendTraining;
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
