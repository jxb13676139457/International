package com.international.actions.student;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.StuActivityDao;
import com.international.model.OverseasStudent;
import com.international.model.StudentActivity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StudentActivityAction extends ActionSupport{
	
	private int activityId;   //前台传过来的id
	private List<StudentActivity> studentActivites;
	private StudentActivity studentActivity;
	StuActivityDao sad;
	
	private String loginUserName = "";  //筛选搜索用户名
	private int id; //界面显示数据的索引
	private final int pageSize=8; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage; //当前页
	private int totalPage; //总页数
	
	private String collegeName;   //根据这个国外院校名称去查找国外院校的对象
	private String newCollegeName;   //新添加的班级名称
	
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public List<StudentActivity> getStudentActivites() {
		return studentActivites;
	}
	public void setStudentActivites(List<StudentActivity> studentActivites) {
		this.studentActivites = studentActivites;
	}
	public StudentActivity getStudentActivity() {
		return studentActivity;
	}
	public void setStudentActivity(StudentActivity studentActivity) {
		this.studentActivity = studentActivity;
	}
	public StuActivityDao getSad() {
		return sad;
	}
	public void setSad(StuActivityDao sad) {
		this.sad = sad;
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
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getNewCollegeName() {
		return newCollegeName;
	}
	public void setNewCollegeName(String newCollegeName) {
		this.newCollegeName = newCollegeName;
	}
	
	//分页查询+筛选
	public String showStuActivity(){
		//查询所有数据存于集合对象中
		studentActivites = sad.queryStuActivities(loginUserName);
		Map session = ActionContext.getContext().getSession();
		session.put("searchStudentActivity",loginUserName);
		//System.out.println("查询全部的："+studentActivites);
		if(studentActivites!=null) {
			if(studentActivites.size()%pageSize==0){
				totalPage = studentActivites.size()/pageSize; 
			}else{
				totalPage = studentActivites.size()/pageSize+1;
			}
			if(pageNo<=0){
				pageNo = 1;
			}else if(pageNo>=totalPage){
				pageNo = totalPage;
			}
			//分页，根据当前页查询要在该页上显示的4条数据
			studentActivites = sad.queryByPage(loginUserName,pageNo,pageSize);
			//System.out.println(studentActivites);
			//设置当前页
			currentPage = pageNo;
			if(studentActivites!=null){
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
	
	//删除学生活动信息
	public String deleteStudentActivity() {
		if(sad.delStuActivity(activityId)) {
			System.out.println("删除成功");
			return "delSucc";
		}else {
			System.out.println("删除失败");
			return "delFail";
		}
	}
	
	//查询选中的学生活动信息   
	public String queryStuActivity() {
		System.out.println(activityId);
		studentActivity = sad.queryById(activityId);
		Map session = ActionContext.getContext().getSession();
		session.put("editStuActivity",studentActivity);
		//System.out.println(interStudent);
		if(studentActivity!=null) {
			return "detailSucc";
		}else {
			return INPUT;
		}
	}
	
	//修改学生活动信息
	public void editStudentActivity() throws IOException{
		//System.out.println("测试是否有进入修改action");
		studentActivity.setCollege(sad.queryByCollegeName(collegeName));
		//System.out.println("测试："+studentActivity.getCollege().getCollegeName());
		String message = "";
		if(sad.updateStuActivity(studentActivity)) {
			System.out.println("更新成功");
			message = "更新成功";
		}else {
			message = "更新失败";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
	
	//加入学生活动信息
	public void addStudentActivity() throws IOException {
		//System.out.println("测试是否进入此action");
		String message = "";
		System.out.println("newCollegeName:"+newCollegeName);   //新添加的国外院校名称，和修改的国外院校名称不同
		//根据国外院校名称把college对象给设置进去，不然会报外键为空值异常
		studentActivity.setCollege(sad.queryByCollegeName(newCollegeName));
		if(sad.addStuActivity(studentActivity)) {
			System.out.println("添加成功");
			message = "添加成功";
		}else{
			System.out.println("添加失败");
			message = "添加失败";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
}
