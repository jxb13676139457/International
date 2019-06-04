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

    <title>国际合作交流管理系统</title>
   <sx:head extraLocales="en-us,nl-nl,de-de"/>
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
	      function toSubmit(){
    		  $.ajax(		  
	    	      {
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/InternationalSys/background/overseasStudentAction!editStudent",
		    	  	  //注：如果没有文件，只是简单的表单数据则可以使用 $('#formid').serialize();
    	  		      data:$("#studentForm").serialize(),
	    	    	  dataType:"json",	
	    	    	  async:false,
	    	    	  success: function(data){
	    	    		  if(data!=null && data!=""){
	    	    			  alert(data);
	    	    		  }
	                   }
	    	      }
	    	  );
	      }
	</script>
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
                            <span  style="color:white; font-size:20px"><s:property value="#session.userName"/></span>
                            <p class="caret"></p>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li class="eborder-top">
                                <a href="profile.jsp"><i class="icon_profile"></i>个人信息</a>
                            </li>
                            <li>
                                <a href="javascript:gotoExitAction();"><i class="icon_key_alt"></i>退出登录</a>
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
						<li><i class="icon_document_alt"></i>修改出国学生信息</li>
					</ol>
				</div>
			</div>
				  <button type="button" class="btn btn-information" style="width:100px;height:30px;font-size:15px">
                            <a href="overseasStudentAction!showStudent"><b>返回上页</b></a></button>
              <!-- Form validations -->              
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                           <b> 修改出国学生信息</b>
                          </header>
                          <div class="panel-body">
                              <div class="form">
                                <label  style="color:red">${ sessionScope.addUserError}</label>
                                  <form class="form-validate form-horizontal" id="studentForm" method="post">
                                    <%--    <div class="form-group ">
                                          <div class="col-lg-10">
                                              <input class="form-control" name="updateStudent.id" value="${sessionScope.editOverStudent.id }"  type="hidden" required/>
                                          </div>
                                      </div> --%>
                                      <div class="form-group ">
                                          <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>学号</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" name="oversea.studentId" value="${sessionScope.editOverseasStudent.studentId }" 
                                               onkeyup="value=value.replace(/[^\d]/g,'')"   placeholder="只能输入数字"
                                              style="width:300px"  type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                        <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>姓名</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" name="oversea.studentName" value="${sessionScope.editOverseasStudent.studentName }" style="width:300px"  type="text"  required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                      <div class="form-group ">
                                          <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cemail" class="control-label col-lg-2"><b>班级</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control"  type="text" name="className" style="width:300px"   value="${sessionScope.editOverseasStudent.classes.className }"
                                              minlength="6" required/>
                                          </div>
                                          </div>
                                      </div>
                                                                           
                                        <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>专业</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" name="major"  style="width:300px"  value="${sessionScope.editOverseasStudent.classes.major}"
                                             type="text"  required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                      <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>性别</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" name="oversea.sex" style="width:300px"   value="${sessionScope.editOverseasStudent.sex}" type="text"   required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                      <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>出国院校就读专业</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" name="oversea.outMajor" style="width:300px"   value="${sessionScope.editOverseasStudent.outMajor}" type="text"   required/>
                                          </div>
                                          </div>
                                      </div>
                                       <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>获得的本校学位</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="inDegree" name="oversea.inDegree" value="${sessionScope.editOverseasStudent.inDegree}" style="width:300px" type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                       <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>获得的外校学位</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="outDegree" name="oversea.outDegree" value="${sessionScope.editOverseasStudent.outDegree}" style="width:300px" type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                       <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>获得的本校奖学金</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="inSchoolarship" name="oversea.inSchoolarship" value="${sessionScope.editOverseasStudent.inSchoolarship}" style="width:300px" type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                       <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>本校奖学金金额</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="inAmount" name="oversea.inAmount" value="${sessionScope.editOverseasStudent.inAmount}" style="width:300px" type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                       <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>获得的外校奖学金</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="outSchoolarship" name="oversea.outSchoolarship" value="${sessionScope.editOverseasStudent.outSchoolarship}" style="width:300px" type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                       <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>外校奖学金金额</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="outAmount" name="oversea.outAmount" value="${sessionScope.editOverseasStudent.outAmount}" style="width:300px" type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                       <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>外校币种</b><span class="required" style="color:red">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" id="currency" name="oversea.currency" value="${sessionScope.editOverseasStudent.currency}" style="width:300px" type="text" required/>
                                          </div>
                                          </div>
                                      </div>
                                          
                                        <div class="form-group ">
                                            <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>出国时间</b></label>
                                          <div class="col-lg-10">
                                              <sx:datetimepicker name="oversea.outTime"  value="" displayFormat="yyyy-MM-dd"/>
                                           </div>
                                          </div>
                                      </div>
                                      
                                          <div class="form-group ">
                                              <div  style="margin-left:250px;margin-top:-10px">
                                              <label for="cname" class="control-label col-lg-2"><b>出国的院校</b></label>
	                                          <div class="col-lg-10">
	                                              <input class="form-control" name="collegeName" style="width:300px"  value="${sessionScope.editOverseasStudent.college.collegeName}"  type="text"/>
	                                          </div>
                                          </div>
                                      </div>
                                      
                                      <div class="form-group ">
                                          <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>类型</b></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" name="type" style="width:300px"  value="${sessionScope.editOverseasStudent.college.type}"  type="text"/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                      <div class="form-group ">
                                          <div  style="margin-left:250px;margin-top:-10px">
                                          <label for="cname" class="control-label col-lg-2"><b>出国津贴(RMB)</b></label>
                                          <div class="col-lg-10">
                                              <input class="form-control" name="oversea.subsidy" style="width:300px"  value="${sessionScope.editOverseasStudent.subsidy}"  type="text"/>
                                          </div>
                                          </div>
                                      </div>
                                      
                                      <div class="form-group">
                                          <div  style="margin-left:250px;margin-top:-10px">
                                          <div class="col-lg-offset-2 col-lg-10">
                                              <button class="btn btn-primary" onclick="toSubmit()">更新</button>
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

	
         <!-- 判断有没有获取数据成功-->
            <%
			     String s=(String)request.getAttribute("error");
			     if(s!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=s%>");
				</script>
			<%
			     }
			%>
			

			

  </body>
</html>
