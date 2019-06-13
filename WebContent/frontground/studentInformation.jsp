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
 <script src="js/bootstrap.min.js"></script>


<!-- mobile setting -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<!-- Favicon -->
<link rel="shortcut icon" href="images/favicon.html">


<!-- jquery -->
    <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery-3.2.1.min.js"></script>
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
         
         
            float:right;
         }
         
               .main-body_list ul li {
           margin-top:5px;
           display:block;
           height:35px;
           line-height: 35px;
           clear:both;
           border-bottom-color:silver;
           border-bottom-width: 1px;
           border-bottom-style: dashed;
           list-style-type:none;
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
					<p style="margin-right:280px">
                  <s:set name="studentName" value="#session.loginUser.studentName"></s:set> 
                       <s:set name="name" value="#session.name"></s:set> 
		             	 <s:if test="#name != null"> 
		             	     <s:if test="#studentName != null"> 
		             	       
                                 
		             	          <li class="dropdown" id="accountmenu">
		             	          
		             	          <s:if test='#session.loginUser.reserve2=="男"'>
					                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-size:20px;color:white; border-radius:50%">
					                    <img alt="" src="images/boy.jpg" class="img-circle" style="border-radius:50% "> &nbsp;&nbsp;  <b>欢迎：<s:property value="#session.loginUser.studentName" />
				             	  </b></a>
				             	  </s:if>
				             	  <s:else>
				             	     <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-size:20px;color:white; border-radius:50%">
					                    <img alt="" src="images/girl.jpg" class="img-circle" style="border-radius:50% ">&nbsp;&nbsp; <b>欢迎：<s:property value="#session.loginUser.studentName" />
				             	  </b></a>
				             	  </s:else>
					                    <ul class="dropdown-menu">
					                      	<li ><a href="updatePassword.jsp"><b>修改密码</b></a></li>
												<li class="divider"></li>
												<li> <a href="login.jsp"><b>注销</b></a> </li>
					                    </ul>
					                </li>
				             
							    
		             	     </s:if>
		             	     
		             	     <s:else>
			           
                              
						  
							     <li class="dropdown" id="accountmenu">
							       	 
					                    <s:if test='#session.loginUser.reserve1=="男"'>
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
												<li> <a href="login.jsp"><b>注销</b></a> </li>
					                    </ul>
					                </li>
				          
		             	     </s:else>
						
					    
					      
		             	  </s:if>
		             	   <s:else> 
		             	   <a href="login.jsp"  style="font-size:20px"><i class="icon_group"></i>登录</a>
		             	   
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
								<li><a href="beforeInformation.jsp" class="trigger"><span><i class="icon-home" ></i><b style="font-size:16px">首页</b></span></a>
						
								</li>
								<li><a href="priorNewsAction" class="trigger"><span><i class="icon-staff"></i><b  style="font-size:16px">新闻中心</b></span></a>
						
								</li>
								<li><a href="priorNoticeAction" class="trigger"><span><i class="icon-service"></i><b  style="font-size:16px">通知公告</b></span></a>
						
								</li>
								<li><a href="priorPolicyAction" class="trigger"><span><i class="icon-port"></i><b  style="font-size:16px">政策法规</b></span></a>
						
								</li>
								<li><a href="priorAbroadCollegeProtocolAction" class="trigger"><span><i class="icon-blog"></i><b  style="font-size:16px">相关下载</b></span></a>
						
								</li>
								<li><a href="#" class="trigger"><span><i class="icon-feature"></i><b style="font-size:16px">国外院校</b></span></a>
									<ul>
										<li><a href="priorAbroadCollegeAction!getAbroadCollegeInfor?status=1"><b>国外院校信息</b></a></li>
										<li><a href="priorAbroadCollegeAction?status=1"><b>国外院校活动</b></a></li>
										<li><a href="priorAbroadCollegeAction!getAbroadCollegeProtocolInfor?status=1"><b>国外院校协议</b></a></li>
								
									</ul>
								</li>

								 <s:if test='#name !=null'>
				                    <s:if test='#session.type=="student"'>
				                        <li><a href="priorStudentInformationAction!getOneStudnetInformation" class="trigger"><span><i class="icon-staff"></i><b style="font-size:16px">个人信息</b></span></a></li>	
                                        <li><a href="priorEnglishAgencyAction!getOneStudnetInformation" class="trigger"><span><i class="icon-service"></i><b style="font-size:16px">雅思信息</b></span></a></li>	
                                
			                    	</s:if>
			                    	
			                    	<s:else>
			                    		<li><a href="#" class="trigger"><span><i class="icon-feature"></i><b style="font-size:16px">学生信息</b></span></a>
											<ul>
												<li><a href="priorStudentInformationAction?status=1"><b>国际学生</b></a></li>
												<li><a href="priorStudentInformationAction!getOverseasStudentInformation?status=1"><b>出国学生</b></a></li>
												<li><a href="priorStudentInformationAction!getExchangeStudentInformation?status=1"><b>交换生</b></a></li>
												<li><a href="priorStudentInformationAction!getStudentActivitiesInformation?status=1"><b>学生活动</b></a></li>
		
										
											</ul>
								         </li>
								         
								         	<li><a href="#" class="trigger"><span><i class="icon-feature"></i><b style="font-size:16px">雅思信息</b></span></a>
											<ul>
												<li><a href="priorEnglishAgencyAction?status=1"><b>雅思机构协议信息</b></a></li>
												<li><a href="priorEnglishAgencyAction!getParticipateTrainingInfor?status=1"><b>雅思培训计划信息</b></a></li>
												<li><a href="priorEnglishAgencyAction!getParticipateSimulationExamInfor?status=1"><b>雅思模拟考试信息</b></a></li>
												<li><a href="priorEnglishAgencyAction!getParticipateFormalExamViewInfor?status=1"><b>雅思正式考试信息</b></a></li>
								
											</ul>
								         </li>
			                    	
			                    	
			                    	
			                    	</s:else>
			                    	
			                    	
			                    	</s:if>
			                    
								
								   <li></li>
							
							</ul>
						</nav>	
                       </div>						
				 <!-- end navigation -->
					</div> <!-- End six teen columns -->
			     </div>
			  </div> <!-- End header top -->
	 <br/>
	
    <div class="content">		
				
	 <div class="content_wrapper">	
		   
		 <div class="first_wrapper_bg">	
          <div class="container"> 		 
			   <div class="service_wrapper_holder">	
			   
			   
                			   
				      <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="beforeInformation.jsp"><img src="images/icons/s4.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="beforeInformation.jsp"> <b> 首&nbsp;&nbsp;页</b>  </a></h5>
						
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->
                 			   
				   <div class="service_wrapper">
				     <div class="four columns">
					    <div class="image_shadow">
					      <a href="priorAbroadCollegeAction!getAbroadCollegeInfor?status=1"><img src="images/icons/s1.png" class="scale-with-grid"/></a>
						</div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorAbroadCollegeAction!getAbroadCollegeInfor?status=1"><b>国外院校信息</b> </a></h5>
						  
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->

                   <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="priorAbroadCollegeAction?status=1"><img src="images/icons/s2.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorAbroadCollegeAction?status=1"> <b>国外院校活动的信息</b> </a></h5>
						 
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->


                   <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="priorAbroadCollegeAction!getAbroadCollegeProtocolInfor?status=1"><img src="images/icons/s3.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorAbroadCollegeAction!getAbroadCollegeProtocolInfor?status=1"> <b>国外院校协议</b> </a></h5>
						
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
	 		
		<div class="main-top" style="padding-top:20px">
	            <div class="sixteen columns">
	       <div class="pb_title_wrapper">
		       <h1 class="pb_title"><b style="text-align:center">学生个人的信息</b></h1>
               <div class="clear"></div>
            				 
          </div>  
	         <div class="main_top_location">
	         <hr>
	         </div>
	      </div>
	         
	       <div class="main-body_list">
	        <div class="row">
		        <!-- <div class="col-xs-3" id="myScrollspy" style="background-color:	#EBEBEB">
		            <ul class="nav nav-tabs nav-stacked" data-spy="affix" data-offset-top="125">
		                <li><a href="#section-1"><b>基本信息</b></a></li>
		                <li><a href="#section-2"><b>出国信息</b></a></li>
		                <li><a href="#section-3"><b>交换信息</b></a></li>
		                <li><a href="#section-4"><b>活动信息</b></a></li>
		                <li><a href="updatePassword.jsp"><b>修改密码</b></a></li>
		            </ul>
		        </div> -->
	        
	       <div class="col-xs-9">
            <h3 id="section-1">基本信息</h3>
              <div class="table-responsive">
				<table class="table table-striped"  style="width:750px">
					<thead>
						<tr>
							<th>年级</th>
							<th>学号</th>
							<th>姓名</th>
							<th>班级</th>
							<th>性别</th>
							<th>专业</th>
						</tr>
					</thead>
					<tbody>
						  <s:iterator value="studentList" var="user" status="st">
						<tr>
							<td> <s:property value="#user.grade"/></td>
							<td> <s:property value="#user.studentNo"/></td>
							<td> <s:property value="#user.studentName"/></td>
							<td> <s:property value="#user.interClass.className"/></td>
							<td> <s:property value="#user.reserve2"/></td>
							<td> <s:property value="#user.profession"/></td>
						</tr>
						</s:iterator>
						
					</tbody>
			</table>
			</div>  
            
            <hr>

            <h3 id="section-2">出国信息</h3>
             <div class="table-responsive">
				<table class="table table-striped" style="width:730px">
					<thead>
						<tr>			
							<th>学号</th>
							<th>姓名</th>
						<!-- 	<th>班级</th>
							<th>专业</th> -->
						<!-- 	<th>性别</th> -->
							<th>出国时间</th>
							<th>出国大学</th>
							<th>替换课程</th>
							<th>替换学分</th>
							<th>出国类型</th>
						</tr>
					</thead>
					<tbody>
						  <s:iterator value="overSeasList" var="user" status="st">
						<tr>
							<td> <s:property value="#user.studentNo"/></td>
							<td> <s:property value="#user.studentName"/></td>
						<%-- 	<td> <s:property value="#user.classNo"/></td>
							<td> <s:property value="#user.profession"/></td> --%>
							<%-- <td> <s:property value="#user.sex"/></td> --%>
							<td> <s:property value="#user.outTime"/></td>
							<td> <s:property value="#user.college"/></td>
						
							<td> <s:property value="#user.replaceCourse"/></td>
							<td> <s:property value="#user.replaceCredit"/></td>
							<td> <s:property value="#user.type"/></td>
						</tr>
						</s:iterator>
					</tbody>
			</table>
			</div>  
            <hr>
            <h3 id="section-3">交换信息</h3>
            
             <div class="table-responsive">
				<table class="table table-striped" style="width:730px">
					<thead>
						<tr>
							<th>学号</th>
							<th>姓名</th>
						<!-- 	<th>班级</th>
							<th>专业</th>
							<th>性别</th> -->
							<th>交换时间</th>
							<th>交换大学</th>
							<th>替换课程</th>
							<th>替换学分</th>
						</tr>
					</thead>
					<tbody>
						 <s:iterator value="exchangeList" var="user" status="st">
						<tr>
							<td> <s:property value="#user.studentNo"/></td>
							<td> <s:property value="#user.studentName"/></td>
						<%-- 	<td> <s:property value="#user.classNo"/></td>
							<td> <s:property value="#user.profession"/></td>
							<td> <s:property value="#user.sex"/></td> --%>
							<td> <s:property value="#user.outTime"/></td>
							<td> <s:property value="#user.college"/></td>
						
							<td> <s:property value="#user.replaceCourse"/></td>
							<td> <s:property value="#user.replaceCredit"/></td>
					
						</tr>
						</s:iterator>
					</tbody>
			</table>
			</div>  
			
			<hr>
			
		  <h3 id="section-4">交流活动信息</h3>
            
             <div class="table-responsive">
				<table class="table table-striped" style="width:730px">
					<thead>
						<tr>
						         <th>学号</th>
							     <th>姓名</th>
                                 <th>国外院校</th>
                                 <th>活动主题</th>
                                 <th>活动时间</th>
						</tr>
					</thead>
					<tbody>
					  <s:iterator value="activitiesList" var="user" status="st">
						<tr>
						    <td> <s:property value="#user.id.studentNo"/></td>
							<td> <s:property value="#user.id.studentName"/></td>
							<td> <s:property value="#user.id.name"/></td>
							<td> <s:property value="#user.id.title"/></td>
							<td> <s:property value="#user.id.time"/></td>

						</tr>
						</s:iterator>
					</tbody>
			</table>
			</div>  
             
            <hr>
            </div>
		  </div>

        </div>
			   
	 
    <div class="clear"></div>	
</div><!-- End Portfolio -->

	</div>  <!-- End port wrapper -->	

			
           </div> <!-- End Content wrapper -->
        </div> 	 <!-- End Contain -->	
    </div>	<!-- End content -->

		
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