package com.international.model;

public class CollegeActivity {
	int activityId;	//国外院校交流活动ID
	String title;	//交流活动标题
	String content;	//交流活动内容
	String time;//交流活动时间
	String type;	//交流活动类型
	College coll;	//关系映射
	
	String reserves1;//备用字段
	String reserves2;
	String reserves3;
	
	public CollegeActivity() {
		
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public College getColl() {
		return coll;
	}

	public void setColl(College coll) {
		this.coll = coll;
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
