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
		             	     
		             	     <%-- <s:else>
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
		             	     </s:else> --%>
		             	     
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
								<li class="change"><a href="index.jsp"
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
												href="priorCollegeAction"><b>国外院校信息</b></a></li>
											<li><a href="priorCollegeAction!exqueryActivity"><b>国外院校活动</b></a></li>
											<li><a
												href="priorCollegeAction!exqueryAgreement"><b>国外院校协议</b></a></li>
										</ul>
									</li>
										
								<s:if test='#name!=null'>
									<li>
										<a href="priorCollegeAction!exqueryAgreement1" class="trigger"><span><i class="icon-blog"></i><b  style="font-size:16px">相关下载</b></span></a>
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
												<li><a href="engAgenAgreeAction"><b>雅思机构协议信息</b></a></li>
												<li><a href="priorEnglishAgencyTrainingAction"><b>雅思培训计划信息</b></a></li>
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
	
    <div class="content">		
				
	 <div class="content_wrapper">	
		   
		 <div class="first_wrapper_bg">	
          <div class="container"> 		 
			   <div class="service_wrapper_holder">	
                 			   
				   <div class="service_wrapper">
				     <div class="four columns">
					    <div class="image_shadow">
					      <a href="engAgenAgreeAction"><img src="images/icons/s1.png" class="scale-with-grid"/></a>
						</div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="engAgenAgreeAction"><b>雅思协议信息</b> </a></h5>
						  
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->

                   <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="priorEnglishAgencyTrainingAction"><img src="images/icons/s2.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorEnglishAgencyTrainingAction"> <b>雅思培训计划信息</b> </a></h5>
						 
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->

                   <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="priorExamAction!otherExamSearch"><img src="images/icons/s4.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorExamAction!otherExamSearch"> <b> 雅思模拟考试信息</b>  </a></h5>
						
					  </div> <!-- End service wrapper inner --> 
					 </div>
			   	   </div> <!-- End service wrapper -->

                   <div class="service_wrapper">
				     <div class="four columns">
					   <div class="image_shadow">
					     <a href="priorExamAction!otherScoreSearch"><img src="images/icons/s3.png" class="scale-with-grid"/></a>
					   </div>
				      <div class="service_wrapper_inner"> 	      
					    
				            <h5><a href="priorExamAction!otherScoreSearch"> <b>雅思正式考试信息</b> </a></h5>
						
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
		       <h1 class="pb_title"><b style="text-align:center">参与雅思正式考试的信息</b></h1>
               <div class="clear"></div>
            				 
          </div>  
	         <div class="main_top_location">
	         <hr>
	         </div>
	      </div>
	         
	      <!--  <div class="main-body_list">
	         
              
              <form action="priorEnglishAgencyAction!searchParticipateFormalExamInfor" method="post">
                 
                    <input type="text" style="border-width:2px;width:200px;heigth:30px" name="searchInformation"  placeholder="请输入关键字">
			    <button type="submit" class="btn btn-default" style="border-width:2px;width:80px;heigth:30px">搜索</button>
			     
			         <button type="button" class="btn btn-default" style="width:100px;height:33px">
                            <a href="priorEnglishAgencyAction!getParticipateFormalExamViewInfor?status=1">显示全部</b></a></button>
                            
			     <s:if test='#session.type=="other"'>
			      <button type="button" class="btn btn-default" style="width:70px;height:33px">
                            <a href="priorDownloadExcelAction!exportParticipateFormalExamExcel?searchName=${searchInformation }">导出</a></button>
               	</s:if>
	
              </form>
            </div> -->
            <hr>
	       <div class="main-body_list">
	           <div class="table-responsive">
				<table class="table table-striped" style="width:1000px">
					<thead>
						<tr>
							<th><b>学号</b></th>
				            <th><b>姓名</b></th>
				        <!--     <th><b>班级</b></th> -->
				<!--             <th><b>雅思机构</b></th>
				            <th><b>培训开始时间</b></th>
				            <th><b>培训结束时间</b></th>
				            <th><b>培训学分</b></th>
				            <th><b>培训学期</b></th> -->
				            <th><b>正式考试时间</b></th>
				            <th><b>考试地点</b></th>
				            
				             <th><b>听力</b></th>
				            <th><b>口语</b></th>
				            <th><b>阅读</b></th>
				            <th><b>写作</b></th>
				             <th><b>总分数</b></th>
						</tr>
					</thead>
					<tbody>
					  <s:iterator value="#session.priforscore" var="user" status="st">
						<tr>
							<td> <s:property value="#user.interStu.studentId"/></td>
							<td> <s:property value="#user.interStu.studentName"/></td>
							<%-- <td> <s:property value="#user.id.className"/></td> --%>
						<%-- 	<td> <s:property value="#user.id.agencyName"/></td>
							<td> <s:property value="#user.id.startTime"/></td>
							<td> <s:property value="#user.id.outTime"/></td>
						    <td> <s:property value="#user.id.courseHours"/></td>
							<td> <s:property value="#user.id.semester"/></td> --%>
							<td> <s:property value="#user.exm.time"/></td>
							<td> <s:property value="#user.exm.location"/></td>
							
							<td> <s:property value="#user.listening"/></td>
							<td> <s:property value="#user.oral"/></td>
							<td> <s:property value="#user.reading"/></td>
							<td> <s:property value="#user.writing"/></td>
							<td> <s:property value="#user.score"/></td>
						</tr>
						</s:iterator>
					</tbody>
			</table>
			</div>  
		  </div>
		  
		 	 <s:set name="status" value="#session.status"></s:set> 
		   <div  style="text-align:center">
		       <s:if test="#status==1"> 
                   <c:if test="${totalPage>0}">
                         [<a href="priorEnglishAgencyAction!getParticipateFormalExamViewInfor?pageNo=1"><b>首页</b></a>]
         
                         <c:if test="${currentPage>1}">
                             [<a href="priorEnglishAgencyAction!getParticipateFormalExamViewInfor?pageNo=${currentPage-1}"><b>上一页</b></a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="priorEnglishAgencyAction!getParticipateFormalExamViewInfor?pageNo=${currentPage+1}"><b>下一页</b></a>]
                          </c:if>
         
                         [<a href="priorEnglishAgencyAction!getParticipateFormalExamViewInfor?pageNo=${totalPage}"><b>尾页</b></a>]	
                                                                                                     第${currentPage}页/共${totalPage}页
                      </c:if>
                      </s:if>
                      
                      <s:else>
                      
                         <c:if test="${totalPage>0}">
                         [<a href="priorEnglishAgencyAction!searchParticipateFormalExamInfor?pageNo=1  & temp=1"><b>首页</b></a>]
         
                         <c:if test="${currentPage>1}">
                             [<a href="priorEnglishAgencyAction!searchParticipateFormalExamInfor?pageNo=${currentPage-1}  & temp=1"><b>上一页</b></a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="priorEnglishAgencyAction!searchParticipateFormalExamInfor?pageNo=${currentPage+1}  & temp=1"><b>下一页</b></a>]
                          </c:if>
         
                         [<a href="priorEnglishAgencyAction!searchParticipateFormalExamInfor?pageNo=${totalPage}  & temp=1"><b>尾页</b></a>]	
                                                                                                     第${currentPage}页/共${totalPage}页
                      </c:if>
                      
                      </s:else>
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