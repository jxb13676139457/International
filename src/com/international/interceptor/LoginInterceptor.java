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
			return "login"; 
		}	
	}
	
	/*public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("------:进来拦截器了！--1");
        //获取session
        HttpSession session = request.getSession(true);
        //判断用户ID是否存在，不存在就跳转到登录界面
        if(session.getAttribute("userName") == null){
            System.out.println("------:跳转到login页面！");
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }else{
            session.setAttribute("userName", session.getAttribute("userName"));
            return true;
        }
    }*/


}
