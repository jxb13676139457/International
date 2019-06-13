<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>login</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <link rel="stylesheet" href="css/bootstrap.min.css">
   
       <!--提示查询错误信息 -->
            <%
			     String s=(String)session.getAttribute("fault");
			     if(s!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=s%>");
				</script>
			<%
			     session.setAttribute("fault",null);
			     }
			%>
			
		
		 <style>

		     
		    body {  
		    background-image: url(../images/xing5.jpg);
		    background-size: 100%;  
		    background-repeat: no-repeat;  
		}  
		  
		#login_frame {  
		    width: 400px;  
		    height: 300px;  
		    padding: 13px;  
		  
		    position: absolute;  
		    left: 50%;  
		    top: 50%;  
		    margin-left: -200px;  
		    margin-top: -200px;  
		  
		    background-color: rgba(240, 255, 255, 0.5);  
		  
		    border-radius: 10px;  
		    text-align: center;  
		}  
		  
		  
		#btn_login {  
		    font-size: 14px;  
		    font-family: 宋体;  
		  
		    width: 120px;  
		    height: 28px;  
		    line-height: 28px;  
		    text-align: center;  
		  
		    color: white;  
		    background-color: #3BD9FF;  
		    border-radius: 6px;  
		    border: 0;  
		  
		    float: left;  
		}  
		  
		#forget_pwd {  
		    font-size: 12px;  
		    color: white;  
		    text-decoration: none;  
		    position: relative;  
		    float: right;  
		    top: 5px;  
		  
		}  
		  
		#forget_pwd:hover {  
		    color: blue;  
		    text-decoration: underline;  
		}  
		  
		#login_control {  
		    padding: 0 28px;  
		}  
  </style>
		<script type="text/javascript">
			function test(){
				document.forms[0].method="post";
				document.forms[0].action="userAction";
				document.forms[0].submit();
			}
		</script>
  </head>
  
  <body>  
	<div id="login_frame">  
	    <p id="image_logo"></p>  
	   	<form>
	   	  <h2><b>国际合作交流管理系统</b></h2><br>
		  <label  style="color:red"><s:fielderror/></label>
		  <div class="input-group">
			  <span class="input-group-addon" id="basic-addon1"><p class="glyphicon glyphicon-user" style="height:10px"></p></span>
			  <input id="userName" name="name" type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1" >
			</div>
			<br>
			<div class="input-group">
			  <span class="input-group-addon" id="basic-addon1"><p class="glyphicon glyphicon-lock" style="height:10px"></p></span>
			  <input id="passWord" name="password" type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1" />
			</div>
			  <br>
	             <input type="radio" id="type" name="type" value="student" checked/><b>学生</b>
	             <input type="radio" id="type" name="type" value="other" style="margin-left:15px"/><b>其他</b>
			<button type="button" style="width:280px" class="btn btn-primary" onclick="test()"><b>登录</b></button>
		</form>
	</div>  
  
</body>  
</html>
