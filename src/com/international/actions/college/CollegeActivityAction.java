package com.international.actions.college;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.international.actions.common.ajaxAction;
import com.international.dao.ActivityDao;
import com.international.dao.CollegeDao;
import com.international.model.College;
import com.international.model.CollegeActivity;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;


public class CollegeActivityAction extends ActionSupport{
	private List<CollegeActivity> collegeActivities;
	CollegeActivity addActivity;//添加活动
	CollegeActivity currentActivity;//查询活动
	CollegeActivity updateActivity;//修改活动
	List<CollegeActivity> activityList;
	ActivityDao ad;
	CollegeDao cd;
	Map m;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String activityTime="";//查询时间
	String search="";//查询名称
	String titles="";//查询主题
	String collegeName;//添加时对应的院校名称
	public CollegeActivityAction() {
		
	}
	
	public CollegeActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(CollegeActivity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public CollegeActivity getUpdateActivity() {
		return updateActivity;
	}

	public void setUpdateActivity(CollegeActivity updateActivity) {
		this.updateActivity = updateActivity;
	}

	public CollegeDao getCd() {
		return cd;
	}

	public void setCd(CollegeDao cd) {
		this.cd = cd;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public CollegeActivity getAddActivity() {
		return addActivity;
	}

	public void setAddActivity(CollegeActivity addActivity) {
		this.addActivity = addActivity;
	}

	public List<CollegeActivity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<CollegeActivity> activityList) {
		this.activityList = activityList;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<CollegeActivity> getCollegeActivities() {
		return collegeActivities;
	}
	public void setCollegeActivities(List<CollegeActivity> collegeActivities) {
		this.collegeActivities = collegeActivities;
	}
	public Map getM() {
		return m;
	}
	public void setM(Map m) {
		this.m = m;
	}
	public int getId() {
		return id;
	}
	
	public ActivityDao getAd() {
		return ad;
	}

	public void setAd(ActivityDao ad) {
		this.ad = ad;
	}

	
	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
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
	public String execute() throws Exception{
		m=ActionContext.getContext().getSession();
		collegeActivities=ad.queryAllActivity(titles,activityTime,search);
		//查询到是空直接返回
		if(collegeActivities==null)
			return SUCCESS;
		//计算总页数
		if(collegeActivities.size()%pageSize==0){
			totalPage=collegeActivities.size()/pageSize;
		}else{
			totalPage=collegeActivities.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		collegeActivities=ad.queryActivity(titles,activityTime,search, pageNo, pageSize);
		for(int i=0;i<collegeActivities.size();i++) {
			collegeActivities.get(i).setTime(collegeActivities.get(i).getTime().substring(0, 10));
		}
		//设置当前页
		currentPage=pageNo;
		m.put("collegeActivities", collegeActivities);
		return SUCCESS;
	}
	public String searchInformation() {
		m=ActionContext.getContext().getSession();
		
		return SUCCESS;
	}
	/**
	 *  删除活动信息
	 * @return
	 */
	public String deleteObject() {
		System.out.println("要删除的id:" + id);
		if(ad.deleteActicity(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	/**
	 *  添加活动信息
	 * @return
	 */
	public void addObject() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String message="";

		
		addActivity.setTime(addActivity.getTime().toString().substring(0,10));
		//ajax多出一个逗号，裁剪
		collegeName=collegeName.substring(0, collegeName.length()-2);

		//String hql1="collegeId = '"+addActivity.getColl().getCollegeId()+"'";
		String hql2="time = '"+addActivity.getTime()+"'";
		String hql3="type = '"+addActivity.getType()+"'";
		String hql4="content = '"+addActivity.getContent()+"'";
		String hql5="title like '%"+addActivity.getTitle()+"%'";
		String hql="from CollegeActivity where "  +hql2  +" and " +hql3 +" and " +hql4+" and "+ hql5;
		//查出是否有相同的数据在数据库中
		activityList=ad.queryByhql(hql,collegeName);
		
		if(activityList==null || activityList.size()==0) {
			String hh="from College where collegeName='"+collegeName+"'";
			addActivity.setColl(cd.queryByhql(hh).get(0));
			if(ad.addActivity(addActivity)>0) {
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
	 *  修改前查询活动信息
	 * @return
	 */
	public String searchObjectById() {
		m=ActionContext.getContext().getSession();
		System.out.println("要查询的用户名id:" + id);
		currentActivity=ad.getCollegeActivityInforById(id);
		if(currentActivity!=null) {
			activityTime=currentActivity.getTime();
			collegeName=currentActivity.getColl().getCollegeName();
			currentActivity.setTime(currentActivity.getTime().substring(0, 10));
			m.put("currentActivity", currentActivity);
			return "lookSuccess";
		}
		else {
			String message="获取数据失败!";
			m.put("error", message);
			return "lookError";
		}
		
	}
	
	/**
	 *  修改活动信息
	 * @return
	 */
	public void updateInfor() throws IOException{
		updateActivity.setTime(updateActivity.getTime().substring(0, 10));
		//查询collegeId
		String hh="from College where collegeName='"+collegeName+"'";
		updateActivity.setColl(cd.queryByhql(hh).get(0));
		//hql语句
		String message="";
		String hql1="coll = '"+updateActivity.getColl()+"'";
		String hql2="time = '"+updateActivity.getTime()+"'";
		String hql3="type = '"+updateActivity.getType()+"'";
		String hql4="content = '"+updateActivity.getContent()+"'";
		String hql5="title like '%"+updateActivity.getTitle()+"%'";
		String hql="from CollegeActivity where "+ hql1 +" and " +hql2  +" and " +hql3 +" and " +hql4+" and "+ hql5;
		System.out.println("zzzzz");
		activityList=ad.queryByhql(hql,collegeName);
		System.out.println("ccccc");
		if(activityList==null || activityList.size()==0) {
			if(ad.updateActivity(updateActivity)) {
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
}
