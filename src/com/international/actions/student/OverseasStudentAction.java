package com.international.actions.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.OverseasStuDao;
import com.international.model.College;
import com.international.model.InternationalClass;
import com.international.model.InternationalStudent;
import com.international.model.OverseasStudent;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OverseasStudentAction extends ActionSupport{
	
	private String studentId;   //前台传过来的id
	private OverseasStudent oversea;
	private List<OverseasStudent> overseas;
	OverseasStuDao osd;
	private String loginUserName = "";  //筛选搜索用户名
	private int id; //界面显示数据的索引
	private final int pageSize=4; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage; //当前页
	private int totalPage; //总页数
	
	private String className;   //根据这个班级名称去找到对应的班级表
	private String collegeName;   //根据这个国外院校名称去找到对应的国外院校表
	private String newClassName;
	private String newCollegeName;
	
	public String getNewCollegeName() {
		return newCollegeName;
	}
	public void setNewCollegeName(String newCollegeName) {
		this.newCollegeName = newCollegeName;
	}
	public String getNewClassName() {
		return newClassName;
	}
	public void setNewClassName(String newClassName) {
		this.newClassName = newClassName;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public OverseasStudent getOversea() {
		return oversea;
	}
	public void setOversea(OverseasStudent oversea) {
		this.oversea = oversea;
	}
	public List<OverseasStudent> getOverseas() {
		return overseas;
	}
	public void setOverseas(List<OverseasStudent> overseas) {
		this.overseas = overseas;
	}
	public OverseasStuDao getOsd() {
		return osd;
	}
	public void setOsd(OverseasStuDao osd) {
		this.osd = osd;
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
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	//分页查询+筛选
	public String showStudent(){
		//System.out.println("测试有无进入此action");
		//查询所有数据存于集合对象中
		overseas = osd.queryOverseas(loginUserName);
		//System.out.println("查询全部的："+interStudents);
		if(overseas!=null) {
			if(overseas.size()%pageSize==0){
				totalPage = overseas.size()/pageSize; 
			}else{
				totalPage = overseas.size()/pageSize+1;
			}
			if(pageNo<=0){
				pageNo = 1;
			}else if(pageNo>=totalPage){
				pageNo = totalPage;
			}
			//分页，根据当前页查询要在该页上显示的4条数据
			overseas = osd.queryByPage(loginUserName,pageNo,pageSize);
			System.out.println(overseas);
			//设置当前页
			currentPage = pageNo;
			if(overseas!=null){
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
	
	//删除出国学生
	public String deleteOverseasStudent() {
		if(osd.delOverseas(studentId)) {
			System.out.println("删除成功");
			return "delSucc";
		}else {
			System.out.println("删除失败");
			return INPUT;
		}
	}
	
	//查询选中的出国学生信息   
	public String queryStudent() {
		oversea = osd.queryById(studentId);
		Map session = ActionContext.getContext().getSession();
		session.put("editOverseasStudent",oversea);
		if(oversea!=null) {
			return "detailSucc";
		}else {
			return INPUT;
		}
	}
	
	//修改出国学生信息    
	public void editStudent() throws IOException{
		String message = "";
		InternationalClass classes = osd.queryByClassName(className);
		//System.out.println(classes);
		College college = osd.queryByCollegeName(collegeName);
		//System.out.println(college);
		if(classes!=null) {
			if(college!=null) {
				oversea.setClasses(classes);
				if(osd.updateStudent(oversea)) {
					System.out.println("更新成功");
					message = "更新成功";
				}else {
					message = "更新失败";
				}
			}else {
				message = "没有与此对应的国外院校合作";
			}
		}else{
			message = "没有开设此对应的班级";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
	
	//获取学生的信息通过学号	
    public void getStudentInformation() throws IOException{
    	//Map  session =ActionContext.getContext().getSession();
    	InternationalStudent student = new InternationalStudent();
    	System.out.println("获取到的学号："+ studentId);
    	student = osd.queryByStuId(studentId);
    	if(student!=null){
    		student.setStudentId(studentId);
    		student.setClasses(student.getClasses());
    		System.out.println("自动获取的班级对象班级："+student.getClasses().getClassName());
    	}
    	//System.out.println("获取到的学号2："+ studentId);
    	String autoStudentName = student.getStudentName();
    	String autoClassName = student.getClasses().getClassName();
    	String autoMajor = student.getClasses().getMajor();
    	String autoSex = student.getSex();
    	String auto = autoStudentName+","+autoClassName+","+autoSex+","+autoMajor;
    	//System.out.println(auto);
    	ajaxAction.toJson(ServletActionContext.getResponse(),auto);
    }
	
	//加入出国学生
	public void addOveaStudent() throws IOException {
		//System.out.println("测试是否进入此action");
		String message = "";
		//System.out.println(interStudent);
		System.out.println("newCollegeName:"+newCollegeName);   //新添加的国外院校名称，和修改的国外院校名称不同
		System.out.println("newClassName:"+newClassName);   //新添加的班级名称，和修改的班级名称不同
		System.out.println("studentId:"+studentId);
		OverseasStudent existStudent = new OverseasStudent();
		existStudent = osd.queryById(studentId);
		//System.out.println("根据studentId到数据库中查找到存在的学生对象"+existStudent);
		if(existStudent!=null) {
			message = "已存在该学生，请重新输入学号";
		}else {
			//根据班级名称和国外院校名称把classes和college对象给设置进去，不然会报外键为空值异常
			oversea.setClasses(osd.queryByClassName(newClassName));
			oversea.setCollege(osd.queryByCollegeName(newCollegeName));
			if(osd.addStudent(oversea)) {
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
