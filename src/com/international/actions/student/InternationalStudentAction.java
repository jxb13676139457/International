package com.international.actions.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.StudentDao;
import com.international.model.Admin;
import com.international.model.InternationalClass;
import com.international.model.InternationalStudent;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InternationalStudentAction extends ActionSupport {
	
	private String studentId;   //前台传过来的id
	private List<InternationalStudent> interStudents;
	private InternationalStudent interStudent;
	StudentDao sd;
	private String loginUserName = "";  //筛选搜索用户名
	private int id; //界面显示数据的索引
	private final int pageSize=4; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage; //当前页
	private int totalPage; //总页数
	
	private String className;
	private String newClassName;  //添加的新班级名称
	private String grade;   
	private String major;
	private InternationalClass classes;
	
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
	public List<InternationalStudent> getInterStudents() {
		return interStudents;
	}
	public void setInterStudents(List<InternationalStudent> interStudents) {
		this.interStudents = interStudents;
	}
	public InternationalStudent getInterStudent() {
		return interStudent;
	}
	public void setInterStudent(InternationalStudent interStudent) {
		this.interStudent = interStudent;
	}
	public StudentDao getSd() {
		return sd;
	}
	public void setSd(StudentDao sd) {
		this.sd = sd;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public InternationalClass getClasses() {
		return classes;
	}
	public void setClasses(InternationalClass classes) {
		this.classes = classes;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getNewClassName() {
		return newClassName;
	}
	public void setNewClassName(String newClassName) {
		this.newClassName = newClassName;
	}
	
	//分页查询+筛选
	public String showStudent(){
		//查询所有数据存于集合对象中
		interStudents = sd.queryInterStudents(loginUserName);
		//System.out.println("查询全部的："+interStudents);
		if(interStudents!=null) {
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
			//分页，根据当前页查询要在该页上显示的4条数据
			interStudents = sd.queryByPage(loginUserName,pageNo,pageSize);
			System.out.println(interStudents);
			//设置当前页
			currentPage = pageNo;
			if(interStudents!=null){
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
	
	//删除国际班学生
	public String deleteInterStudent() {
		if(sd.delInterStudent(studentId)) {
			System.out.println("删除成功");
			return "delSucc";
		}else {
			System.out.println("删除失败");
			return "delFail";
		}
	}
	
	//查询选中的国际班学生信息   
	public String queryStudent() {
		//System.out.println(studentId);
		interStudent = sd.queryById(studentId);
		Map session = ActionContext.getContext().getSession();
		session.put("editStudent",interStudent);
		//System.out.println(interStudent);
		if(interStudent!=null) {
			return "detailSucc";
		}else {
			return INPUT;
		}
	}
	
	//修改国际班学生信息    
	public void editStudent() throws IOException{
		//System.out.println("测试是否有进入修改action");
		interStudent.setClasses(sd.queryByClassName(className));
		//System.out.println("测试："+interStudent.getClasses().getClassName());
		String message = "";
		if(sd.updateStudent(interStudent)) {
			System.out.println("更新成功");
			message = "更新成功";
		}else {
			message = "更新失败";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
	
	//根据年级获取专业信息
	public void getProfessionInformation() throws IOException{
		System.out.println("grade:"+ grade);
		List<InternationalClass> classList=new ArrayList<InternationalClass>();
		classList = sd.queryByGrade(grade);
		if(classList!=null && classList.size()>0){
			System.out.println("classList："+ classList.size());	
		    for(int i = 0 ;i<classList.size();i++){
			  //设置关联的学生对象为空
			  classList.get(i).setInterStudents(null);
	        }
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),classList);
	}
	
	//根据年级和专业获取班级信息
	public void getclassInfromation() throws IOException{
		List<InternationalClass> classList = new ArrayList<InternationalClass>();
		//System.out.println("测试有无进入此action，hql语句：");
		classList = sd.queryByMajor(grade,major);
		if(classList!=null && classList.size()>0){
		   for(int i = 0 ;i<classList.size();i++){
			  classList.get(i).setInterStudents(null);
	       }
		   ajaxAction.toJson(ServletActionContext.getResponse(),classList);
		}
	}
	
	//加入国际班学生   
	public void addInterStudent() throws IOException {
		//System.out.println("测试是否进入此action");
		String message = "";
		//System.out.println(interStudent);
		System.out.println("major:"+major);
		System.out.println("newClassName:"+newClassName);   //新添加的班级名称，和修改的班级名称不同
		//根据班级名称把classes对象给设置进去，不然会报classId外键为空值异常
//		InternationalClass newClass = sd.queryByClassName(newClassName);
//		System.out.println(newClass);
		System.out.println("studentId:"+studentId);
		InternationalStudent existStudent = new InternationalStudent();
		existStudent = sd.queryById(studentId);
		//System.out.println("根据studentId到数据库中查找到存在的学生对象"+existStudent);
		if(existStudent!=null) {
			message = "已存在该学生，请重新输入学号";
		}else {
			interStudent.setClasses(sd.queryByClassName(newClassName));
			if(sd.addStudent(interStudent)) {
				System.out.println("添加成功");
				message = "添加成功";
			}else{
				System.out.println("添加失败");
				message = "添加失败";
			}
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
}
