package com.international.actions.admin;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.international.dao.UserDao;
import com.international.model.Admin;
import com.opensymphony.xwork2.ActionSupport;
import com.international.common.ajaxAction;

public class AddOperatorAction extends ActionSupport{
	
	private List<Admin> admins;
	private Admin admin;
	private UserDao ud;
	private String loginUserName = "";  //筛选搜索的用户名
	
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public List<Admin> getAdmins() {
		return admins;
	}
	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public UserDao getUd() {
		return ud;
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
	}

	//添加管理员
	public void addLoginUser() throws IOException {
		int count = 0;
		String message = "";
		count = ud.addOperator(admin);
		System.out.println(count);
		if(count>0) {
			System.out.println("添加成功");
			message = "添加成功";
			//admins = ud.queryAdmins(loginUserName);
		}else{
			System.out.println("添加失败");
			message = "添加失败";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}

}
