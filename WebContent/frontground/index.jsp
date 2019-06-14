<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html dir="ltr" lang="en-US" >
<head>
<meta charset="UTF-8" />
<meta name="robots" content="index, follow" />

<!-- page title -->
<title>国际合作交流管理系统</title>

    <sx:head extraLocales="en-us,nl-nl,de-de"/>


<!-- add css stylesheets -->	
<link rel="stylesheet" href="css/style.css"/>
<link rel="stylesheet" href="css/base.css"/>
<link rel="stylesheet" href="css/skeleton.css"/>
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen"/>

<!-- Pretty Photo -->
 <link rel="stylesheet" type="text/css" href="css/prettyPhoto.css"/> 

 <link rel="stylesheet" href="css/bootstrap.min.css">




<!-- mobile setting -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<!-- Favicon -->
<link rel="shortcut icon" href="images/favicon.html">


<!-- jquery -->
   <script src="js/jquery-3.2.1.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="js/jquery.carouFredSel-6.1.0-packed.js" type="text/javascript"></script>
<script src="js/jquery.tipsy.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/jquery.iconshadow.js" type="text/javascript"></script>
<script src="js/jquery.flexslider-min.js" type="text/javascript"></script>
<script src="js/superfish.js" type="text/javascript"></script>
<script src="js/jquery.color.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>
<script src="js/jquery.cycle.all.js"  type="text/javascript"></script>
<script src="js/modernizr.custom.js"  type="text/javascript"></script>
<script src="js/jquery.fitvids.js"  type="text/javascript"></script>
<script src="js/jquery.ui.totop.js"  type="text/javascript"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="js/jquery.twitter.js"></script>


    
   <style>
         span{
            //float:right;
         }
   </style>
</head>
<body>	
<!-- Image Back ground and pattern Back ground  -->
    <!-- <div id="pattern_bg"></div>  -->
     <img src="images/large/bg.jpg" alt="" id="background" />
	<div class="header_top_first" style="height:50px">
		<div class="container sixteen columns header_top_inner">
	    	<div class="eight columns align_left">
				<div class="testimonials" style="margin-left:30px;margin-top:20px">
		            <div class="testi_icon" ></div>
                    <ul>
				 	 	<li>
	               			<div class="testi-text dro" style="font-size:30px;">北京理工大学珠海学院</div>                                  
	                    </li>
						<li>	
	                  		<div class="testi-text dro"  style="font-size:30px">计算机学院</div>  
						</li>
                    </ul>						
                </div>			
			</div>
		
				<div class="top_info">
					<p id="cotr" style="margin-right:90px">
                  <s:set name="studentName" value="#session.loginUser.studentName"></s:set> 
                       <s:set name="name" value="#session.loginIn"></s:set> 
		             	 <s:if test="#name != null"> 
		             	 	<script>
		             	 		$('#cotr').css('margin-right','255px');
		             	 	</script>
		             	     <s:if test="#studentName != null"> 
		             	          <li class="dropdown" id="accountmenu">
		             	          <s:if test='#session.loginUser.sex=="男"'>
					                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-size:20px;color:white; border-radius:50%">
						                    <img alt="" src="images/boy.jpg" class="img-circle" style="border-radius:50% "> &nbsp;&nbsp;  <b>欢迎：<s:property value="#session.loginUser.studentName" /></b>
				             	  		</a>
				             	  </s:if>
				             	  <s:else>
				             	     <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-size:20px;color:white; border-radius:50%">
					                    <img alt="" src="images/girl.jpg" class="img-circle" style="border-radius:50% ">&nbsp;&nbsp; <b>欢迎：<s:property value="#session.loginUser.studentName" />
				             	  		</b></a>
				             	  </s:else>
					                    <ul class="dropdown-menu">
					                      	<li ><a href="updatePassword.jsp"><b>修改密码</b></a></li>
												<li class="divider"></li>
												<li> <a href="userAction!exitFront"><b>注销</b></a> </li>
					                    </ul>
					                </li>
		             	     </s:if>
		             	     
		             	     <s:elseif test="#session.loginUser3.teacherName!=null">
							     <li class="dropdown" id="accountmenu">
					               <s:if test='#session.loginUser3.sex=="男"'>
					                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-size:20px;color:white; border-radius:50%">
					                    <img alt="" src="images/boy.jpg" class="img-circle" style="border-radius:50%;"> &nbsp;&nbsp;  <b>欢迎：<s:property value="#session.loginUser3.teacherName" />
				             	  		</b></a>
				             	  </s:if>
				             	  <s:else>
				             	     <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-size:20px;color:white; border-radius:50%">
					                    <img alt="" src="images/girl.jpg" class="img-circle" style="border-radius:50% ">&nbsp;&nbsp;   <b>欢迎：<s:property value="#session.loginUser3.teacherName" />
				             	  </b></a>
				             	  </s:else>
					                    <ul class="dropdown-menu">
					                      	<li ><a href="updatePassword.jsp"><b>修改密码</b></a></li>
												<li class="divider"></li>
												<li> <a href="userAction!exitFront"><b>注销</b></a> </li>
					                    </ul>
					                </li>
		             	     </s:elseif>
		             	     
		             	     <s:else>
							     <li class="dropdown" id="accountmenu">
					                    <s:if test='#session.loginUser2.sex=="男"'>
					                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-size:20px;color:white; border-radius:50%">
					                    <img alt="" src="images/boy.jpg" class="img-circle" style="border-radius:50% "> &nbsp;&nbsp;  <b>欢迎：<s:property value="#session.loginUser2.userName" />
				             	  </b></a>
				             	  </s:if>
				             	  <s:else>
				             	     <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-size:20px;color:white; border-radius:50%">
					                    <img alt="" src="images/girl.jpg" class="img-circle" style="border-radius:50% ">&nbsp;&nbsp;   <b>欢迎：<s:property value="#session.loginUser2.userName" />
				             	  </b></a>
				             	  </s:else>
					                    <ul class="dropdown-menu">
					                      	<li ><a href="updatePassword.jsp"><b>修改密码</b></a></li>
												<li class="divider"></li>
												<li> <a href="userAction!exitFront"><b>注销</b></a> </li>
					                    </ul>
					                </li>
		             	     </s:else>
		             	  </s:if>
		             	  
		             	   <s:else> 
								<span style="display:block; margin-top:2px;">
									<a href="login.jsp"  style="font-size:15px;" class="btn btn-default">登录</a>
								</span>
		             	   		<style>
		             	   			.btn-default{
		             	   				background-color:rgb(51,51,51);
		             	   			}
		             	   		</style>
		             	   </s:else>
		               </p>
				</div>
        </div>  
  <div class="clear"></div>		
