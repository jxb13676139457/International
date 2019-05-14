package com.international.actions.admin;

import java.util.Map;

import com.international.dao.UserDao;
import com.international.model.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private Admin admins;
	UserDao ud;
	
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
	
	//登录
	public String login(){
		System.out.println("====="+admins.getAdminId()+","+admins.getPassword()+"=======");
		Admin admin = ud.checkLogin(admins);
		if(admin!=null){
			Map session = ActionContext.getContext().getSession();
			//登录用户存入session
			session.put("admin",admin);
			System.out.println(admin.getUserName());
			addFieldError("loginTip","登陆成功");
			return SUCCESS;
		}else{
			addFieldError("loginTip","登陆失败，账户或密码错误");
			return INPUT;
		}
	}
	
	
}
