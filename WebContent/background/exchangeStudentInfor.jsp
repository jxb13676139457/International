<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  
     
<!-- 显示查询错误-->
            <%
			     String s=(String)session.getAttribute("searchError");
			     if(s!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=s%>");
				</script>
			<%
			     session.setAttribute("searchError",null);
			     }
			%>
			
						<!-- 更新信息显示 -->
			
			    <%
			     String update=(String)session.getAttribute("updateStudentInfor");
			     if(update!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=update%>");
				</script>
			<%
			     session.setAttribute("updateStudentInfor",null);
			     }
			%>
			
			<!-- 没有信息显示时提示信息-->
            <%
			     String message=(String)request.getAttribute("message");
			     if(message!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=message%>");
				</script>
			<%
			     }
			%>
			
		<script type="text/javascript">
	     	//用来跳转到维护操作员模块的action
			function gotoShowAction(){
				location.href="managerAction!showOperator";
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
              <a href="indexAction" class="logo"><span style="font-size:25px;color:white"><img src="img/logo3.png" id="logo" ><b>国际合作交流后端管理系统</b></span></a>
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
                      <a class="" href="indexAction">
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
						<li><i class="fa fa-home"></i><a href="indexAction">首页</a></li>
						<li><i class="fa fa-table"></i>维护交换生信息</li>
					</ol>
				</div>
			</div>
			<div class="row" style="margin:5px">
			      <div class="nav search-row" id="top_menu">
                <!--  search form start -->
                <ul class="nav top-menu">                    
                    <li>
                       <form class="navbar-form" action="exchangeStudentAction!showStudent" method="post">
                             <input class="form-control" name="loginUserName" placeholder="输入查找的关键字" type="text" required/>
                             <button type="submit" class="btn btn-default" style="width:80px;height:30px">
                             	<i class=" icon_search"></i>&nbsp;&nbsp;<b>搜索</b></button>
                             <button type="button" class="btn btn-default" style="width:80px;height:30px">
                             	<a href="addExchangeStudent.jsp"><i class="icon_plus_alt2"></i>&nbsp;&nbsp;<b>添加</b></a></button>
                             <button type="button" class="btn btn-default" style="width:120px;height:30px">
                             	<a href="exchangeStudentAction!showStudent"><i class="icon_menu"></i>&nbsp;&nbsp;<b>显示全部</b></a></button>
                             <button type="button" class="btn btn-default" style="width:80px;height:30px">
                             	<a href="excelAction!exportExchangeSudent"><i class="icon_upload"></i>&nbsp;&nbsp;<b>导出</b></a>
                             </button>
                        </form>
                    </li>                    
                </ul>
                <!--  search form end -->                
              </div>
			</div>
			<div class="row"></div>
              <!-- page start-->

              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                     
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr style="background-color:#ededed">
                                 <th>学号</th>
                                 <th>姓名</th>
                                 <th>性别</th>
                                 <th>班级</th>
                                 <th>专业</th>
                                 <th>交换出国开始时间</th>
                                 <th>交换出国结束时间</th>
                                 <th>交换的院校</th>
                                 <th>操作</th>
                              </tr>
                           <s:iterator value="exchangeStudents" var="student" status="st">
                              <tr>
                                 <td><s:property value="#student.studentNo"/></td>
                                 <td><s:property value="#student.studentName"/></td>
                                 <td><s:property value="#student.sex"/></td>
                                 <td><s:property value="#student.className"/></td>
                                 <td><s:property value="#student.major"/></td>
                                 <td><s:property value="#student.startTime.toString().substring(0,10)"/></td>
                                 <td><s:property value="#student.endTime.toString().substring(0,10)"/></td>
                                 <td><s:property value="#student.exchangeCollege"/></td>
                                 <td>
                                 	 <script type="text/javascript" charset="UTF-8">
                                       		//删除指定id的学生
                                       		function deleteStudent(id){
                                       			if(confirm("你确定要删除该学生吗？")){
                                       				location.href="exchangeStudentAction!deleteExchangeStudent?stuId="+id;
                                       			}
                                       		}
                                       		//修改指定id的学生
                                       		function editStudent(id){
                                       			location.href="exchangeStudentAction!queryStudent?stuId="+id;
                                       		}
                                  	</script> 
                                  	<div class="btn-group">
                                      <!-- <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a> --> 
                                      <a class="btn btn-default" href="javascript:editStudent('<s:property value="#student.id"/>')"><i class="icon_pencil"></i></a>
                                      <a class="btn btn-default" href="javascript:deleteStudent('<s:property value="#student.id"/>')"><i class="icon_trash_alt"></i></a>
                                 	</div>
                                 </td>
                              </tr>
                              </s:iterator>                             
                           </tbody>
                        </table>
                         </section>
                     </div> 
               <s:set name="status" value="#session.status"></s:set> 
		       <div  style="text-align:center">
		          <s:if test="#status==1"> 
                   <c:if test="${totalPage>0}">
                         [<a href="exchangeStudentAction?pageNo=1">首页</a>]
         
                         <c:if test="${currentPage>1}">
                             [<a href="exchangeStudentAction?pageNo=${currentPage-1}">上一页</a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="exchangeStudentAction?pageNo=${currentPage+1}">下一页</a>]
                          </c:if>
         
                         [<a href="exchangeStudentAction?pageNo=${totalPage}">尾页</a>]	
                                                                                                     第${currentPage}页/共${totalPage}页
                      </c:if>
                      
                     </s:if>
                     
                     <s:else>
                            <c:if test="${totalPage>0}">
                         [<a href="exchangeStudentAction!searchStudent?pageNo=1 & temp=1"">首页</a>]
         
                         <c:if test="${currentPage>1}">
                             [<a href="exchangeStudentAction!searchStudent?pageNo=${currentPage-1} & temp=1"">上一页</a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="exchangeStudentAction!searchStudent?pageNo=${currentPage+1} & temp=1"">下一页</a>]
                          </c:if>
         
                         [<a href="exchangeStudentAction!searchStudent?pageNo=${totalPage} & temp=1"">尾页</a>]	
                                                                                                     第${currentPage}页/共${totalPage}页
                      </c:if>
                     
                     </s:else>
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
    <!-- nicescroll -->
    <script src="js/jquery.scrollTo.min.js"></script>
    <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
    <!--custome script for all page-->
    <script src="js/scripts.js"></script>
    <script src="js/js.js"  charset="gb2312"></script>
    
    
   </body>
</html>
