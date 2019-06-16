package com.international.frontground.actions.ielts;

import java.util.List;

import com.international.dao.AttendTrainingDao;
import com.international.model.AttendTraining;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.javafx.collections.MappingChange.Map;

public class priorEnglishAgencyTrainingAction extends ActionSupport {

	private List<AttendTraining> attendTrainings;
	private AttendTraining attendTraining;
	AttendTrainingDao atd;
	private String loginUserName = "";  //筛选搜索用户名
	java.util.Map<String, Object> m;
	public List<AttendTraining> getAttendTrainings() {
		return attendTrainings;
	}
	public void setAttendTrainings(List<AttendTraining> attendTrainings) {
		this.attendTrainings = attendTrainings;
	}
	public AttendTraining getAttendTraining() {
		return attendTraining;
	}
	public void setAttendTraining(AttendTraining attendTraining) {
		this.attendTraining = attendTraining;
	}
	public AttendTrainingDao getAtd() {
		return atd;
	}
	public void setAtd(AttendTrainingDao atd) {
		this.atd = atd;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	
	
	public String execute() {
		m=ActionContext.getContext().getSession();
		System.out.println("execute被调用");
		attendTrainings = atd.queryAttends(loginUserName);
		m.put("attendTrainings", attendTrainings);
		System.out.println("attendTrainings = "+attendTrainings);
		return SUCCESS;
	}
	
}
