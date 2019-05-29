package com.international.actions.student;

import java.util.List;

import com.international.dao.StudentDao;
import com.international.model.Admin;
import com.international.model.InternationalStudent;
import com.opensymphony.xwork2.ActionSupport;

public class InternationalStudentAction extends ActionSupport {
	
	private List<InternationalStudent> interStudents;
	private InternationalStudent interStudent;
	StudentDao sd;
	private String loginUserName = "";  //筛选搜索用户名
	private int id; //界面显示数据的索引
	private final int pageSize=4; //每页显示记录的个数
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
	
	
	//分页查询+筛选
	public String showOperator(){
		//查询所有数据存于集合对象中
		interStudents = sd.queryInterStudents(loginUserName);
		System.out.println("查询全部的："+interStudents);
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
}
