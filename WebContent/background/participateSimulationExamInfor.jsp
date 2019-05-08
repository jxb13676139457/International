<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
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
			
			      function toSubmitSimulationExcel(){

			    	  var formData = new FormData($("#simulationExcel")[0]);  // 要求使用的html对象
			    		  $.ajax(		  
					    	      {
					    	    	  
					    	    	  type:"post",
					    	    	  url:"http://localhost:8080/Graduate/participateSimulationExamAction!importSimulationExamExcel",
					    	    	
 					    	  		//注：如果没有文件，只是简单的表单数据则可以使用 $('#formid').serialize();
				    	  		      data:formData,
					    	    	  dataType:"json",	
					    	    	  async:false,			    
					    	          contentType: false,  
					    	          processData: false, 
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
						<li><i class="fa fa-table"></i>维护学生参与模拟考试的信息</li>
					</ol>
				</div>
			</div>
			<div class="row" style="margin:5px">
			      <div class="nav search-row" id="top_menu">
                <!--  search form start -->
                <ul class="nav top-menu">                    
                    <li>
                     <form class="navbar-form" action="participateSimulationExamAction!searchInformation" method="post">
                            <input class="form-control" name="simulationStudentNo" placeholder="输入查找的学号" type="text" required/>
                            <button type="submit" class="btn btn-default" style="width:80px;height:30px"><i class=" icon_search"></i>&nbsp;&nbsp;<b>搜索</b></button>
                             
                               <button type="button" class="btn btn-default" style="width:80px;height:30px">
                            <a href="addParticipateSimulationExam.jsp"><i class="icon_plus_alt2"></i>&nbsp;&nbsp;<b>添加</b></a></button>
                              <button type="button" class="btn btn-default" style="width:120px;height:30px">
                            <a href="participateSimulationExamAction">
                             <i class="icon_menu"></i>&nbsp;&nbsp;
                            <b>显示全部</b></a></button>
               
                       
                               <button type="button" class="btn btn-default" style="width:80px;height:30px">
                            <a href="exportParticipateSimulationExamExcel?searchName=${simulationStudentNo }"><i class="icon_upload"></i>&nbsp;&nbsp;<b>导出</b></a></button>
                      
                             <button class="btn btn-default" data-toggle="modal" data-target="#myModal" style="width:80px;height:30px; float:rigtht">
									<i class="icon_download"></i>&nbsp;&nbsp;<b>导入</b>
								</button>
							<a class="btn btn-default" href="downLoad.action?fileName=模拟考试的参与表信息模板.xls" style="width:140px;height:30px">	<i class="icon_download"></i>&nbsp;&nbsp;<b>下载Excel模板</b></a>
                        </form>
                    </li>                    
                </ul>
                <!--  search form end -->                
              </div>
			</div>
			
			
							<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">
								上传模拟考试信息的EXCEL表
							</h4>
						</div>
						<div class="modal-body">
						   <form enctype="multipart/form-data" id="simulationExcel" method="post">
				                 <input id="file-zh" name="upload" type="file"
				                    multiple>			
				            </form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
							<button type="button" class="btn btn-primary" onclick="toSubmitSimulationExcel()" >
								提交
							</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
			
			<div class="row"></div>
              <!-- page start-->

              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                     
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr style="background-color:#ededed">
                                 <th>培训的机构</th>
                                 <th>模拟考试的时间</th>
                                 <th>学号</th>
                                 <th>姓名</th>
                                 <th>班级</th>
                              
                                 <th>听力</th>
                                 <th>口语</th>	
                                 <th>阅读</th>
                                 <th>写作</th>
                                 <th>总分数</th>
                                 <th>操作</th>
                              </tr>
                           <s:iterator value="UserList" var="user" status="st">
                              <tr>
                                   <td><s:property value="#user.simulation.train.agency.name"/></td>
                                 <td><s:property value="#user.simulation.time"/></td>
                                 
                                  <td><s:property value="#user.student.studentNo"/></td>
                                  <td><s:property value="#user.student.studentName"/></td>
                                 <td><s:property value="#user.student.interClass.className"/></td>                          
                                  <td><s:property value="#user.listening"/></td>
                                 <td><s:property value="#user.oral"/></td>
                                 <td><s:property value="#user.reading"/></td>
                                 <td><s:property value="#user.writing"/></td>
                                 <td><s:property value="#user.score"/></td>
                                 <td>
                                  <div class="btn-group">
                                     <!-- <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a> --> 
                                      <a class="btn btn-default" href="javascript:searchProcess('<s:property value="#user.id"/>','participateSimulationExamAction!searchObjectById?id=')"><i class="icon_pencil"></i></a>
                                      <a class="btn btn-default" href="javascript:deleteProcess('<s:property value="#user.id"/>', 'participateSimulationExamAction!deleteObject?id=')"><i class="icon_trash_alt"></i></a>
                            
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
                         [<a href="participateSimulationExamAction?pageNo=1">首页</a>]
         
                         <c:if test="${currentPage>1}">
                             [<a href="participateSimulationExamAction?pageNo=${currentPage-1}">上一页</a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="participateSimulationExamAction?pageNo=${currentPage+1}">下一页</a>]
                          </c:if>
         
                         [<a href="participateSimulationExamAction?pageNo=${totalPage}">尾页</a>]	
                                                                                                     第${currentPage}页/共${totalPage}页
                      </c:if>
                      </s:if>
                      <s:else>
                      <c:if test="${totalPage>0}">
                         [<a href="participateSimulationExamAction!searchInformation?pageNo=1 & temp=1">首页</a>]
         
                         <c:if test="${currentPage>1}">
                             [<a href="participateSimulationExamAction!searchInformation?pageNo=${currentPage-1} & temp=1">上一页</a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="participateSimulationExamAction!searchInformation?pageNo=${currentPage+1} & temp=1">下一页</a>]
                          </c:if>
         
                         [<a href="participateSimulationExamAction!searchInformation?pageNo=${totalPage} & temp=1">尾页</a>]	
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
			
			
            <!--提示查询错误 -->
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
			     String update=(String)session.getAttribute("updateInfor");
			     if(update!=null)
			     {
			 %>
			 
				 <script type="text/javascript" language="javascript">
				 
					alert("<%=update%>");
				</script>
			<%
			     session.setAttribute("updateInfor",null);
			     }
			%>

  </body>
</html>
