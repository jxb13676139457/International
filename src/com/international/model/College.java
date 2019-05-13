package com.international.model;

import java.util.Date;

public class College {
	int collegeId;			//国外院校ID
	String collegeName;		//国外院校名称
	String address;			//国外院校地址ַ
	String startTime;		//合作开始时间
	String type;			//合作类型
	String status;			//备注状态
	String country;			//国家
	String contactPerson;	//联系人
	String position;		//联系人职位
	String phone;			//联系电话
	String email;			//电子邮件
	
	String reserves1;		//备用字段
	String reserves2;
	String reserves3;
	
	public College() {
		
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
