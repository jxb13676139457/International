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
    
      
      <!--提示查询错误信息 -->
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
    
  </head>

  <body>
  
    <!-- 自动生成时间的js -->
    			   <script language="javascript" type="text/javascript"> 
						window.onload=function(){ 
						//设置年份的选择 
						var myDate= new Date(); 
						var startYear=myDate.getFullYear()-5;//起始年份 
						var endYear=myDate.getFullYear()+10;//结束年份 
						var obj=document.getElementById('myYear');
						
						for (var i=startYear;i<=endYear;i++) 
						{ 
						obj.options.add(new Option(i,i)); 
						}  
					}
			</script> 
				<!-- 根据年级来获取专业的信息 -->
			<script type="text/javascript">
			
			      function getProfession(){
			    	  
			    	
			    	  var temp=1;
			    	  var grade= $("#myYear").find("option:selected").val();
		    	  
			    	  if(grade!=null){
			    		
			    		  $.ajax(			    		
					    	      {
					    	    	  
					    	    	  type:"post",
					    	    	  url:"http://localhost:8080/Graduate/studentInformationAction!getProfessionInformation",
					    	    	  data:{grade:grade},
					    	    	  dataType:"json",			    	
					    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					    	    	  traditional:true,
					    	    	  success: function(data){			
	
						    	    		 var html="";
						    	    		 
						    	    		 html=html +'<option></option>';
			
						                     for(var i=0; i<data.length; i++){
						                    	
						                    	   for(var j=0; j<i; j++){
						                    				 
						                    		 if(data[i].reserve2==(data[j].reserve2)){
						                    			 temp=0;
						                    			 break;
						                    		 }
						                    	 }
						                    	 if(temp==1){
						                    		 html=html +'<option value=""+ data[i].reserve2+"">'+data[i].reserve2+'</option>';
						                    	 }
						                    	 temp=1;
						                     }
						                     
						                     $('#pro').html(html);
					    	    	
					                   },
					                   
					                   error: function(data){
					                	   
					                		 var html="";
				    	    				 $('#pro').html(html);
					                   }
		  
					    	    	  }			    	      
					    	  
					    	  );
			    	  }else{
			    		  
			    		  alert("请选择一条数据!");
			    	  }
			    	  
			    
			      }
			</script>
			
			
				<!-- 根据年级来获取班级的信息 -->
			<script type="text/javascript">
			
			      function getClassInformation(){
			    	  
			    	  var grade= $("#myYear").find("option:selected").val();
			    	  var profession=$("#pro").find("option:selected").text();
			    	  			    	   
			    	  if(profession!=null){
			    		  $.ajax(		  
					    	      {
					    	    	  
					    	    	  type:"post",
					    	    	  url:"http://localhost:8080/Graduate/studentInformationAction!getclassInfromation",
					    	    	  data:{profession:profession,grade:grade},
					    	    	  dataType:"json",			    	
					    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					    	    	  traditional:true,
					    	    	  success: function(data){
					    	    		  
					    	    		 var html="";

					    	    		 html=html +'<option></option>';
					                     for(var i=0; i<data.length; i++){
					                    	                                   
					                    		 html=html +'<option value=""+ data[i].className+"">'+data[i].className+'</option>';
					                    	
					                     }
					                     
					                     $('#cla').html(html);
					                   },   
					                   error: function(data){
					                	   
					                		 var html="";
					                		 $('#cla').html(html);
					                   }
		  
					    
					    	    	  
					    	    	  
					    	      }
					    	      
					    	  
					    	  );
			    	  }else{
			    		  
			    		  alert("请选择一条数据!");
			    	  }
			    	  
			    
			      }
			</script>
		<script type="text/javascript">
			
			      function toSubmit(){
			    	  alert("hello");
			    	  var formData = new FormData($("#studentExcel")[0]);  // 要求使用的html对象
			    		  $.ajax(		  
					    	      {
					    	    	  
					    	    	  type:"post",
					    	    	  url:"http://localhost:8080/Graduate/studentInformationAction!importExcel",
					    	    	
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
						<li><i class="fa fa-table"></i>维护国际学生信息</li>
					</ol>
				</div>
			</div>
			<div class="row" style="margin:5px">
			      <div class="nav search-row" id="top_menu">
                <!--  search form start -->
                <ul class="nav top-menu">            
                             
                 <li>
                       <form class="navbar-form" action="studentInformationAction!searchStudentGFCS" method="post">
                             <label><b>年级</b>&nbsp;</label>
                             <select id="myYear" name="grade" 
                                         style="width:150px;height:30px;border-radius:3px;-webkit-border-radius:5px;-moz-border-radius :3px;"
                                         onchange="getProfession()">
                                            <option selected></option>
                              </select> 
                                <label><b>专业</b>&nbsp;</label>
                             <select id="pro" name="profession" style="width:150px;height:30px;border-radius:3px;-webkit-border-radius:3px;-moz-border-radius:3px;"
                                                onchange="getClassInformation()">                               
                               
                               </select>
                                 <label><b>班级</b>&nbsp;</label>
                             <select id="cla" name="className" style="width:150px;height:30px;border-radius:3px;-webkit-border-radius:3px;-moz-border-radius:3px;">                           
                                            
                                            </select>
                                   <label><b>状态</b>&nbsp;</label>           
                               <select name="status2" style="width:150px;height:30px;border-radius:3px;-webkit-border-radius:3px;-moz-border-radius:3px;" >
                                                    <option selected></option>
                                                    <option>在班</option>
                                                    <option>已转班</option>
                               </select>
                            <button type="submit" class="btn btn-default" style="width:120px;height:30px;margin-top:-3px"><i class=" icon_search"></i>&nbsp;&nbsp;<b>查询统计2</b></button>
                     
                        </form>
                        </li>
                    <li style="margin-top:10px">
                       <form class="navbar-form" action="studentInformationAction!searchStudent" method="post">
                            <input class="form-control" name="studentInformation" placeholder="输入查找的关键字" type="text" required/>
                            <button type="submit" class="btn btn-default" style="width:80px;height:30px"><i class=" icon_search"></i>&nbsp;&nbsp;<b>搜索</b></button>
                            
                               <button type="button" class="btn btn-default" style="width:80px;height:30px">
                            <a href="addStudent.jsp"><i class="icon_plus_alt2"></i>&nbsp;&nbsp;<b>添加</b></a></button>
                              <button type="button" class="btn btn-default" style="width:120px;height:30px">
                            <a href="studentInformationAction?status=1"><i class="icon_menu"></i>&nbsp;&nbsp;
                            <b>显示全部</b></a></button>
      
                              <button type="button" class="btn btn-default" style="width:80px;height:30px">
                            <a href="exportInterStudentExcel2?studentInfor=${studentInfor}"><i class="icon_upload"></i>&nbsp;&nbsp;<b>导出</b></a></button>
                            
                            <!--   <button type="button" class="btn btn-default" style="width:80px;height:30px">
                            <a href=studentInformationAction!importExcel><i class="icon_download"></i>&nbsp;&nbsp;<b>导入</b></a></button> -->
                            <button class="btn btn-default" data-toggle="modal" data-target="#myModal" style="width:80px;height:30px; float:rigtht">
									<i class="icon_download"></i>&nbsp;&nbsp;<b>导入</b>
								</button>
								
							<a class="btn btn-default" href="downLoad.action?fileName=国际班学生信息模板.xls" style="width:140px;height:30px">	<i class="icon_download"></i>&nbsp;&nbsp;<b>下载Excel模板</b></a>
                        </form>
                    </li>
                  
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
								上传国际班学生EXCEL表
							</h4>
						</div>
						<div class="modal-body">
						   <form enctype="multipart/form-data" id="studentExcel" method="post">
				                 <input id="file-zh" name="upload" type="file"
				                    multiple>			
				            </form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
							<button type="button" class="btn btn-primary" onclick="toSubmit()" >
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
                                 <th>年级</th>
                                 <th>学号</th>
                                 <th>密码</th>
                                 <th>姓名</th>
                                 <th>性别</th>
                                 <th>班级</th>
                                 <th>专业</th>
                                 <th>状态</th>
                                 <th>总人数</th>
                                 <th>操作</th>
                              </tr>
                           <s:iterator value="StudentList" var="student" status="st">
                              <tr>
                                 <td><s:property value="#student.grade"/></td>
                                 <td><s:property value="#student.studentNo"/></td>
                                 <td><s:property value="#student.password"/></td>
                                 <td><s:property value="#student.studentName"/></td>
                                 <td><s:property value="#student.reserve2"/></td>
                                 <td><s:property value="#student.interClass.className"/></td>
                                 <td><s:property value="#student.profession"/></td>                             
                                 <td><s:property value="#student.status"/></td>
                                    <td><s:property value="tojiSum"/></td>
                                 <td>
                                  <div class="btn-group">
                                     <!-- <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a> --> 
                                      <a class="btn btn-default" href="javascript:searchProcess('<s:property value="#student.id"/>','studentInformationAction!searchStudentById?id=')"><i class="icon_pencil"></i></a>
                                      <a class="btn btn-default" href="javascript:deleteProcess('<s:property value="#student.id"/>', 'studentInformationAction!deleteStudent?id=')"><i class="icon_trash_alt"></i></a>
                            
                                  </div>
                                  </td>
                              </tr>
                              </s:iterator>                             
                           </tbody>
                        </table>
                         </section>
                     </div> 

                        <div  style="text-align:center"> 
                           <c:if test="${totalPage>0}">
                         [<a href="studentInformationAction!searchStudentGFCS?pageNo=1 & temp=1">首页</a>]
         
                         <c:if test="${currentPage>1}">
                             [<a href="studentInformationAction!searchStudentGFCS?pageNo=${currentPage-1} & temp=1">上一页</a>]
                        </c:if>
         
                         <c:if test="${currentPage<totalPage}">
                            [<a href="studentInformationAction!searchStudentGFCS?pageNo=${currentPage+1} & temp=1">下一页</a>]
                          </c:if>
         
                         [<a href="studentInformationAction!searchStudentGFCS?pageNo=${totalPage} & temp=1">尾页</a>]	
                                                                                                     第${currentPage}页/共${totalPage}页
                      </c:if>
                      
             
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
