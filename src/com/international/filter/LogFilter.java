package com.international.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter("/LogFilter")
public class LogFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		String UserName=(String) session.getAttribute("userName");
		String a=httpRequest.getRequestURI();
		System.out.println("url = "+a);
		if(a.equals("/InternationalSys/background/login.jsp")) {
			if(a.contains(".css") || a.contains(".js") || a.contains(".png")|| a.contains(".jpg")) {
				System.out.println("正常跳转");
				chain.doFilter(request, response);
			}
			chain.doFilter(request, response);
		}else if(UserName==null || UserName.equals("")){
			((HttpServletResponse)response).sendRedirect("login.jsp");
			System.out.println("用户名为空");				
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
