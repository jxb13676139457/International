package com.international.actions.admin;

import java.util.Map;

import com.international.dao.UserDao;
import com.international.model.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private Admin admins;
	UserDao ud;
	//int adminType;
	public Admin getAdmins() {
		return admins;
	}
	public void setAdmins(Admin admins) {
		this.admins = admins;
	}
	public UserDao getUd() {
		return ud;
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
//	public int getAdminType() {
//		return adminType;
//	}
//	public void setAdminType(int adminType) {
//		this.adminType = adminType;
//	}
	
	//登录
	public String login(){
		System.out.println("====="+admins.getAdminId()+","+admins.getPassword()+"=======");
		Admin admin = ud.checkLogin(admins);
		if(admin!=null){
			System.out.println(admin.getType());
			//adminType=admin.getType();
			Map session = ActionContext.getContext().getSession();
			//登录用户存入session
			session.put("admin",admin);
			session.put("userName",admin.getUserName());
			//用来开放菜单项而用
			session.put("adminType",admin.getType());
			System.out.println(admin.getUserName());
			addFieldError("loginTip","登陆成功");
			return SUCCESS;
		}else{
			addFieldError("loginTip","登陆失败，账户或密码错误");
			return INPUT;
		}
	}
	
	
}
