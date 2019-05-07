package com.international.action;

import com.international.dao.UserDao;
import com.international.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class logAction extends ActionSupport{

	private User user;
	private UserDao ud;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserDao getUd() {
		return ud;
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
	}

	public String execute( )throws Exception{
		System.out.println("username = "+user.getUserName());
		if(ud.checkLogin(user)){
			return "success";
		}else{
			return "input";
		}
	}
}
