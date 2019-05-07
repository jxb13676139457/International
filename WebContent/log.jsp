<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>登录</h3>
	<s:form action="logAction" method="post">
        <s:textfield name="user.UserName" label="name"></s:textfield> 
        <s:password name="user.Password" label="password"></s:password>            
        <s:submit value="Login"></s:submit>
    </s:form>
</body>
</html>