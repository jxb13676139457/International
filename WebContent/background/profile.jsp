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

    <title>个人信息</title>

    <!-- Bootstrap CSS -->    
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="css/elegant-icons-style.css" rel="stylesheet" />
    <link href="css/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
    
    	  <!-- 判断有没有重复名字的管理员-->
            <%
			     String s=(String)request.getAttribute("updateInfor");
			     if(s!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=s%>");
				</script>
			<%
			     }
			%>
			
    
  </head>
  <body>
  <!-- container section start -->
   <section id="container" class="">
     
    
      <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
            </div>

            <!--logo start-->
    <!--      <img src="img/logo3.png" style="width:100px;height:50px" class="col-sm-4" > -->
              <a href="index.jsp" class="logo"><span style="font-size:25px;color:white"><img src="img/logo3.png" id="logo" ><b>国际合作交流后端管理系统</b></span></a>
             
            <!--logo end-->              
 
                <ul>
                    <!-- alert notification end-->
                    <!-- user login dropdown start-->
                    <li class="dropdown pull-right " style="margin-top:10px">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt="" src="img/avatar1_small.jpg">
                            </span>
                             <!-- <s:textfield name="userInfo.name"  disabled="true"></s:textfield> -->
                            <span  style="color:white; font-size:20px"><s:property value="#session.adminUser.userName"/></span>
                            <p class="caret"></p>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li class="eborder-top">
                                <a href="profile.jsp"><i class="icon_profile"></i>个人信息</a>
                            </li>
                            <li>
                                <a href="login.jsp"><i class="icon_key_alt"></i>退出登录</a>
                            </li>
                        </ul>
                    </li>
                    <!-- user login dropdown end -->
                </ul>
                <!-- notificatoin dropdown end-->
            </div>
      </header>      
      <!--header end-->

      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse " >
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">                
                  <li class="active">
                      <a class="" href="beforeInformation.jsp">
                          <i class="icon_house_alt"></i>
                          <span>首页</span>
                      </a>
                  </li>
                  		      
             
                               
                   <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_table"></i>
                          <span>学生信息</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="studentInformationAction">维护国际学生信息</a></li>
                           <li><a class="" href="overseasStudentAction">维护出国生信息</a></li>
                            <li><a class="" href="exchangeStudentAction">维护交换生信息</a></li>
                             <li><a class="" href="studentActivitiesAction">维护学生活动信息</a></li>
                      </ul>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_desktop"></i>
                          <span>班级信息</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="internationalClassAction">维护班级信息</a></li>
                      </ul>
                  </li>
                  
                   <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_document_alt"></i>
                          <span>雅思信息</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="englishAgencyAction">维护雅思机构信息</a></li> 
                           <li><a class="" href="trainingAction">维护雅思培训信息</a></li>     
                            <li><a class="" href="simulationExamAction">维护模拟考试信息</a></li>     
                             <li><a class="" href="formalExamAction">维护正式考试信息</a></li>   
                             <li><a class="" href="participateTrainingAction">维护参与培训计划信息</a></li>  
                              <li><a class="" href="participateSimulationExamAction">维护参与模拟考试信息</a></li>  
                               <li><a class="" href="participateFormalExamAction">维护参与正式考试信息</a></li>  
                               <li><a class="" href="agencyProtocolAction">维护雅思协议信息</a></li>                    
                      </ul>
                  </li>  

                  
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_genius"></i>
                          <span>国外院校</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="abroadCollegeAction">维护国外院校信息</a></li>     
                          <li><a class="" href="abroadCollegeActivitiesAction">维护活动信息</a></li>  
                          <li><a class="" href="abroadCollegeProtocolAction">维护协议信息</a></li>                         
                      </ul>
                  </li>                                                                         
                  
                   <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_piechart"></i>
                          <span>发布信息</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="noticeAction">维护通知信息</a></li>     
                          <li><a class="" href="newsAction">维护新闻信息</a></li>  
                          <li><a class="" href="policyAction">维护政策法规信息</a></li>                         
                      </ul>
                  </li>                                                                         
                  
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_documents_alt"></i>
                          <span>个人信息</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">                          
                          <li><a class="" href="profile.jsp">维护个人信息</a></li>
                          <li><a class="" href="login.html"><span>退出登录</span></a></li>
                      </ul>
                  </li>
                  
                   <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_document_alt"></i>
                          <span>操作员信息</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="loginUserInformationAction?status=1">维护操作员信息</a></li>                          
                      </ul>
                  </li>    
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="beforeInformation.jsp">首页</a></li>
						<li><i class="icon_documents_alt"></i>管理员信息</li>
					</ol>
				</div>
			</div>
              <div class="row">
                <!-- profile-widget -->
                <div class="col-lg-12">
                    <div class="profile-widget profile-widget-info">
                          <div class="panel-body">
                            <div class="col-lg-2 col-sm-2">
                              <h4><s:property value="#session.adminUser.name"/></h4>           
                              <div class="follow-ava">
                                  <img src="img/profile-widget-avatar.jpg" alt="">
                              </div>
                              <h6>管理员</h6>
                            </div>
                         <!--   <div class="col-lg-4 col-sm-4 follow-info">
                                <p>签名：<s:property value="#session.adminUser.introduce"/></p>
                            </div>--> 
                    
                        </div>
                    </div>
                </div>
              </div>
              <!-- page start-->
              <div class="row">
                 <div class="col-lg-12">
                    <section class="panel">
                          <header class="panel-heading tab-bg-info">
                              <ul class="nav nav-tabs">
                          
                                  <li class="active">
                                      <a data-toggle="tab" href="#edit-profile">
                                        <i class="icon_tag"></i>
                                                                                                                                               修改个人信息
                                      </a>
                                  </li>
                                  <li class="">
                                      <a data-toggle="tab" href="#profile">
                                          <i class="icon_menu-square_alt2"></i>
                                                                                                                                        个人信息
                                      </a>
                                  </li>                        
                                  
                              </ul>
                          </header>
                          <div class="panel-body">
                              <div class="tab-content">
                                  <!-- profile -->
                                  <div id="profile" class="tab-pane ">
                                    <section class="panel">
                                      <div class="bio-graph-heading">
                                          <b style="font-size:50px"> 国际合作交流管理系统管理员信息 </b>
                                       </div>
                                      <div class="panel-body bio-graph-info">
                                          <h1>管理员信息</h1>
                                          <div class="row">
                                              <div class="bio-row">
                                                  <p><span>用户名</span>: <s:property value="#session.adminUser.name"></s:property> </p>
                                              </div>                                                                                   
                                          
                                          
                                              <div class="bio-row">
                                                  <p><span>密码 </span>:  <s:property value="#session.adminUser.password"></s:property></p>
                                              </div>
                                              
                                                    <div class="bio-row">
                                                  <p><span>真实姓名 </span>:  <s:property value="#session.adminUser.userName"></s:property></p>
                                              </div>
                                      
                                          </div>
                                      </div>
                                    </section>
                                   
                                  </div>
                                  <!-- edit-profile -->
                                  <div id="edit-profile" class="tab-pane active">
                                    <section class="panel">                                          
                                          <div class="panel-body bio-graph-info">
                                              <h1>修改信息</h1>
                                              <form class="form-horizontal" action="updateAdminUserAction!updateAdmin.action" method="post">      
                                              
                                                  <label  style="color:red"><s:fielderror/></label>
                                               <div class="form-group">
                                                      <div class="col-lg-6">
                                                          <input type="hidden" name="updateUser.id"  value="${sessionScope.adminUser.id }" class="form-control" id="f-name" placeholder=" " />
                                                         
                                                      </div>
                                                  </div>                                            
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label">用户名<span style="color:red">*</span></label>
                                                      <div class="col-lg-6">
                                                          <input type="text" name="updateUser.name"  value="${sessionScope.adminUser.name }" class="form-control" id="f-name" placeholder=" "  required/>                                                     
                                                      </div>
                                               
                                                  </div>
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label">密码  <span style="color:red">*</span></label>
                                                      <div class="col-lg-6">
                                                          <input type="text" name="updateUser.password" class="form-control" id="l-name" placeholder=" " minLength="4" required/>
                                                      
                                                                
                                                      </div>
                                                  </div>
                                               
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label">真实姓名 <span style="color:red">*</span></label>
                                                      <div class="col-lg-6">
                                                          <input type="text" name="updateUser.userName"  value="${sessionScope.adminUser.userName }" class="form-control" id="email" placeholder=" " required>
                                                        
                                                      </div>
                                                  </div>                                            
                              
                                                  <div class="form-group">
                                                      <div class="col-lg-offset-2 col-lg-10">
                                                          <button type="submit" class="btn btn-primary">更新</button>
                                                      </div>
                                                  </div>
                                              </form>
                                          </div>
                                      </section>
                                  </div>
                        
                              </div>
                          </div>
                      </section>
                 </div>
              </div>

              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
  </section>
  <!-- container section end -->
    <!-- javascripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- nice scroll -->
    <script src="js/jquery.scrollTo.min.js"></script>
    <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
    <!-- jquery knob -->
    <script src="assets/jquery-knob/js/jquery.knob.js"></script>
    <!--custome script for all page-->
    
    <script src="js/scripts.js"></script>
  <script>

      //knob
      $(".knob").knob();

  </script>


  </body>
</html>
