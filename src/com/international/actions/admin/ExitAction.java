package com.international.actions.admin;

import java.util.Map;

import com.international.model.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExitAction extends ActionSupport {
	public String execute() {
		Map session = ActionContext.getContext().getSession();
		session.clear();
		Admin admin = (Admin)session.get("admin");
		String userName = (String) session.get("userName");
		if(admin==null && userName==null) {
			System.out.println("清除session成功");
			return SUCCESS;
		}else {
			return INPUT;
		}
	}
}
