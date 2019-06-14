package com.international.frontground.actions.student;

import java.util.List;
import java.util.Map;

import com.international.frontground.dao.PriStudentDao;
import com.international.model.ExchangeStudent;
import com.international.model.InternationalStudent;
import com.international.model.OverseasStudent;
import com.international.model.StudentActivity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PriorStudentAction extends ActionSupport{
	
	PriStudentDao psd;
	//other查询
	List<InternationalStudent> interStudents;
	List<ExchangeStudent> exchangeStudents;
	List<OverseasStudent> overseasStudents;
	List<StudentActivity> studentActivities;
	//学生查询
	InternationalStudent interStudent;
	ExchangeStudent exchangeStudent;
	OverseasStudent overseasStudent;
	List<StudentActivity> studentActivity;
	
	private int id; //界面显示数据的索引
	private final int pageSize=7; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage; //当前页
	private int totalPage; //总页数
	
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
	public PriStudentDao getPsd() {
		return psd;
	}
	public void setPsd(PriStudentDao psd) {
		this.psd = psd;
	}
	public List<InternationalStudent> getInterStudents() {
		return interStudents;
	}
	public void setInterStudents(List<InternationalStudent> interStudents) {
		this.interStudents = interStudents;
	}
	public List<ExchangeStudent> getExchangeStudents() {
		return exchangeStudents;
	}
	public void setExchangeStudents(List<ExchangeStudent> exchangeStudents) {
		this.exchangeStudents = exchangeStudents;
	}
	public List<OverseasStudent> getOverseasStudents() {
		return overseasStudents;
	}
	public void setOverseasStudents(List<OverseasStudent> overseasStudents) {
		this.overseasStudents = overseasStudents;
	}
	public List<StudentActivity> getStudentActivities() {
		return studentActivities;
	}
	public void setStudentActivities(List<StudentActivity> studentActivities) {
		this.studentActivities = studentActivities;
	}
	public InternationalStudent getInterStudent() {
		return interStudent;
	}
	public void setInterStudent(InternationalStudent interStudent) {
		this.interStudent = interStudent;
	}
	public ExchangeStudent getExchangeStudent() {
		return exchangeStudent;
	}
	public void setExchangeStudent(ExchangeStudent exchangeStudent) {
		this.exchangeStudent = exchangeStudent;
	}
	public OverseasStudent getOverseasStudent() {
		return overseasStudent;
	}
	public void setOverseasStudent(OverseasStudent overseasStudent) {
		this.overseasStudent = overseasStudent;
	}
	public List<StudentActivity> getStudentActivity() {
		return studentActivity;
	}
	public void setStudentActivity(List<StudentActivity> studentActivity) {
		this.studentActivity = studentActivity;
	}
	//教师查询国际班学生信息(分页处理)
	public String showInterStudent() {
		interStudents = psd.queryInterStudent();
		if(interStudents.size()%pageSize==0){
			totalPage = interStudents.size()/pageSize; 
		}else{
			totalPage = interStudents.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo = 1;
		}else if(pageNo>=totalPage){
			pageNo = totalPage;
		}
		String obj = "InternationalStudent";
		//分页，根据当前页查询要在该页上显示的4条数据
		interStudents = psd.queryByPage(obj,pageNo,pageSize);
		System.out.println(interStudents);
		//设置当前页
		currentPage = pageNo;
		if(interStudents.size()>0) {
			return "interStudentSucc";
		}else {
			//搜索不到数据，则把分页的页码清零
			pageNo = 0;
			totalPage = 0;
			currentPage = 0;
			return "interStudentFail";
		}
	}
	
	//教师查询国际班学生信息(分页处理)
	public String showExchangeStudent() {
		exchangeStudents = psd.queryExStudent();
		if(exchangeStudents.size()%pageSize==0){
			totalPage = exchangeStudents.size()/pageSize; 
		}else{
			totalPage = exchangeStudents.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo = 1;
		}else if(pageNo>=totalPage){
			pageNo = totalPage;
		}
		String obj = "ExchangeStudent";
		//分页，根据当前页查询要在该页上显示的4条数据
		exchangeStudents = psd.queryByPage(obj,pageNo,pageSize);
		System.out.println(exchangeStudents);
		//设置当前页
		currentPage = pageNo;
		if(exchangeStudents.size()>0) {
			return "exStudentSucc";
		}else {
			//搜索不到数据，则把分页的页码清零
			pageNo = 0;
			totalPage = 0;
			currentPage = 0;
			return "exStudentFail";
		}
	}
	
	//教师查询出国学生信息(分页处理)
	public String showOverseasStudent() {
		overseasStudents = psd.queryOverseas();
		if(overseasStudents.size()%pageSize==0){
			totalPage = overseasStudents.size()/pageSize; 
		}else{
			totalPage = overseasStudents.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo = 1;
		}else if(pageNo>=totalPage){
			pageNo = totalPage;
		}
		String obj = "OverseasStudent";
		//分页，根据当前页查询要在该页上显示的4条数据
		overseasStudents = psd.queryByPage(obj,pageNo,pageSize);
		System.out.println(overseasStudents);
		//设置当前页
		currentPage = pageNo;
		if(overseasStudents.size()>0) {
			return "overseasSucc";
		}else {
			//搜索不到数据，则把分页的页码清零
			pageNo = 0;
			totalPage = 0;
			currentPage = 0;
			return "overseasFail";
		}
	}
	
	//教师查询学生活动信息(分页处理)
	public String showStudentActivity() {
		studentActivities = psd.queryActivity();
		if(studentActivities.size()%pageSize==0){
			totalPage = studentActivities.size()/pageSize; 
		}else{
			totalPage = studentActivities.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo = 1;
		}else if(pageNo>=totalPage){
			pageNo = totalPage;
		}
		String obj = "StudentActivity";
		//分页，根据当前页查询要在该页上显示的4条数据
		studentActivities = psd.queryByPage(obj,pageNo,pageSize);
		System.out.println(studentActivities);
		//设置当前页
		currentPage = pageNo;
		if(studentActivities.size()>0) {
			return "activitySucc";
		}else {
			//搜索不到数据，则把分页的页码清零
			pageNo = 0;
			totalPage = 0;
			currentPage = 0;
			return "activityFail";
		}
	}
	
	//学生查询个人基本信息
	public String showPersonById() {
		Map session = ActionContext.getContext().getSession();
		//拿到session存的登录对象
		InternationalStudent student = (InternationalStudent) session.get("loginUser");
		//拿到登录学生的学号
		String stuId = student.getStudentId();
		System.out.println(stuId);
		String type1 = "InternationalStudent";
		interStudent = psd.queryStuById(stuId,type1);
		String type2 = "OverseasStudent";
		overseasStudent = psd.queryStuById(stuId,type2);
		String type3 = "ExchangeStudent";
		exchangeStudent = psd.queryStuById(stuId,type3);
		studentActivity = psd.queryStuActivityById(stuId);
		return "showPersonSucc";
	}
	
}
