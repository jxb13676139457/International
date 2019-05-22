package com.international.common;

import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.international.model.Admin;

public class LoginFilter implements Filter{

	 private static final long serialVersionUID = 1L;

	  @Override
	  public void destroy() {
	      // TODO Auto-generated method stub
	      
	  }

	  @Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	          throws IOException, ServletException {
	      //转化为HttpServlet的请求响应对象
	      HttpServletRequest req = (HttpServletRequest)request;
	      HttpServletResponse res = (HttpServletResponse)response;
	      //获取访问路径地址
	      String uri = req.getRequestURI();
	      //String的endsWith()用来测试字符串是否以指定的后缀结尾
	      if(uri.endsWith("login.jsp")){
	          chain.doFilter(req,res);
	          return;  //这里的return一定要加上，否则会不断死循环请求重定向
	      }
	      //获取session存的值
	      Admin admin = (Admin)req.getSession().getAttribute("admin");
	      //System.out.println(userName);
	      if(admin==null){
	          System.out.println("未登录用户被拦截在登录注册页面");
	          res.sendRedirect("login.jsp");   
	      }
	      //放行
	      chain.doFilter(req,res);
	  }

	  @Override
	  public void init(FilterConfig filterConfig) throws ServletException {
	      // TODO Auto-generated method stub
		  FileImageOutputStream imageOutput = null;
	      System.out.println("跟随tomcat启动而自动运行");
//	      try {
//	    	  imageOutput = new FileImageOutputStream(new File("E:\\eclipse for J2EE\\International\\WebContent\\background\\img\\verifyCode"));
//	    	  VerifyCode code = new VerifyCode();
//	    	  code.output(imageOutput);
//	    	  System.out.println("图片生成成功");
//	      }catch(IOException e) {
//		    	 e.printStackTrace();
//		  }
	  }

}
