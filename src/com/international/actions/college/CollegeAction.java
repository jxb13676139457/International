package com.international.actions.college;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.international.actions.ajaxAction;
import com.international.dao.CollegeDao;
import com.international.model.College;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CollegeAction extends ActionSupport {
	private List<College> colleges;
	Map m;
	List<College> collegeList;
	CollegeDao cd;
	College currentCollege;//修改国际院校信息所用
	College addCollege;//添加国际院校信息所用
	College updateCollege;//更新国际院校信息所用
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String search="";//查询条件
	String abroadTime;//合作时间
	public CollegeAction() {
		
	}
	public List<College> getCollegeList() {
		return collegeList;
	}
	public void setCollegeList(List<College> collegeList) {
		this.collegeList = collegeList;
	}
	public College getAddCollege() {
		return addCollege;
	}
	public void setAddCollege(College addCollege) {
		this.addCollege = addCollege;
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
	
	public College getCurrentCollege() {
		return currentCollege;
	}
	public void setCurrentCollege(College currentCollege) {
		this.currentCollege = currentCollege;
	}
	public String getAbroadTime() {
		return abroadTime;
	}
	public void setAbroadTime(String abroadTime) {
		this.abroadTime = abroadTime;
	}
	public College getUpdateCollege() {
		return updateCollege;
	}
	public void setUpdateCollege(College updateCollege) {
		this.updateCollege = updateCollege;
	}
	public String execute() {
		m=ActionContext.getContext().getSession();
		m.put("searchCollege", search);
		colleges=cd.queryAllCollege(search);
		//查询到是空直接返回
		if(colleges==null)
			return SUCCESS;
		//System.out.println(search);
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
		for(int i=0;i<colleges.size();i++) {
			colleges.get(i).setStartTime(colleges.get(i).getStartTime().substring(0, 10));
		}
		//设置当前页
		currentPage=pageNo;
		m.put("colleges", colleges);
		return SUCCESS;
	}
	
	/**
	 *  删除国际院校信息
	 * @return
	 */
	public String deleteObject() {
		System.out.println("要删除的id:" + id);
		if(cd.deleteCollege(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	/**
	 *  修改前查询国际院校信息
	 * @return
	 */
	public String searchObjectById() {
		m=ActionContext.getContext().getSession();
		System.out.println("要查询的用户名id:" + id);
		currentCollege=cd.getAbroadCollegeInforById(id);
		if(currentCollege!=null) {
			abroadTime=currentCollege.getStartTime();
			currentCollege.setStartTime(currentCollege.getStartTime().substring(0, 10));
			m.put("currentCollege", currentCollege);
			return "lookSuccess";
		}
		else {
			String message="获取数据失败!";
			m.put("error", message);
			return "lookError";
		}
		
	}
	/**
	 *  添加国际院校信息
	 * @return
	 */
	public void addObject() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String message="";
		System.out.println(addCollege.getCollegeName());
		addCollege.setStartTime(addCollege.getStartTime().toString().substring(0,10));
		
		
		String hql1="collegeName = '"+addCollege.getCollegeName()+"'";
		String hql2="startTime = '"+addCollege.getStartTime()+"'";
		String hql3="type = '"+addCollege.getType()+"'";
		String hql4="status = '"+addCollege.getStatus()+"'";
		String hql5="country like '%"+addCollege.getCountry()+"%'";
		String hql6="contactPerson like '%"+addCollege.getContactPerson()+"%'";
		String hql7="phone like '%"+addCollege.getPhone()+"%'";
		String hql8="address like '%"+addCollege.getAddress()+"%'";
		String hql9="phone like '%"+addCollege.getPhone()+"%'";
		String hql10="email like '%"+addCollege.getEmail()+"%'";
		String hql="from College where "+ hql1 +" and " +hql2  +" and " +hql3 +" and " +hql4+" and "+ hql5 +" and "+ hql6 +" and "+ hql7+" and "+ hql8+" and "+ hql9+" and "+ hql10;
		collegeList=cd.queryByhql(hql);
		
		if(collegeList==null || collegeList.size()==0) {
			if(cd.addCollege(addCollege)>0) {
				message="添加成功";
			}
			else {
				message="添加失败";
			}
		}
		else {
			message="已存在该条信息";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
	/**
	 *  修改国际院校信息
	 * @return
	 */
	public void updateInfor() throws IOException{
		updateCollege.setStartTime(updateCollege.getStartTime().substring(0, 10));
		String message="";
		String hql1="collegeName = '"+updateCollege.getCollegeName()+"'";
		String hql2="startTime = '"+updateCollege.getStartTime()+"'";
		String hql3="type = '"+updateCollege.getType()+"'";
		String hql4="status = '"+updateCollege.getStatus()+"'";
		String hql5="country like '%"+updateCollege.getCountry()+"%'";
		String hql6="contactPerson like '%"+updateCollege.getContactPerson()+"%'";
		String hql7="phone like '%"+updateCollege.getPhone()+"%'";
		String hql8="address like '%"+updateCollege.getAddress()+"%'";
		String hql9="phone like '%"+updateCollege.getPhone()+"%'";
		String hql10="email like '%"+updateCollege.getEmail()+"%'";
		String hql="from College where "+ hql1 +" and " +hql2  +" and " +hql3 +" and " +hql4+" and "+ hql5 +" and "+ hql6 +" and "+ hql7+" and "+ hql8+" and "+ hql9+" and "+ hql10;
		collegeList=cd.queryByhql(hql);
		if(collegeList==null || collegeList.size()==0) {
			if(cd.updateCollege(updateCollege)) {
				message="更新成功";
			}
			else {
				message="更新失败";
			}
		}
		else {
			message="已存在该条信息";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
	/**
	 *  获取国际院校信息到select列表中
	 * @return
	 */
	public void getCollegeInformation() throws IOException{
		String hql="from College";
		List<College> collegeList= new ArrayList<College>();
		collegeList=cd.queryByhql(hql);
		ajaxAction.toJson(ServletActionContext.getResponse(),collegeList);
	}
}

	