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
    
        <!-- 判断有没有重复名字的管理员-->
           
       <script type="text/javascript">
	     	//用来跳转到查询订单的action
			function gotoShowAction(){
				location.href="managerAction";
			}
       </script>
		<script type="text/javascript">
			      function toSubmit(){
		    		  $.ajax(		  
			    	      {
			    	    	  type:"post",
			    	    	  url:"http://localhost:8080/InternationalSys/background/managerAction!showOperator",
				    	  		  //注：如果没有文件，只是简单的表单数据则可以使用 $('#formid').serialize();
		    	  		      data:$("#showloginUser").serialize(),
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
	<script type="text/javascript">
		//批量添加管理员
	      function tijiao(){
	    	  var checkID=[];
	    	  $("input[name='checkbox']:checked").each(function(i){
	    		  checkID[i]=$(this).val();
	    	  });
	    	  alert(checkID[i]);  //弹出提示
	    	  if(checkID.length>0){
	    		  $.ajax({
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/InternationalSys/managerAction!toJson",
	    	    	  data:{checkID:checkID},
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  if(data){
	    	    			   alert("设置成功");
	    	    			   window.location.href("http://localhost:8080/Graduate/loginUserInformationAction!execute");
	    	    		  }else{
	    	    			   alert("该用户已经是管理员,请重试！");
	    	    		  }
	                   }
	    	      });
	    	  }else{
	    		  alert("请选择一条数据!");
	    	  }
	      }
	      
		//批量解除管理员
	      function toReset(){
	    	  var checkID=[];
	    	  $("input[name='checkbox']:checked").each(function(i){
	    		  checkID[i]=$(this).val();
	    	  });
	    	  
	    	  if(checkID.length>0){
	    		  $.ajax({
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/Graduate/adminUserAction!deleteUser",
	    	    	  data:{checkID:checkID},
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  if(data){
	    	    			   alert("成功解除管理员权限");
	    	    			   
	    	    			   window.location.href("http://localhost:8080/Graduate/loginUserInformationAction!execute");
	    	    		  }else{
	    	    			   alert("解除管理员权限失败,请判断该用户是不是管理员！");
	    	    		  }
	                   }
	    	      });
	    	  }else{
	    		  alert("请选择一条数据!");
	    	  }
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
						<li><i class="fa fa-table"></i>维护操作员</li>
			
					</ol>
				</div>
			</div>
			<div class="row" style="margin:5px">
			      <div class="nav search-row" id="top_menu">
                <!--  search form start -->
                <ul class="nav top-menu">                    
                    <li>
                        <form class="navbar-form" action="managerAction!showOperator" id="showloginUser" method="post">
                          <input class="form-control" name="loginUserName" placeholder="输入要查找的关键字" type="text" required/>
                          <button type="submit" class="btn btn-default" style="width:80px;height:30px"><i class=" icon_search"></i>&nbsp;&nbsp;<b>搜索</b></button>
                          
                          <button type="button" class="btn btn-default" style="width:80px;height:30px">
                          <a href="addLoginUser.jsp"><i class="icon_plus_alt2"></i>&nbsp;&nbsp;<b>添加</b></a></button>
                          <button type="button" class="btn btn-default" style="width:120px;height:30px">
                          <a href="managerAction!showOperator"><i class="icon_menu"></i>&nbsp;&nbsp;<b>显示全部</b></a></button>
                       
                          <button type="button" class="btn btn-default" style="width:80px;height:30px">
                          <a href="excelAction!exportOperator"><i class="icon_upload"></i>&nbsp;&nbsp;<b>导出</b></a></button>

                        <!-- <button type="button" class="btn btn-default" style="width:120px;height:30px" onclick="tijiao()">
                          <b><i class="icon_plus_alt2"></i>&nbsp;&nbsp;授予管理员</b></button>
                           
                        <button type="button" class="btn btn-default" style="width:120px;height:30px" onclick="toReset()">
                          <b><i class="icon_close_alt2"></i>&nbsp;&nbsp;解除管理员</b></button> -->
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
                                <!--  <th>&nbsp;</th> -->
                                 <th>用户名</th>
                                 <th>真实名字</th>
                                 <th>性别</th>
                                 <th>超级管理员</th>
                                 <th>操作</th>
                              </tr>
                           
                           <s:iterator value="admins" var="admin" status="st">
                              <tr>               
                                 <%-- <td id="name" style="width:10px">
                                 <input type="checkbox" name="checkbox" id="checkbox" value="<s:property value="#admin.id"/>"/>
                                 </td>   --%>              
                                 <td id="adminId"><s:property value="#admin.adminId"/></td>
                                 <td id="username"><s:property value="#admin.userName"/></td>
                                 <td id="sex"><s:property value="#admin.sex"/></td>
                                 <td id="type"><s:property value="#admin.type"/></td>
                               
                               <%--   <s:set name="status" value="#admin.adminType"></s:set> 
                                 <s:if test="#status==1">
                                 	<td id="type"><s:property value="是"/></td>
                                 </s:if>
                                 <s:if test="#status==0">
                                 	<td id="type"><s:property value="否"/></td>
                                 </s:if> --%>
                                 <%-- <s:else test="#status==0">
                                 	<td id="type"><s:property value="否"/></td>
                                 </s:else> --%>
                                 
                                 <td>
                                   <script type="text/javascript" charset="UTF-8">
                                       		//删除指定id的管理员
                                       		function deleteOperator(adminId){
                                       			if(confirm("你确定要删除该操作员吗？")){
                                       				location.href="managerAction!deleteLoginUser?adminId="+adminId;
                                       			}
                                       		}
                                       		//修改指定id的管理员
                                       		function editOperator(adminId){
                                       			location.href="managerAction!queryLoginUser?adminId="+adminId;
                                       		}
                                   </script> 
                                  <div class="btn-group">
                                     <!-- <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a> --> 
                                     <a class="btn btn-default" href="javascript:editOperator('<s:property value="#admin.adminId"/>')"><i class="icon_pencil"></i></a>
                                       <%-- <a class="btn btn-default" href="javascript:resetLoginUser('<s:property value="#loginUser.id"/>')"><i class="icon_like_alt"></i></a> --%>
                                      <a class="btn btn-default" href="javascript:deleteOperator('<s:property value="#admin.adminId"/>')"><i class="icon_trash_alt"></i></a>
                                  </div>
                                  </td>
                              </tr>
                            </s:iterator>                             
                           </tbody>
                        </table>
                          </section>                
                  </div>
            <%-- <s:set name="status" value="#session.status"></s:set>  --%>
		       <div  style="text-align:center">
		         <%--  <s:if test="#status==1"> 
                     <c:if test="${totalPage>0}">
                         [<a href="managerAction?pageNo=1">首页</a>]
                    
                         <c:if test="${currentPage>1}">
                             [<a href="managerAction?pageNo=${currentPage-1}">上一页</a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="managerAction?pageNo=${currentPage+1}">下一页</a>]
                          </c:if>
         
                         [<a href="managerAction?pageNo=${totalPage}">尾页</a>]
                                                                                                     第${currentPage}页/共${totalPage}页
                     </c:if>
                      </s:if>
                                
                  <s:else>
                        <c:if test="${totalPage>0}">
                         [<a href="loginUserInformationAction!searchLoginUser?pageNo=1 & temp=1">首页</a>]
                    
                         <c:if test="${currentPage>1}">
                             [<a href="loginUserInformationAction!searchLoginUser?pageNo=${currentPage-1} & temp=1">上一页</a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="loginUserInformationAction!searchLoginUser?pageNo=${currentPage+1} & temp=1">下一页</a>]
                          </c:if>
         
                         [<a href="loginUserInformationAction!searchLoginUser?pageNo=${totalPage} & temp=1">尾页</a>]
                                                                                                     第${currentPage}页/共${totalPage}页
                     </c:if>
                  </s:else> --%>
                  
                  	<!-- 分页 -->
			  		[<a href="managerAction!showOperator?pageNo=1&loginUserName=${sessionScope.searchLoginUser}">首页</a>]
						<c:choose>
							<c:when test="${currentPage>1}">
								[<a href="managerAction!showOperator?pageNo=${currentPage-1}&loginUserName=${sessionScope.searchLoginUser}">上一页</a>]
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${currentPage<totalPage}">
								[<a href="managerAction!showOperator?pageNo=${currentPage+1}&loginUserName=${sessionScope.searchLoginUser}">下一页</a>]
							</c:when>
						</c:choose>
					[<a href="managerAction!showOperator?pageNo=${totalPage}&loginUserName=${sessionScope.searchLoginUser}">尾页</a>]
					第${currentPage}页/共${totalPage}页
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
    <script src="js/js.js"  charset="UTF-8"></script>
    

  </body>
</html>