</div>

<div class="header_top_second">

<div class="container sixteen columns header_top_second_inner">

<div class="four columns  logo" ><a href="#"  style="margin-left:40px"><img src="images/logo3.png" id="logo"  /></a></div>
    
 <div class="contact_head" style="float:left;color:white;margin-left:-125px;margin-top:10px" >
 <b style="font-size:40px">国际合作交流管理系统</b>
</div>

</div>
<div class="clear"></div>
</div>
		
<!-- Main header -->
<div id="main_wrapper">
	<div class="main_wrapper_inner">
      
      <!-- Header -->
              <div class="header_menu">
			     <div class="header_top_inner">
				    <div class="container sixteen columns">
				         <!-- begin navigation -->
					 <div class="main_menu" >
						<nav id="dropdown" style="margin-top:12px;margin-left:-15px;width:1200">
							<ul class="sf-menu clearfix" >								
								<li class="change"><a href="beforeInformation.jsp"
										class="trigger"><span><i class="icon-home"></i><b
												style="font-size: 16px">首页</b></span></a></li>
									<li class="change"><a href="priorNewsAction" class="trigger"><span><i
												class="icon-staff"></i><b style="font-size: 16px">新闻中心</b></span></a></li>
									<li class="change"><a href="priorNoticeAction" class="trigger"><span><i
												class="icon-service"></i><b style="font-size: 16px">通知公告</b></span></a>
	
									</li>
									<li class="change"><a href="priorPolicyAction" class="trigger"><span><i
												class="icon-port"></i><b style="font-size: 16px">政策法规</b></span></a></li>
	
									<li class="change"><a href="#" class="trigger"><span><i
												class="icon-feature"></i><b style="font-size: 16px">国外院校</b></span></a>
										<ul>
											<li><a
												href="priorAbroadCollegeAction!getAbroadCollegeInfor?status=1"><b>国外院校信息</b></a></li>
											<li><a href="priorAbroadCollegeAction?status=1"><b>国外院校活动</b></a></li>
											<li><a
												href="priorAbroadCollegeAction!getAbroadCollegeProtocolInfor?status=1"><b>国外院校协议</b></a></li>
										</ul>
									</li>
										
								<s:if test='#name!=null'>
									<li>
										<a href="priorAbroadCollegeProtocolAction" class="trigger"><span><i class="icon-blog"></i><b  style="font-size:16px">相关下载</b></span></a>
									</li>
									<!-- 如果是学生 -->
				                    <s:if test='#session.type=="student"'>
				                        <li><a href="priorStudentAction!showPersonById" class="trigger"><span><i class="icon-staff"></i><b style="font-size:16px">个人信息</b></span></a></li>	
                                        <li><a href="priorExamAction!studentAgencySearch" class="trigger"><span><i class="icon-service"></i><b style="font-size:16px">雅思信息</b></span></a></li>	
                                
			                    	</s:if>
			                    	<!-- 如果是老师或其他 -->
			                    	<s:else>
			                    		<li><a href="#" class="trigger"><span><i class="icon-feature"></i><b style="font-size:16px">学生信息</b></span></a>
											<ul>
												<li><a href="priorStudentAction!showInterStudent"><b>国际学生</b></a></li>
												<li><a href="priorStudentAction!showOverseasStudent"><b>出国学生</b></a></li>
												<li><a href="priorStudentAction!showExchangeStudent"><b>交换生</b></a></li>
												<li><a href="priorStudentAction!showStudentActivity"><b>学生活动</b></a></li>
											</ul>
								         </li>
								         
								         	<li><a href="#" class="trigger"><span><i class="icon-feature"></i><b style="font-size:16px">雅思信息</b></span></a>
											<ul>
												<li><a href="priorEnglishAgencyAction?status=1"><b>雅思机构协议信息</b></a></li>
												<li><a href="priorEnglishAgencyAction!getParticipateTrainingInfor?status=1"><b>雅思培训计划信息</b></a></li>
												<li><a href="priorExamAction!otherExamSearch"><b>雅思模拟考试信息</b></a></li>
												<li><a href="priorExamAction!otherScoreSearch"><b>雅思正式考试信息</b></a></li>
											</ul>
								         </li>
			                    	</s:else>	
			                    	</s:if>	
			                    	<s:else>
			                    		<style>
			                    			.change{
			                    				width:200px;
			                    			}
			                    			.change span{
			                    				margin-left:35px;
			                    			}
			                    		</style>
			                    	</s:else>
								   <li></li>
							</ul>
						</nav>	
                       </div>						
				 <!-- end navigation -->
					</div> <!-- End six teen columns -->
			     </div>
			  </div> <!-- End header top -->
	 <br/>
 	<div class="slider_wrapper">
		      
            <div class="flexslider">
						<ul class="slides">
							<li>
								<img src="images/slides/bg.jpg" alt="header"/>
								<!-- <div class="flex-caption">Grandon Multi-Purpose HTML Theme available only on Themeforest</div>
							 -->
							</li>
							<li>
								<img src="images/slides/bg2.jpg" alt="header"/>
								<!-- <div class="flex-caption">The best place you must visit, At 23 North Frankand, Canada</div>
						    -->
						    </li>	
							<li>
								<img src="images/slides/bg3.jpg" alt="header"/>
								<!-- <div class="flex-caption">Welcome to Grandon land the romantic place in the world</div> -->
						    </li>								
						</ul>	
               <div class="clear"></div>
                  						
			 </div>	
	         <div></div>
            </div> 	
    
	
    <div class="content">		
				
	 <div class="content_wrapper">	
		   
		 <div class="first_wrapper_bg">	
          <div class="container"> 		 
			   <div class="service_wrapper_holder">	
                 			   
				   <div class="service_wrapper">
				     <div class="four columns">
					    <div class="image_shadow">
					      <a href="priorNewsAction"><img src="images/icons/s1.png" class="scale-with-grid"/></a>
						</div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorNewsAction"><b>新闻中心</b> </a></h5>
						  
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->

                   <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="priorNoticeAction"><img src="images/icons/s2.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorNoticeAction"> <b>通知公告</b> </a></h5>
						 
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->

                   <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="priorPolicyAction"><img src="images/icons/s4.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorPolicyAction"> <b> 政策法规</b>  </a></h5>
						
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->

                   <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="priorAbroadCollegeProtocolAction"><img src="images/icons/s3.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorAbroadCollegeProtocolAction"> <b>协议下载</b> </a></h5>
						
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->
                 <div class="clear"></div>
		        </div><!-- End service wrapper holder -->
				</div>             
		 </div>  <!-- End first wrapper -->
		 <div class="content_shadow"></div>
	<div class="clear"></div>	 
	

