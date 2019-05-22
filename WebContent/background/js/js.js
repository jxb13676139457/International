

//重置操作员的密码
function resetLoginUser(id){
	
	if(confirm("确定要重置该操作员的密码吗？")){
		
		location.href="adminUserAction!resetLoginUser?id=" + id;
				
	}
}

//删除的过程
function deleteProcess(id, str){
	
	if(confirm("删除可能会涉及级联删除其他信息，确定要删除吗？")){
		
		location.href=str+id;
				
	}
}

//查找过程
function searchProcess(id, str){
			
	location.href=str+id;
				
}


//根据学号来获取学生的信息
 function getStudentInformation(){
    	  
    	  var studentNo= $("#studentNo").val();

    	  if(studentNo!=null){
    		
    		  $.ajax(			    		
		    	      {
		    	    	  
		    	    	  type:"post",
		    	    	  url:"http://localhost:8080/Graduate/overseasStudentAction!getStudentInformation",
		    	    	  data:{studentNo:studentNo},
		    	    	  dataType:"json",			    	
		    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		    	    	  traditional:true,
		    	    	  success: function(data){			
		    	    		  
			    	    	$("#studentName").val(data.studentName);
			    	     	$("#className").val(data.reserve1); 
			    	    	$("#sex").val(data.reserve2); 
			    	    	$("#profession").val(data.profession);
			    	    	$("#grade").val(data.grade);
		                   },
		                   
		                   error: function(data){
		                	   
		                	   $("#studentName").val("");
				    	    	$("#className").val("");
				    	    	$("#profession").val("");
				    	      	$("#profession").val("");
				    	    	$("#grade").val("");
		                   }

		    	    	  }			    	      
		    	  
		    	  );
 
    	  }
    
      }


//获取国外院校的信息
 function getCollegeInformation(){
    
	 var temp=1;
	 $.ajax(			    		
   	      {
   	    	  
   	    	  type:"post",
   	    	  url:"http://localhost:8080/InternationalSys/background/collegeAction!getCollegeInformation",
   	    	  data:{},
   	    	  dataType:"json",			    	
   	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   	    	  traditional:true,
   	    	  success: function(data){			

	    	    		 var html="";
	    	    		 html=html +'<option selected>--请选择国外院校--</option>';
	    	    		
	                     for(var i=0; i<data.length; i++){
	                    	
	                    	   for(var j=0; j<i; j++){
	                    	
	                    		 if(data[i].collegeName==(data[j].collegeName)){
	                    			 temp=0;
	                    			 break;
	                    		 }
	                    	 }
	                    	 if(temp==1){
	                    		 html=html +'<option value=""+ data[i].collegeName+"">'+data[i].collegeName+'</option>';
	                    	 }
	                    	 temp=1;
	                     }
	                     	
	                     $('#college').html(html);
   	    	
                  },
                  
                  error: function(data){
               	   
               		 var html="";
	    				 $('#college').html(html);
                  }

   	    	  }			    	      
   	  
   	  );
   }
 
 
//获取雅思机构的信息
 function getAgencyInformation(){
    	  
	 var temp=1;
	 
	 $.ajax(			    		
   	      {
   	    	  
   	    	  type:"post",
   	    	  url:"http://localhost:8080/Graduate/trainingAction!getAgencyInformation",
   	    	  data:{},
   	    	  dataType:"json",			    	
   	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   	    	  async:true,
   	    	  traditional:true,
   	    	  success: function(data){			

	    	    		 var html="";
	    	    		 html=html +'<option selected>--��ѡ�����--</option>';
	    	    		
	                     for(var i=0; i<data.length; i++){
	                    	
	                    	   for(var j=0; j<i; j++){
	                    	
	                    		 if(data[i].name==(data[j].name)){
	                    			 temp=0;
	                    			 break;
	                    		 }
	                    	 }
	                    	 if(temp==1){
	                    		 html=html +'<option value=""+ data[i].name+"">'+data[i].name+'</option>';
	                    	 }
	                    	 temp=1;
	                     }
	                     
	                     $('#agencyName').html(html);
   	    	
                  },
                  
                  error: function(data){
               	   
               		 var html="";
	    				 $('#agencyName').html(html);
                  }

   	    	  }			    	      
   	  
   	  );
   }

