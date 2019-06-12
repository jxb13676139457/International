package com.international.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		
		Map session=ai.getInvocationContext().getSession();

		String userName=(String)session.get("userName");
		if(userName!=null){
			System.out.println("session有效，用户不为空");
			return ai.invoke();
		}else{ 
			ActionContext ac=ai.getInvocationContext();
			ac.put("errorMessage","用户未登录，请先登录。");
			System.out.println("用户session过期，被转发到登录界面");
			return "login"; 
		}	
	}

}
