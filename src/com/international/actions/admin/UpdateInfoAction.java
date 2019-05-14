package com.international.actions.admin;

import java.util.Map;

import com.international.dao.UserDao;
import com.international.model.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateInfoAction extends ActionSupport {
	
	private Admin admin;
	UserDao ud;
	private String oldPwd;
	private String newPwd;
	private String userName;
	
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
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//修改管理员信息
	public String updateAdmin() {
		Map session = ActionContext.getContext().getSession();
		admin = (Admin)session.get("admin");
		//System.out.println(admin.getUserName());
		if(ud.updateAdminInfo(admin,oldPwd,newPwd,userName)) {
			System.out.println("测试");
			return SUCCESS;
		}else {
			addFieldError("oldPwd","修改管理员信息失败");
			return INPUT;
		}
	}
}