//获取雅思培训的开始时间
 function getStartTime(){
	  
	  
	  var temp=1;
	  var agencyName= $("#agencyName").find("option:selected").text();   	  
	  
	  if(agencyName!=null || startTime!=null){
		  $.ajax(		  
	    	      {
	    	    	  
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/Graduate/simulationExamAction!getStartTimeInformation",
	    	    	  data:{agencyName:agencyName},
	    	    	  async:false,
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  
	    	    			 var html="";
		    	    		 html=html +'<option selected>--��ѡ��ʼʱ��--</option>';
                             
		                     for(var i=0; i<data.length; i++){
		                    	
		                    	   for(var j=0; j<i; j++){
		                    				 
		                    		 if(data[i].startTime==(data[j].startTime)){
		                    			 temp=0;
		                    			 break;
		                    		 }
		                    	 }
		                    	 if(temp==1){
		                    		 html=html +'<option value=""+ data[i].startTime+"">'+data[i].startTime+'</option>';
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
		  
		  alert("��ѡ��һ������!");
	  }

 }
 
 
 //虎丘雅思培训的结束时间
 function getOutTime(){
	  
	  
	  var temp=1;
	  var agencyName= $("#agencyName").find("option:selected").text();   	  
	  var startTime= $("#startTime").find("option:selected").text();
	  

	  if(agencyName!=null && startTime!=null){
		  $.ajax(		  
	    	      {
	    	    	  
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/Graduate/simulationExamAction!getOutTimeInformation",
	    	    	  data:{agencyName:agencyName,startTime:startTime},
	    	    	  async:false,
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  
	    	    			 var html="";
		    	    		 html=html +'<option selected>--��ѡ�����ʱ��--</option>';

		                     for(var i=0; i<data.length; i++){
		                    	
		                    	   for(var j=0; j<i; j++){
		                    				 
		                    		 if(data[i].outTime==(data[j].outTime)){
		                    			 temp=0;
		                    			 break;
		                    		 }
		                    	 }
		                    	 if(temp==1){
		                    		 html=html +'<option value=""+ data[i].outTime+"">'+data[i].outTime+'</option>';
		                    	 }
		                    	 temp=1;
		                     }
		                     
	                     $('#outTime').html(html);
	                   },   
	                   error: function(data){
	                	   
	                		 var html="";
	                		 $('#outTime').html(html);
	                   }   	  
	    	    	  
	    	      }

	    	  );
	  }else{
		  
		  alert("��ѡ��һ������!");
	  }
	  

 }

 
//获取雅思培训的课时
 function getCourseHours(){
	  
	  
	  var temp=1;
	  var agencyName= $("#agencyName").find("option:selected").text();   	  
	  var startTime= $("#startTime").find("option:selected").text();
	  var outTime= $("#outTime").find("option:selected").text();

	  if(agencyName!=null && startTime!=null && outTime!=null){
		  $.ajax(		  
	    	      {
	    	    	  
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/Graduate/simulationExamAction!getCourseHoursInformation",
	    	    	  data:{agencyName:agencyName,startTime:startTime,outTime:outTime},
	    	    	  async:false,
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  
	    	    			 var html="";
		    	    		 html=html +'<option selected>--��ѡ���ʱ--</option>';

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
		  
		  alert("��ѡ��һ������!");
	  }
	  

 }
 
 //获取雅思培训的学期
 function getSemester(){
	  
	  
	  var temp=1;
	  var agencyName= $("#agencyName").find("option:selected").text();   	  
	  var startTime= $("#startTime").find("option:selected").text();
	  var outTime= $("#outTime").find("option:selected").text();
	  var courseHours2= $("#courseHours").find("option:selected").text();
	  
	  

	  if(agencyName!=null && startTime!=null && outTime!=null && courseHours!=null){
		  $.ajax(		  
	    	      {
	    	    	  
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/Graduate/simulationExamAction!getSemesterInformation",
	    	    	  data:{agencyName:agencyName,startTime:startTime,outTime:outTime,courseHours2:courseHours2},
	    	    	  async:false,
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  
	    	    			 var html="";
		    	    		 html=html +'<option selected>--��ѡ��ѧ��--</option>';

		                     for(var i=0; i<data.length; i++){
		                    	
		                    	   for(var j=0; j<i; j++){
		                    				 
		                    		 if(data[i].semester==(data[j].semester)){
		                    			 temp=0;
		                    			 break;
		                    		 }
		                    	 }
		                    	 if(temp==1){
		                    		 html=html +'<option value=""+ data[i].semester+"">'+data[i].semester+'</option>';
		                    	 }
		                    	 temp=1;
		                     }
		                     
	                     $('#semester').html(html);
	                   },   
	                   error: function(data){
	                	   
	                		 var html="";
	                		 $('#semester').html(html);
	                   }   	  
	    	    	  
	    	      }

	    	  );
	  }else{
		  
		  alert("��ѡ��һ������!");
	  }
 }

	  
 	  //获取模拟考试的时间
	  function getSimulationTime(){
	 	  
	 	  var temp=1;
	 	  var agencyName= $("#agencyName").find("option:selected").text();   	  
	 	  var startTime= $("#startTime").find("option:selected").text();
	 	  var outTime= $("#outTime").find("option:selected").text();
	 	  var courseHours2= $("#courseHours").find("option:selected").text();
	 	  var semester= $("#semester").find("option:selected").text();

	 		  $.ajax(		  
	 	    	      {
	 	    	    	  
	 	    	    	  type:"post",
	 	    	    	  url:"http://localhost:8080/Graduate/simulationExamAction!getSimulationInformation",
	 	    	    	  data:{agencyName:agencyName,startTime:startTime,outTime:outTime,courseHours2:courseHours2,semester:semester},
	 	    	    	  async:false,
	 	    	    	  dataType:"json",			    	
	 	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	 	    	    	  traditional:true,
	 	    	    	  success: function(data){
	 	    	    		  
	 	    	    			 var html="";
	 		    	    		 html=html +'<option selected>--��ѡ����ʱ��--</option>';

	 		                     for(var i=0; i<data.length; i++){
	 		                    	
	 		                    	   for(var j=0; j<i; j++){
	 		                    				 
	 		                    		 if(data[i].time==(data[j].time)){
	 		                    			 temp=0;
	 		                    			 break;
	 		                    		 }
	 		                    	 }
	 		                    	 if(temp==1){
	 		                    		 html=html +'<option value=""+ data[i].time+"">'+data[i].time+'</option>';
	 		                    	 }
	 		                    	 temp=1;
	 		                     }
	 		                     
	 	                     $('#simulationTime').html(html);
	 	                   },   
	 	                   error: function(data){
	 	                	   
	 	                		 var html="";
	 	                		 $('#simulationTime').html(html);
	 	                   }   	  
	 	    	    	  
	 	    	      }

	 	    	  );

	  }

 
	//获取模拟考试的地点
 function getSimulationLocation(){
	  
	  var temp=1;
	  var agencyName= $("#agencyName").find("option:selected").text();   	  
	  var startTime= $("#startTime").find("option:selected").text();
	  var outTime= $("#outTime").find("option:selected").text();
	  var courseHours2= $("#courseHours").find("option:selected").text();
	  var semester= $("#semester").find("option:selected").text();
	  var simulationTime= $("#simulationTime").find("option:selected").text();

		  $.ajax(		  
	    	      {
	    	    	  
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/Graduate/simulationExamAction!getSimulationLocationInformation",
	    	    	  data:{agencyName:agencyName,startTime:startTime,outTime:outTime,courseHours2:courseHours2,semester:semester,simulationTime:simulationTime},
	    	    	  async:false,
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  
	    	    			 var html="";
		    	    		 html=html +'<option selected>--��ѡ���Եص�--</option>';

		                     for(var i=0; i<data.length; i++){
		                    	
		                    	   for(var j=0; j<i; j++){
		                    				 
		                    		 if(data[i].location==(data[j].location)){
		                    			 temp=0;
		                    			 break;
		                    		 }
		                    	 }
		                    	 if(temp==1){
		                    		 html=html +'<option value=""+ data[i].location+"">'+data[i].location+'</option>';
		                    	 }
		                    	 temp=1;
		                     }
		                     
	                     $('#location').html(html);
	                   },   
	                   error: function(data){
	                	   
	                		 var html="";
	                		 $('#location').html(html);
	                   }   	  
	    	    	  
	    	      }

	    	  );
 }

 
 
//获取正式考试的时间
 function getFormalTime(){
	  
	  var temp=1;
	  var agencyName= $("#agencyName").find("option:selected").text();   	  
	  var startTime= $("#startTime").find("option:selected").text();
	  var outTime= $("#outTime").find("option:selected").text();
	  var courseHours2= $("#courseHours").find("option:selected").text();
	  var semester= $("#semester").find("option:selected").text();

		  $.ajax(		  
	    	      {
	    	    	  
	    	    	  type:"post",
	    	    	  url:"http://localhost:8080/Graduate/formalExamAction!getFormalTimeInformation",
	    	    	  data:{agencyName:agencyName,startTime:startTime,outTime:outTime,courseHours2:courseHours2,semester:semester},
	    	    	  async:false,
	    	    	  dataType:"json",			    	
	    	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	    	  traditional:true,
	    	    	  success: function(data){
	    	    		  
	    	    			 var html="";
		    	    		 html=html +'<option selected>--��ѡ����ʱ��--</option>';

		                     for(var i=0; i<data.length; i++){
		                    	
		                    	   for(var j=0; j<i; j++){
		                    				 
		                    		 if(data[i].time==(data[j].time)){
		                    			 temp=0;
		                    			 break;
		                    		 }
		                    	 }
		                    	 if(temp==1){
		                    		 html=html +'<option value=""+ data[i].time+"">'+data[i].time+'</option>';
		                    	 }
		                    	 temp=1;
		                     }
		                     
	                     $('#formalTime').html(html);
	                   },   
	                   error: function(data){
	                	   
	                		 var html="";
	                		 $('#formalTime').html(html);
	                   }   	  
	    	    	  
	    	      }

	    	  );

 }


//获取模拟考试的地点
function getFormalLocation(){
 

 var temp=1;
 var agencyName= $("#agencyName").find("option:selected").text();   	  
 var startTime= $("#startTime").find("option:selected").text();
 var outTime= $("#outTime").find("option:selected").text();
 var courseHours2= $("#courseHours").find("option:selected").text();
 var semester= $("#semester").find("option:selected").text();
 var formalTime= $("#formalTime").find("option:selected").text();

	  $.ajax(		  
   	      {
   	    	  
   	    	  type:"post",
   	    	  url:"http://localhost:8080/Graduate/formalExamAction!getFormalLocationInformation",
   	    	  data:{agencyName:agencyName,startTime:startTime,outTime:outTime,courseHours2:courseHours2,semester:semester,formalTime:formalTime},
   	    	  async:false,
   	    	  dataType:"json",			    	
   	    	  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   	    	  traditional:true,
   	    	  success: function(data){
   	    		  
   	    			 var html="";
	    	    		 html=html +'<option selected>--��ѡ���Եص�--</option>';

	                     for(var i=0; i<data.length; i++){
	                    	
	                    	   for(var j=0; j<i; j++){
	                    				 
	                    		 if(data[i].location==(data[j].location)){
	                    			 temp=0;
	                    			 break;
	                    		 }
	                    	 }
	                    	 if(temp==1){
	                    		 html=html +'<option value=""+ data[i].location+"">'+data[i].location+'</option>';
	                    	 }
	                    	 temp=1;
	                     }
	                     
                    $('#location').html(html);
                  },   
                  error: function(data){
               	   
               		 var html="";
               		 $('#location').html(html);
                  }   	  
   	    	  
   	      }

   	  );
	  
	  
	  
	  
}