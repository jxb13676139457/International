<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>国际合作交流管理系统</title>

    <!-- Bootstrap CSS -->    
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="css/elegant-icons-style.css" rel="stylesheet" />
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-img3-body">
  <div style="text-align:center; margin-top:10px; font-size:60px;color:white"><b>国际合作交流管理系统后台</b> </div>
    <div class="container">
      <form class="login-form" action="loginAction!login" method="post"
       style="margin-top:80px">    
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <label style="color:red"><s:fielderror/></label>
            <div class="input-group">
              <span class="input-group-addon"><i class="icon_profile"></i></span>
              <input type="text" name="admins.adminId" class="form-control" placeholder="用户名" required />
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" name="admins.password" class="form-control" placeholder="密码" required/>
            </div>
          <!--   <label class="checkbox">
                <span class="pull-right"> <a href="#"> Forgot Password?</a></span>
            </label>
            --> 
            <button class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
        </div>
      </form>
    </div>
  </body>
</html>
