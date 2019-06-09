package com.international.actions.ielts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.AttendTrainingDao;
import com.international.model.AttendTraining;
import com.international.model.College;
import com.international.model.InternationalClass;
import com.international.model.OverseasStudent;
import com.international.model.Training;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AttendTrainingAction extends ActionSupport{
	
	private List<AttendTraining> attendTrainings;
	private AttendTraining attendTraining;
	private int attendId;   //前台传过来的id
	AttendTrainingDao atd;
	private String loginUserName = "";  //筛选搜索用户名
	private int id; //界面显示数据的索引
	private final int pageSize=4; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage; //当前页
	private int totalPage; //总页数
	
	//需要用来智能化动态获取的操作属性
	private String studentId;
	private String agencyName;
	private String startTime;
	private String endTime;
	private int courseHours;
	private int courseFee;
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getCourseHours() {
		return courseHours;
	}
	public void setCourseHours(int courseHours) {
		this.courseHours = courseHours;
	}
	public int getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
	public AttendTrainingDao getAtd() {
		return atd;
	}
	public void setAtd(AttendTrainingDao atd) {
		this.atd = atd;
	}
	public List<AttendTraining> getAttendTrainings() {
		return attendTrainings;
	}
	public void setAttendTrainings(List<AttendTraining> attendTrainings) {
		this.attendTrainings = attendTrainings;
	}
	public AttendTraining getAttendTraining() {
		return attendTraining;
	}
	public void setAttendTraining(AttendTraining attendTraining) {
		this.attendTraining = attendTraining;
	}
	public int getAttendId() {
		return attendId;
	}
	public void setAttendId(int attendId) {
		this.attendId = attendId;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}

	//分页查询+筛选  此筛选功能不支持班级名称，专业，培训机构名称
	public String showAttend(){
		//System.out.println("测试有无进入此action");
		//查询所有数据存于集合对象中
		attendTrainings = atd.queryAttends(loginUserName);
		//System.out.println("查询全部的："+interStudents);
		if(attendTrainings!=null) {
			if(attendTrainings.size()%pageSize==0){
				totalPage = attendTrainings.size()/pageSize; 
			}else{
				totalPage = attendTrainings.size()/pageSize+1;
			}
			if(pageNo<=0){
				pageNo = 1;
			}else if(pageNo>=totalPage){
				pageNo = totalPage;
			}
			//分页，根据当前页查询要在该页上显示的4条数据
			attendTrainings = atd.queryByPage(loginUserName,pageNo,pageSize);
			System.out.println(attendTrainings);
			//设置当前页
			currentPage = pageNo;
			if(attendTrainings!=null){
				loginUserName = "";  //为了重置搜索框传的值，避免点击显示全部按钮还会保留传入的值进而还是在筛选
				return SUCCESS;
			}else{
				loginUserName = "";
				return INPUT;
			}
		}else {
			//搜索不到数据，则把分页的页码清零
			pageNo = 0;
			totalPage = 0;
			currentPage = 0;
			loginUserName = "";
			return INPUT;
		}
	}
	
	//删除学生参与培训计划的记录
	public String deleteAttendTraining() {
		if(atd.delAttendTraining(attendId)) {
			System.out.println("删除成功");
			return "delSucc";
		}else {
			System.out.println("删除失败");
			return INPUT;
		}
	}
	
	/*//查询选中的学生参与培训计划的记录   不符合需求所以不做修改功能
	public String queryAttendTraining() {
		attendTraining = atd.queryById(attendId);
		Map session = ActionContext.getContext().getSession();
		session.put("editAttendTraining",attendTraining);
		if(attendTraining!=null) {
			return "detailSucc";
		}else {
			return INPUT;
		}
	}
	*/
	
	//根据培训机构名字获取培训计划开始时间
	public void getStartTimes() throws IOException{
		//System.out.println("agencyName:"+ agencyName);
		List<Training> trainingList=new ArrayList<Training>();
		trainingList = atd.queryByAgencyName(agencyName);
		if(trainingList!=null && trainingList.size()>0){
			//System.out.println("trainingList："+ trainingList.size());	
		    for(int i = 0 ;i<trainingList.size();i++){
			  //设置关联的培训机构为空
		    	trainingList.get(i).setAgencies(null);
	        }
		}
		//System.out.println("培训计划对象："+trainingList);
		ajaxAction.toJson(ServletActionContext.getResponse(),trainingList);
	}
	
	//根据培训机构名字获取培训计划结束时间
	public void getEndTimes() throws IOException{
		//System.out.println("agencyName:"+ agencyName);
		List<Training> trainingList=new ArrayList<Training>();
		trainingList = atd.queryByAgencyName(agencyName);
		if(trainingList!=null && trainingList.size()>0){
		    for(int i = 0 ;i<trainingList.size();i++){
			  //设置关联的培训机构为空
		    	trainingList.get(i).setAgencies(null);
	        }
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),trainingList);
	}
	
	//根据培训机构名字获取培训计划课时
	public void getAllCourseHours() throws IOException{
		//System.out.println("agencyName:"+ agencyName);
		List<Training> trainingList=new ArrayList<Training>();
		trainingList = atd.queryByAgencyName(agencyName);
		if(trainingList!=null && trainingList.size()>0){
		    for(int i = 0 ;i<trainingList.size();i++){
			  //设置关联的培训机构为空
		    	trainingList.get(i).setAgencies(null);
	        }
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),trainingList);
	}
	
	//根据培训机构名字获取培训计划费用
	public void getAllCourseFee() throws IOException{
		//System.out.println("agencyName:"+ agencyName);
		List<Training> trainingList=new ArrayList<Training>();
		trainingList = atd.queryByAgencyName(agencyName);
		if(trainingList!=null && trainingList.size()>0){
		    for(int i = 0 ;i<trainingList.size();i++){
			  //设置关联的培训机构为空
		    	trainingList.get(i).setAgencies(null);
	        }
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),trainingList);
	}
		
	//加入学生培训计划
	public void addAttendTraining() throws IOException {
		//System.out.println("测试是否进入此action");
		String message = "";
		System.out.println("studentId:"+studentId);
		System.out.println("agencyName:"+agencyName);
		System.out.println("startTime:"+startTime);
		System.out.println("endTime:"+endTime);
		System.out.println("courseHours:"+courseHours);   
		System.out.println("courseFee:"+courseFee);   
		attendTraining.setInterStudent(atd.queryByStuId(studentId));
		attendTraining.setTraining(atd.queryByTraining(startTime,endTime,courseHours,courseFee));
		if(atd.addAttend(attendTraining)) {
			System.out.println("添加成功");
			message = "添加成功";
		}else{
			System.out.println("添加失败");
			message = "添加失败,没有找到这条培训计划，建议去培训计划模块看看";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
}
