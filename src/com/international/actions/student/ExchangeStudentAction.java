package com.international.actions.student;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.ExchangeStuDao;
import com.international.model.ExchangeStudent;
import com.international.model.InternationalStudent;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExchangeStudentAction extends ActionSupport {
	
	private int stuId;
	private List<ExchangeStudent> exchangeStudents;
	private ExchangeStudent exchangeStudent;
	ExchangeStuDao esd;
	private String loginUserName = "";  //筛选搜索用户名
	private int id; //界面显示数据的索引
	private final int pageSize=4; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage; //当前页
	private int totalPage; //总页数
	
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public List<ExchangeStudent> getExchangeStudents() {
		return exchangeStudents;
	}
	public void setExchangeStudents(List<ExchangeStudent> exchangeStudents) {
		this.exchangeStudents = exchangeStudents;
	}
	public ExchangeStudent getExchangeStudent() {
		return exchangeStudent;
	}
	public void setExchangeStudent(ExchangeStudent exchangeStudent) {
		this.exchangeStudent = exchangeStudent;
	}
	public ExchangeStuDao getEsd() {
		return esd;
	}
	public void setEsd(ExchangeStuDao esd) {
		this.esd = esd;
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
	
	//分页查询+筛选
	public String showStudent(){
		//查询所有数据存于集合对象中
		exchangeStudents = esd.queryInterStudents(loginUserName);
		//System.out.println("查询全部的："+interStudents);
		if(exchangeStudents!=null) {
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
			//分页，根据当前页查询要在该页上显示的4条数据
			exchangeStudents = esd.queryByPage(loginUserName,pageNo,pageSize);
			System.out.println(exchangeStudents);
			//设置当前页
			currentPage = pageNo;
			if(exchangeStudents!=null){
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
	
	//删除交换生
	public String deleteExchangeStudent() {
		if(esd.delExStudent(stuId)) {
			System.out.println("删除成功");
			return "delSucc";
		}else {
			System.out.println("删除失败");
			return "delFail";
		}
	}
	
	//查询选中的交换生信息   
	public String queryStudent() {
		System.out.println(stuId);
		exchangeStudent = esd.queryById(stuId);
		Map session = ActionContext.getContext().getSession();
		session.put("editExStudent",exchangeStudent);
		if(exchangeStudent!=null) {
			return "detailSucc";
		}else {
			return INPUT;
		}
	}
	
	//修改交换生信息    
	public void editStudent() throws IOException{
		//System.out.println("测试是否有进入修改action");
		//exchangeStudent.setClasses(esd.queryByClassName(className));
		//System.out.println("测试："+interStudent.getClasses().getClassName());
		String message = "";
		if(esd.updateStudent(exchangeStudent)) {
			System.out.println("更新成功");
			message = "更新成功";
		}else {
			message = "更新失败";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
	
	//添加交换生
	public void addExchangeStudent() throws IOException {
		String message = "";
		if(esd.addExStudent(exchangeStudent)) {
			System.out.println("添加成功");
			message = "添加成功";
		}else{
			System.out.println("添加失败");
			message = "添加失败";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
}