<div class="container">

 <div class="port_wrapper_home">
			        					
     <div class="port_inner">
	   <div class="sixteen columns">
	       <div class="pb_title_wrapper">
		       <h1 class="pb_title"><b>最新信息</b></h1>
               <div class="clear"></div>
            				 
          </div>  
       </div>
	   			    

	<ul id="carousel-works" class="portfolio group"> 
		<li class="four columns">
			  <div class="panel panel-default " style="width:220px">
						    <div class="panel-heading">
						        <h3 class="panel-title" style="font-size:20px">
						           <b>  新闻中心</b>
						           <a href="priorNewsAction"><span  class="more">more>></span></a>
						        </h3>
						    </div>
						   <s:iterator value="newsList" var="user" status="st">
						    <div class="panel-body"> 
		            
						            <a href="<s:property value="#user.newsUrl"/>">
                                  <b> <s:property value="#user.title"/></b></a>                         
						         <span><s:property value="#user.time"/>  </span>
						    </div>					
						  </s:iterator>
			           </div>
		</li> 
		<li class="four columns" style="margin-left:-40px">
			 <div class="panel panel-default" style="width:220px">
						    <div class="panel-heading">
						        <h3 class="panel-title" style="font-size:20px">
						           <b>通知公告</b>
						                                      
						              <a href="priorNoticeAction"><span class="more" id="more">more>></span></a>                          
						        </h3>
						    </div>
						    
						   <s:iterator value="noticeList" var="user" status="st">
							    <div class="panel-body">
							            
							     
							         <a href="priorNoticeAction!getInformationByTitle.action?title=<s:property value="#user.title"/>&time=<s:property value="#user.time"/>">
                                  <b><s:property value="#user.title" /></b> </a> 
                                  
							         <span><s:property value="#user.time"/>  </span>
							    </div>
							   
						  </s:iterator>
						  
			      </div>
		</li>
		<li class="four columns" style="margin-left:-80px">

			  <div class="panel panel-default" style="width:250px">
						    <div class="panel-heading">
						        <h3 class="panel-title" style="font-size:20px">
						            <b> 政策法规</b> 
						             <a href="priorPolicyAction"><span  class="more">more>></span></a>
						        </h3>
						    </div>
						    <s:iterator value="policyList" var="user" status="st">
							    <div class="panel-body">
							            
							         <a href="priorPolicyAction!getInformationByTitle.action?title=<s:property value="#user.title"/>&time=<s:property value="#user.time"/>">
                                 <b> <s:property value="#user.title"/></b> </a>
							                         
							         <span><s:property value="#user.time"/>  </span>
							    </div>
							   
						  </s:iterator>
			   </div>

		</li>
		<li class="four columns" style="margin-left:-100px">
		 <div class="panel panel-default"  style="width:250px">
						    <div class="panel-heading">
						        <h3 class="panel-title" style="font-size:20px">
						           <b>协议下载</b>
						             <a href="priorAbroadCollegeProtocolAction"><span>more>></span></a>
						        </h3>
						    </div>
						    <s:iterator value="abroadProtocolList" var="user" status="st">
							    <div class="panel-body">
							            
							          <a href="priorAbroadCollegeProtocolAction!getInformationByTitle.action?title=<s:property value="#user.title"/>&time=<s:property value="#user.time"/>">
                                 <b> <s:property value="#user.title"/></b></a>
							                                           
							         <span><s:property value="#user.time"/>  </span>
							    </div>
							   
						  </s:iterator>
				    </div>

		</li>


	
	</ul>
		<div class="clearfix"></div>
        </div>
			   
	 
    <div class="clear"></div>	
			   </div><!-- End Portfolio -->

	</div>  <!-- End port wrapper -->	

			
           </div> <!-- End Content wrapper -->
        </div> 	 <!-- End Contain -->	
    </div>	<!-- End content -->

    		<!-- Footer -->



          <!-- Sub footer -->
		
		<div id="subfooter_wrapper">
		   <div class="container">
    	    <div class="subfooter">
          <div style="text-align;font-size:30px;color:white;" >
	 		     Copyright ©北京理工大学珠海学院-国际交流合作处-All Rights Reserved.
	 		 </div>
			
            
            </div>
			</div>
        </div> <!-- End Sub footer -->
		
	</div> <!-- End main_wrapper_inner -->	   
</div> <!-- End main_wrapper -->
</body>
</html>