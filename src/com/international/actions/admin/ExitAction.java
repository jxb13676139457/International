package com.international.actions.admin;

import java.util.Map;

import com.international.model.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExitAction extends ActionSupport {
	public String execute() {
		Map session = ActionContext.getContext().getSession();
		Admin admin = (Admin)session.get("admin");
		if(admin!=null) {
			session.remove("admin");
			System.out.println("清除session成功");
			return SUCCESS;
		}else {
			return INPUT;
		}
	}
}
