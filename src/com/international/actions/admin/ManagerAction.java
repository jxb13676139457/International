package com.international.actions.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.UserDao;
import com.international.model.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport {
	
	private String adminId;
	private List<Admin> admins;
	private Admin admin;
	private UserDao ud;
	private int id; //界面显示数据的索引
	private final int pageSize=4; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage; //当前页
	private int totalPage; //总页数
	
	private String password;
	private String userName;
	private String sex;
	private String type;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	private String loginUserName = "";   //筛选搜索用户名
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public List<Admin> getAdmins() {
		return admins;
	}
	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}
	public UserDao getUd() {
		return ud;
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
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
	public String showOperator(){
		//Map map = ActionContext.getContext().getSession();
		//map.put("search",loginUserName);
		//查询所有数据存于集合对象中
		admins = ud.queryAdmins(loginUserName);
		Map session = ActionContext.getContext().getSession();
		session.put("searchLoginUser",loginUserName);
		System.out.println("查询全部的："+admins);
		if(admins!=null) {
			if(admins.size()%pageSize==0){
				totalPage = admins.size()/pageSize; 
			}else{
				totalPage = admins.size()/pageSize+1;
			}
			if(pageNo<=0){
				pageNo = 1;
			}else if(pageNo>=totalPage){
				pageNo = totalPage;
			}
			//分页，根据当前页查询要在该页上显示的4条数据
			admins = ud.queryByPage(loginUserName,pageNo,pageSize);
			System.out.println(admins);
			//设置当前页
			currentPage = pageNo;
			if(admins!=null){
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
	
	//删除操作员
	public String deleteLoginUser() {
		if(ud.deleteOperator(adminId)) {
			System.out.println("删除成功");
			return "delSucc";
		}else {
			addFieldError("tip","删除失败");
			return INPUT;
		}
	}
	
	//查询选中的操作员信息
	public String queryLoginUser() {
		System.out.println(adminId);
		admin = ud.queryById(adminId);
		Map map = ActionContext.getContext().getSession();
		map.put("editAdmin",admin);
		System.out.println(admin);
		if(admin!=null) {
			return "detailSucc";
		}else {
			return INPUT;
		}
	}
	
	//修改操作员信息
	public void editLoginUser() throws IOException {
		String message = "";
		if(ud.updateOperator(adminId,admin)) {
			System.out.println("更新成功");
			message = "更新成功";
			//return "updateSucc";
		}else {
			System.out.println("更新失败");
			message = "更新失败";
			//return INPUT;
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
}

