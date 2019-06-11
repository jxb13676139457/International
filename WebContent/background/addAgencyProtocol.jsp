<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="img/favicon.png">
    
        <sx:head extraLocales="en-us,nl-nl,de-de"/>
    <title>国际合作交流管理系统</title>

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
    
    						<!-- 添加雅思协议-->
			<script type="text/javascript">
			
			      function toSubmit(){
			    	  
			    	  var formData = new FormData($( "#uploadProtocol" )[0]);  // 要求使用的html对象
			    		  $.ajax(		  
					    	      {
					    	    	  
					    	    	  type:"post",
					    	    	  url:"http://localhost:8080/InternationalSys/background/agencyAgreementAction!addObject",
					    	    	  /* data:{agencyName:agencyName,title:title,time:time,upload:upload,type:type}, */
					    	    	  data: formData,  
					    	    	  dataType:"json",	
					    	    	  async:false,
					    	          contentType: false,  
					    	          processData: false,  
					    	    	  success: function(data){
					    	    		  
					    	    		
					    	    		  if(data!=null){
					    	    			  alert(data);
					    	    		  }
					    	    		
					                   }
					  					    	  
					    	      }
					    	      			    	  
					    	  );

			      }
			</script>
     
     <script type="text/javascript">
     
     function toAgencyName(){
     
       var agencyName= $("#agencyName").find("option:selected").text(); 
       
       $("#agencyValue").val(agencyName);
     }
   
     </script>  	 
  </head>

  <body onload="getAgencyInformation()">
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
                         <span  style="color:white; font-size:20px"><s:property value="#session.userName"/></span>
                         <p class="caret"></p>
                     </a>
                     <ul class="dropdown-menu extended logout">
                         <div class="log-arrow-up"></div>
                         <li class="eborder-top">
                             <a href="profile.jsp"><i class="icon_profile"></i>个人信息</a>
                         </li>
                         <li>
                             <a href="exitAction"><i class="icon_key_alt"></i>退出登录</a>
                         </li>
                     </ul>
                 </li>
                 <!-- user login dropdown end -->
             </ul>
             <!-- notificatoin dropdown end-->
      </header>      
      <!--header end-->

      <!--sidebar start-->
             <!--侧边菜单栏-->
      <aside>
          <div id="sidebar"  class="nav-collapse " >
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">                
                  <li class="active">
                      <a class="" href="index.jsp">
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
                          <li><a class="" href="internationalStudentAction!showStudent">维护国际学生信息</a></li>
                          <li><a class="" href="overseasStudentAction!showStudent">维护出国生信息</a></li>
                          <li><a class="" href="exchangeStudentAction!showStudent">维护交换生信息</a></li>
                          <li><a class="" href="studentActivityAction!showStuActivity">维护学生活动信息</a></li>
                      </ul>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_desktop"></i>
                          <span>班级信息</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="internationClassAction">维护班级信息</a></li>
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
                          <li><a class="" href="trainingAction">维护雅思培训计划信息</a></li>     
                          <li><a class="" href="examAction">维护考试信息</a></li>     
                          <li><a class="" href="attendTrainingAction!showAttend">维护学生参与培训计划</a></li>  
                          <li><a class="" href="scoreAction">维护参与考试信息</a></li>  
                          <li><a class="" href="agencyAgreementAction">维护雅思协议信息</a></li>                    
                      </ul>
                  </li>  
                  
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_genius"></i>
                          <span>国外院校</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="collegeAction">维护国外院校信息</a></li>     
                          <li><a class="" href="collegeActivityAction">维护活动信息</a></li>  
                          <li><a class="" href="collegeAgreementAction">维护协议信息</a></li>                         
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
                          <li><a class="" href="exitAction"><span>退出登录</span></a></li>
                      </ul>
                  </li>
                  <!-- 动态开放此菜单项-->
					<c:choose>
						<c:when test="${ sessionScope.adminType eq '是'}">
							<li class="sub-menu">
		                      <a href="javascript:;" class="">
		                          <i class="icon_document_alt"></i>
		                          <span>操作员信息</span>
		                          <span class="menu-arrow arrow_carrot-right"></span>
		                      </a>
		                      <ul class="sub">
		                          <li><a class="" href="managerAction!showOperator">维护操作员信息</a></li>                          
		                      </ul>
		                    </li>    
						</c:when>
					</c:choose>
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
						<li><i class="icon_document_alt"></i>添加雅思协议的信息</li>
					</ol>
				</div>
			</div>
			
			     <button type="button" class="btn btn-information" style="width:100px;height:30px;font-size:15px">
                            <a href="agencyAgreementAction"><b>返回上页</b></a></button>
              <!-- Form validations -->              
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                         <b>添加雅思协议的信息</b>
                          </header>
                          <div class="panel-body">
                              <div class="form">
                                <label  style="color:red">${ sessionScope.addUserError}</label>
                                
                                  <form class="form-validate form-horizontal" id="uploadProtocol" method="post" action="" enctype="multipart/form-data">                                                         
                                      
                                      
                                 
                                                <input class="form-control" id="agencyValue" name="agencyName"  type="hidden" style="width:300px" required/>                                   
                                
                                      
                                        <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>雅思机构</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                               <select id="agencyName" style="width:300px;height:35px;border-radius:5px;-webkit-border-radius:3px;-moz-border-radius:3px;"
		                                        onchange="toAgencyName()" > </select>
                                          </div>
                                          </div>
                                      </div>
                                      
                                         <div class="form-group ">
                                              <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>主题</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                             <input class="form-control" id="title" name="addAgreement.title"  type="text" style="width:300px" required/>                                             
                                          </div>
                                          </div>
                                      </div>
                                      
                                       <div class="form-group ">
                                                   <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>签订的时间</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <sx:datetimepicker name="addAgreement.time" id="time" displayFormat="yyyy-MM-dd"/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                    <div class="form-group ">
                                           <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>上传协议</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <s:file name="upload" id="upload"></s:file>
                                          </div>
                                          </div>
                                      </div>
                                                                           
                                       <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>协议的类型</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                                <!--<select name="addAgreement.type"  id="type" style="width:300px;height:35px;border-radius:5px;-webkit-border-radius:3px;-moz-border-radius:3px;"required>
                                                    <option selected>宣讲</option>
                                                    <option>翻译</option>
                                                    <option>其他类型</option>
                                                 
                                                    
                                              </select>-->
                                              <input class="form-control" id="type" name="addAgreement.type"  type="text" style="width:300px" required/>
                                          </div>
                                          </div>
                                      </div>
                                                        
                                        
                                      
                                      <div class="form-group">
                                          <div  style="margin-left:550px;margin-top:-10px">
                                          <div class="col-lg-offset-2 col-lg-10">
                                              <button class="btn btn-primary" onclick="toSubmit()"><b>添加</b> </button>
                                          </div>
                                      </div>
                                      </div>
                                  </form>

           
                                                      
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
    <!-- jquery validate js -->
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>

    <!-- custom form validation script for this page-->
    <script src="js/form-validation-script.js"></script>
    <!--custome script for all page-->
    <script src="js/scripts.js"></script>    
    <script src="js/js.js"  charset="utf-8"></script>

<%-- 
            <%
			     String s=(String)request.getAttribute("addInfor");
			     if(s!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=s%>");
				</script>
			<%
			     }
			%> --%>
			

  </body>
</html>
