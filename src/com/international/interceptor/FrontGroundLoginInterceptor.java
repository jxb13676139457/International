package com.international.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class FrontGroundLoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		
		Map session=ai.getInvocationContext().getSession();

		String studentName=(String)session.get("loginUser");
		String adminName=(String) session.get("loginUser2");
		String teacherName=(String) session.get("loginUser3");
		System.out.println("studentName = "+studentName);
		System.out.println("adminName = "+adminName);
		System.out.println("teacherName = "+teacherName);
		if(studentName!=null || adminName!=null || teacherName!=null){
			System.out.println("session有效，用户不为空");
			return ai.invoke();
		}else{ 
			ActionContext ac=ai.getInvocationContext();
			ac.put("errorMessage","用户未登录，请先登录。");
			System.out.println("用户session过期，被转发到登录界面");
			return "frontgroundlogin"; 
		}	
	}
}
