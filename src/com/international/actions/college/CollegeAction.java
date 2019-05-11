package com.international.actions.college;

import java.util.List;
import java.util.Map;

import com.international.dao.CollegeDao;
import com.international.model.College;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CollegeAction extends ActionSupport {
	private List<College> colleges;
	Map m;
	CollegeDao cd;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String search="";
	public CollegeAction() {
		
	}
	public List<College> getColleges() {
		return colleges;
	}
	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}
	public Map getM() {
		return m;
	}
	public void setM(Map m) {
		this.m = m;
	}
	public CollegeDao getCd() {
		return cd;
	}
	public void setCd(CollegeDao cd) {
		this.cd = cd;
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
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String execute() {
		m=ActionContext.getContext().getSession();
		colleges=cd.queryAllCollege();
		//计算总页数
		if(colleges.size()%pageSize==0){
			totalPage=colleges.size()/pageSize;
		}else{
			totalPage=colleges.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		colleges=cd.queryCollege(search,pageNo,pageSize);
		//设置当前页
		currentPage=pageNo;
		
		return SUCCESS;
	}
	
	/**
	 *  删除国际院校信息
	 * @return
	 */
	public String deleteObject() {
		System.out.println("要删除的id:" + id);
		if(deleteCollege(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
}
