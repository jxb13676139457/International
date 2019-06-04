package com.international.model;

public class AgencyAgreement {
	int agreementId;	//合作机构协议ID
	String title;		//协议标题
	String time;		//协议签订时间
	String type;		//协议类型
	String fileName;	//协议文件名
	String savePath;	//文件保存路径
	Agency agen;		//关系映射,Agency(agencyId)
	
	String reserves1;//备用字段
	String reserves2;
	String reserves3;
	
	public AgencyAgreement() {
		
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
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
