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
    
    
    <script type="text/javascript">
     	//用来跳转到维护操作员模块的action
		function gotoShowAction(){
			location.href="managerAction!showOperator";
		}
		//用来跳转到退出系统的action
   		function gotoExitAction(){
   			location.href="exitAction";
   		}
  	</script>
  	
  	
	<script type="text/javascript">
	  //根据培训机构名字来获取培训计划的开始时间信息
      function getStartTimes(){
    	  //console.log("测试进入获取开始时间");
    	  var temp=1;
    	  var agencyName= $("#agencyName").val();
    	  console.log(agencyName);
    	  if(agencyName!=null){
    		  $.ajax(			    		
	    	      {
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/InternationalSys/background/attendTrainingAction!getStartTimes",
	    	    	  data:{agencyName:agencyName},
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){			
	    	    		 var html="";
	    	    		 html=html +'<option selected>--请选择培训开始时间--</option>';
	                     for(var i=0; i<data.length; i++){
	                   	   for(var j=0; j<i; j++){
	                   		 if(data[i].startTime==(data[j].startTime)){
	                   			 temp=0;
	                   			 break;
	                   	   	 }
	                   	   }
	                   	   if(temp==1){
	                   		 html=html +'<option value=""+ data[i].startTime+"">'+data[i].startTime.substring(0,10)+'</option>';
	                   	   }
	                   	   temp=1;
	                     }
	                     $('#startTime').html(html);
	                   },
	                   error: function(data){
	               		 var html="";
	  	    				 $('#startTime').html(html);
	                   }
	    	    	}			    	      
	    	  	);
    	  }else{
    		  alert("请选择一条数据!");
    	  }
      }
      
      //根据培训机构名字来获取培训计划的结束时间信息
      function getEndTimes(){
    	  //console.log("测试进入获取结束时间");
    	  var temp=1;
    	  var agencyName= $("#agencyName").val();
    	  console.log(agencyName);
    	  if(agencyName!=null){
    		  $.ajax(			    		
	    	      {
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/InternationalSys/background/attendTrainingAction!getEndTimes",
	    	    	  data:{agencyName:agencyName},
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){			
	    	    		 var html="";
	    	    		 html=html +'<option selected>--请选择培训结束时间--</option>';
	                     for(var i=0; i<data.length; i++){
	                   	   for(var j=0; j<i; j++){
	                   		 if(data[i].endTime==(data[j].endTime)){
	                   			 temp=0;
	                   			 break;
	                   	   	 }
	                   	   }
	                   	   if(temp==1){
	                   		 html=html +'<option value=""+ data[i].endTime+"">'+data[i].endTime.substring(0,10)+'</option>';
	                   	   }
	                   	   temp=1;
	                     }
	                     $('#endTime').html(html);
	                   },
	                   error: function(data){
	               		 var html="";
	  	    				 $('#endTime').html(html);
	                   }
	    	    	}			    	      
	    	  	);
    	  }else{
    		  alert("请选择一条数据!");
    	  }
      }
      
      //根据培训机构名字来获取培训计划的课时信息
      function getAllCourseHours(){
    	  //console.log("测试进入获取培训课时");
    	  var temp=1;
    	  var agencyName= $("#agencyName").val();
    	  console.log(agencyName);
    	  if(agencyName!=null){
    		  $.ajax(			    		
	    	      {
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/InternationalSys/background/attendTrainingAction!getAllCourseHours",
	    	    	  data:{agencyName:agencyName},
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){			
	    	    		 var html="";
	    	    		 html=html +'<option selected>--请选择培训课时--</option>';
	                     for(var i=0; i<data.length; i++){
	                   	   for(var j=0; j<i; j++){
	                   		 if(data[i].courseHours==(data[j].courseHours)){
	                   			 temp=0;
	                   			 break;
	                   	   	 }
	                   	   }
	                   	   if(temp==1){
	                   		 html=html +'<option value=""+ data[i].courseHours+"">'+data[i].courseHours+'</option>';
	                   	   }
	                   	   temp=1;
	                     }
	                     $('#courseHours').html(html);
	                   },
	                   error: function(data){
	               		 var html="";
	  	    				 $('#courseHours').html(html);
	                   }
	    	    	}			    	      
	    	  	);
    	  }else{
    		  alert("请选择一条数据!");
    	  }
      }
    
      //根据培训机构名字来获取培训计划费用信息
      function getAllCourseFee(){
    	  //console.log("测试进入获取开始时间");
    	  var temp=1;
    	  var agencyName= $("#agencyName").val();
    	  console.log(agencyName);
    	  if(agencyName!=null){
    		  $.ajax(			    		
	    	      {
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/InternationalSys/background/attendTrainingAction!getAllCourseFee",
	    	    	  data:{agencyName:agencyName},
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){			
	    	    		 var html="";
	    	    		 html=html +'<option selected>--请选择培训费用--</option>';
	                     for(var i=0; i<data.length; i++){
	                   	   for(var j=0; j<i; j++){
	                   		 if(data[i].courseFee==(data[j].courseFee)){
	                   			 temp=0;
	                   			 break;
	                   	   	 }
	                   	   }
	                   	   if(temp==1){
	                   		 html=html +'<option value=""+ data[i].courseFee+"">'+data[i].courseFee+'</option>';
	                   	   }
	                   	   temp=1;
	                     }
	                     $('#courseFee').html(html);
	                   },
	                   error: function(data){
	               		 var html="";
	  	    				 $('#courseFee').html(html);
	                   }
	    	    	}			    	      
	    	  	);
    	  }else{
    		  alert("请选择一条数据!");
    	  }
      }
	</script>
  	
    <!-- 添加学生参与培训计划-->
	<script type="text/javascript">
	      function onSubmit(){
	    	  console.log("测试有无进入");
	    	  var studentId = $("#studentNo").val();	
	    	  var studentName = $("#studentName").val();	
	    	  var className = $("#className").val();		    	
	    	  var major = $("#profession").val();			    	 
	    	  var agencyName = $("#agencyName").val();			    	 
	    	  var startTime = $("#startTime").find("option:selected").text();
	    	  var endTime= $("#endTime").find("option:selected").text();			    	
	    	  var courseHours = $("#courseHours").find("option:selected").text();			    	
	    	  var courseFee = $("#courseFee").find("option:selected").text();
	    	  /* alert("测试:"+studentId+","+studentName+","+className+","+major+","
	    			  +agencyName+","+startTime.substring(0,10)+","+endTime.substring(0,10)+","
	    			  +courseHours+","+courseFee); */
    		  $.ajax(		  
	    	      {
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/InternationalSys/background/attendTrainingAction!addAttendTraining?studentId="
	    	    			  	+studentId+"&agencyName="+agencyName+"&startTime="+startTime.substring(0,10)+"&endTime="+endTime.substring(0,10)
	    	    			  	+"&courseHours="+courseHours+"&courseFee="+courseFee,
	    	    	  data:$("#addAttendForm").serialize(),
	    	    	  dataType:"json",	
	    	    	  async:false,
	    	    	  contentType:"application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  if(data!=null){
	    	    			  alert(data);
	    	    		  }
	                   }
	    	      }
	    	  );		    	  
	      }
	      
	      function getTraining(){
	    	  getStartTimes();
	    	  getEndTimes();
	    	  getAllCourseHours();
	    	  getAllCourseFee();
	      }
	  </script>
  </head>

  <body onload="getTraining()">
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
            </div>
      </header>      
      <!--header end-->

      <!--sidebar start-->
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
                           <li><a class="" href="trainingAction">维护雅思培训信息</a></li>     
                            <li><a class="" href="simulationExamAction">维护考试信息</a></li>     
                             <li><a class="" href="attendTrainingAction!showAttend">维护参与培训计划信息</a></li>  
                              <li><a class="" href="participateSimulationExamAction">维护参与考试信息</a></li>  
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
	                          <li><a class="" href="javascript:gotoShowAction();">维护操作员信息</a></li>                          
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
						<li><i class="fa fa-home"></i><a href="index.jsp">首页</a></li>
						<li><i class="icon_document_alt"></i>添加参与培训计划的信息</li>
					</ol>
				</div>
			</div>
			
			     <button type="button" class="btn btn-information" style="width:100px;height:30px;font-size:15px">
                            <a href="attendTrainingAction!showAttend"><b>返回上页</b></a></button>
              <!-- Form validations -->              
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                           <b> 添加参与培训计划的信息</b>
                          </header>
                          <div class="panel-body">
                              <div class="form">
                                <label  style="color:red">${ sessionScope.addUserError}</label>
                                  <form class="form-validate form-horizontal" id="addAttendForm" method="post" action="">                                                         
                                      <div class="form-group ">
                                           <div  style="margin-left:250px;margin-top:-10px">
	                                          <label for="cname" class="control-label col-lg-2"><b>学号</b><span class="required" style="color:red">*</span></label>
	                                          <div class="col-lg-10">
	                                              <input class="form-control" name="attendTraining.interStudent.studentId"  id="studentNo" onblur="getStudentInformation()" style="width:300px" type="text" required/>
	                                          </div>
                                          </div>
                                   </div>

                                   <div class="form-group ">
                                        <div  style="margin-left:250px;margin-top:-10px">
	                                       <label for="cname" class="control-label col-lg-2"><b>姓名</b><span class="required" style="color:red">*</span></label>
	                                       <div class="col-lg-10">
	                                           <input class="form-control" name="attendTraining.interStudent.studentName" id="studentName" style="width:300px" type="text" required/>
	                                       </div>
                                       </div>
                                   </div>
                                      
                              <div class="form-group ">
                              	<div  style="margin-left:250px;margin-top:-10px">
                                    <label for="cname" class="control-label col-lg-2"><b>班级</b><span class="required" style="color:red">*</span></label>
                                    <div class="col-lg-10">
                                        <input class="form-control" name="attendTraining.interStudent.classes.className"  id="className" style="width:300px" type="text" required/>
                                    </div>
                                 </div>
                              </div>
                              
		                           <div class="form-group ">
		                              <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>专业</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" name="attendTraining.interStudent.classes.major"  id="profession" style="width:300px" type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                    <div class="form-group ">
	                                    <div  style="margin-left:250px;margin-top:-10px">
	                                          <label for="cname" class="control-label col-lg-2"><b>雅思机构名称</b><span class="required" style="color:red">*</span></label>
	                                          <div class="col-lg-10">
	                                               <input class="form-control" name="attendTraining.interStudent.classes.major"  id="agencyName" style="width:300px" type="text"
	                                               		onchange="getTraining()" placeholder="必填项，供下面信息动态获取"  required/>
	                                          </div>
	                                     </div>
                                      </div>
                                      
                                      <div class="form-group ">
	                                      <div  style="margin-left:250px;margin-top:-10px">
	                                          <label for="cname" class="control-label col-lg-2"><b>培训开始的时间</b><span class="required" style="color:red">*</span></label>
	                                          <div class="col-lg-10">
	                                        	<%--       <sx:datetimepicker name="startTime" displayFormat="yyyy-MM-dd"/> --%>
	                                               <select id="startTime" name="attendTraining.training.startTime" style="width:300px;height:35px;border-radius:5px;-webkit-border-radius:3px;-moz-border-radius:3px;"
	                                          	onchange=""></select>
	                                          </div>
                                          </div>
                                      </div>
                                      
                                       <div class="form-group ">
	                                       <div  style="margin-left:250px;margin-top:-10px">
	                                          <label for="cname" class="control-label col-lg-2"><b>培训结束的时间</b><span class="required" style="color:red">*</span></label>
	                                          <div class="col-lg-10">
	                                             <%--  <sx:datetimepicker name="outTime" displayFormat="yyyy-MM-dd"/> --%>
	                                                 <select id="endTime" name="attendTraining.training.endTime" style="width:300px;height:35px;border-radius:5px;-webkit-border-radius:3px;-moz-border-radius:3px"
	                                                onchange=""></select>
	                                          </div>
                                          </div>
                                      </div>
                                                                           
                                       <div class="form-group ">
	                                       <div  style="margin-left:250px;margin-top:-10px">
	                                          <label for="cname" class="control-label col-lg-2"><b>培训课时</b><span class="required" style="color:red">*</span></label>
	                                          <div class="col-lg-10">
	                                               <select id="courseHours" name="attendTraining.training.courseHours" style="width:300px;height:35px;border-radius:5px;-webkit-border-radius:3px;-moz-border-radius:3px;"
	                                               		onchange="">
	                                               </select>
	                                          </div>
                                          </div>
                                      </div>
                                                        
                                     <div class="form-group ">
	                                     <div  style="margin-left:250px;margin-top:-10px">
	                                          <label for="cname" class="control-label col-lg-2"><b>培训费用</b><span class="required" style="color:red">*</span></label>
	                                          <div class="col-lg-10">
	                                              <select id="courseFee" name="attendTraining.training.courseFee" style="width:300px;height:35px;border-radius:5px;-webkit-border-radius:3px;-moz-border-radius:3px;"></select>
	                                          </div>
                                          </div>
                                      </div>        
                                      
                                      <div class="form-group">
	                                      <div  style="margin-left:550px;margin-top:-10px">
	                                          <div class="col-lg-offset-2 col-lg-10">
	                                              <button class="btn btn-primary" onclick="onSubmit()"><b>添加</b></button>
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
    <script src="js/js.js"  charset="UTF-8"></script>


<%--             <%
			     String s=(String)request.getAttribute("addInfor");
			     if(s!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=s%>");
				</script>
			<%
			     }
			%>
			 --%>

  </body>
</html>
