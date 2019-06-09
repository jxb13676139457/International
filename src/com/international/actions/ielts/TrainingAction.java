package com.international.actions.ielts;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.AgencyDao;
import com.international.dao.TrainingDao;
import com.international.model.Agency;
import com.international.model.Training;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TrainingAction extends ActionSupport {

	private String agencyName;
	private TrainingDao td;
	private AgencyDao ad;
	private List<Training> trainingList;
	private Training training;
	Map m;
	private String searchStartTime="";
	private String searchEndTime="";
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	
	
	public AgencyDao getAd() {
		return ad;
	}
	public void setAd(AgencyDao ad) {
		this.ad = ad;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public TrainingDao getTd() {
		return td;
	}
	public void setTd(TrainingDao td) {
		this.td = td;
	}
	public List<Training> getTrainingList() {
		return trainingList;
	}
	public void setTrainingList(List<Training> trainingList) {
		this.trainingList = trainingList;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public Map getM() {
		return m;
	}
	public void setM(Map m) {
		this.m = m;
	}
	public String getSearchStartTime() {
		return searchStartTime;
	}
	public void setSearchStartTime(String searchStartTime) {
		this.searchStartTime = searchStartTime;
	}
	public String getSearchEndTime() {
		return searchEndTime;
	}
	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
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
	
	
	public String execute() {
		System.out.println("td = "+td);
		System.out.println("execute默认方法被调用");
		
		m=ActionContext.getContext().getSession();
		m.put("searchStartTime", searchStartTime);
		m.put("searchEndTime", searchEndTime);
		trainingList=td.queryAllTraining();
		System.out.println("searchStartTime = "+searchStartTime);
		System.out.println("searchEndTime = "+searchEndTime);
		//计算总页数
		System.out.println("trainingList = "+trainingList);
		if(trainingList!=null) {
			if(trainingList.size()%pageSize==0){
				totalPage=trainingList.size()/pageSize;
			}else{
				totalPage=trainingList.size()/pageSize+1;
			}
			if(pageNo<=0){
				pageNo=1;
			}else if(pageNo>totalPage){
				pageNo=totalPage;
			}
		
			//根据当前页查询要在该页上显示的数据
			trainingList=td.queryTraining(searchStartTime,searchEndTime,pageNo,pageSize);
			for(int i=0;i<trainingList.size();i++) {
				trainingList.get(i).setStartTime(trainingList.get(i).getStartTime().substring(0, 10));
				trainingList.get(i).setEndTime(trainingList.get(i).getEndTime().substring(0, 10));
			}
			//System.out.println("id = "+classes.get(0).getClassId());
			//设置当前页
			currentPage=pageNo;
		}
		m.put("trainingList", trainingList);
		System.out.println("trainingList = "+trainingList);
		
		return SUCCESS;
	}
	
	
	public String searchTraining(){
		System.out.println("searchTraining默认方法被调用");
		System.out.println("searchStartTime = "+searchStartTime);
		System.out.println("searchEndTime = "+searchEndTime);
		if(!searchStartTime.equals("") && searchStartTime!=null) {
			searchStartTime = searchStartTime.substring(0, 10);
			
		}
		if(!searchEndTime.equals("") && searchEndTime!=null) {
			searchEndTime = searchEndTime.substring(0, 10);
		}
		System.out.println("searchStartTime = "+searchStartTime);
		System.out.println("searchEndTime = "+searchEndTime);
		
		m=ActionContext.getContext().getSession();
		m.put("searchStartTime", searchStartTime);
		m.put("searchEndTime", searchEndTime);
		trainingList=td.queryAllTraining();
		//计算总页数
		System.out.println("trainingList = "+trainingList);
		if(trainingList.size()%pageSize==0){
			totalPage=trainingList.size()/pageSize;
		}else{
			totalPage=trainingList.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		trainingList=td.queryTraining(searchStartTime,searchEndTime,pageNo,pageSize);
		//System.out.println("id = "+classes.get(0).getClassId());
		//设置当前页
		currentPage=pageNo;
		m.put("trainingList", trainingList);
		System.out.println("trainingList = "+trainingList);
		
		return SUCCESS;
	}
	
	
	public void addTraining() throws IOException {
		System.out.println("ad = "+ad);
		String message="";
		training.setStartTime(training.getStartTime().substring(0,10));
		training.setEndTime(training.getEndTime().substring(0,10));
		agencyName=agencyName.substring(0,agencyName.length()-2);
		System.out.println("agencyName = "+agencyName);
		System.out.println("addTraining方法被调用了");
		
		
		System.out.println("training对象CourseHours = "+training.getCourseHours());
		System.out.println("training对象StartTime() = "+training.getStartTime());
		System.out.println("training对象endTime = "+training.getEndTime());
		System.out.println("training对象courseFee = "+training.getCourseFee());
		
		String hql1 = "courseHours = '"+training.getCourseHours()+"'";
		String hql2 = "startTime = '"+training.getStartTime()+"'";
		String hql3 = "endTime = '"+training.getEndTime()+"'";
		String hql4 = "courseFee = '"+training.getCourseFee()+"'";
		String hql = "from Training where "+hql1+" and "+hql2+" and "+hql3+" and "+hql4;
		
		trainingList = td.queryByHql(hql, agencyName);
		
		if(trainingList==null || trainingList.size()==0) {
			System.out.println("trainingList为空");
			training.setAgencies(ad.queryAgenciesByHql(agencyName).get(0));
			if(td.addTraining(training)>0) {
				
				System.out.println("添加成功");
				message="添加成功";
			}
			else {
				System.out.println("添加失败");
				message="添加失败";
			}
		}
		else {
			message="已存在该条信息";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
	
	
	/**
	 *  删除通知信息
	 */
	public String deleteTraingingInfo() {

		System.out.println("要删除的id:" + id);
		if(td.deleteTraining(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	
	public String searchObjectById() {
		System.out.println("id = "+id);
		
		training = td.getTrainingInforById(id);
		if(training!=null) {
			System.out.println("training对象信息 = "+training);
			System.out.println("training对象ID = "+training.getTrainingId());
			System.out.println("training对象CourseHours = "+training.getCourseHours());
			System.out.println("training对象StartTime = "+training.getStartTime());
			System.out.println("training对象EndTime = "+training.getEndTime());
			System.out.println("training对象CourseFee = "+training.getCourseFee());
			
			return "lookSuccess";
		}
		return "lookError";
	}
	
	
	
	public String updateTrainingInfor(){
		System.out.println("要更新的Id = "+id);
		System.out.println("training对象Id = "+training.getTrainingId());
		System.out.println("training对象AgencyName = "+training.getAgencies().getAgencyName());
		System.out.println("training对象CourseHours = "+training.getCourseHours());
		System.out.println("training对象StartTime() = "+training.getStartTime());
		System.out.println("training对象endTime = "+training.getEndTime());
		System.out.println("training对象courseFee = "+training.getCourseFee());
		
		training.setStartTime(training.getStartTime().substring(0,10));
		training.setEndTime(training.getEndTime().substring(0, 10));
		//查询collegeId
		String hh="from Training where agencyName='"+agencyName+"'";
		training.setAgencies(ad.queryAgenciesByHql(agencyName).get(0));
		System.out.println("training.setAgencies - > AgencyName = "+training.getAgencies().getAgencyName());
		//hql语句
		String message="";
		String hql1 = "courseHours = '"+training.getCourseHours()+"'";
		String hql2 = "startTime = '"+training.getStartTime()+"'";
		String hql3 = "endTime = '"+training.getEndTime()+"'";
		String hql4 = "courseFee = '"+training.getCourseFee()+"'";
		String hql = "from Training where "+hql1+" and "+hql2+" and "+hql3+" and "+hql4;
		
		trainingList = td.queryByHql(hql, agencyName);
		if(trainingList==null || trainingList.size()==0) {
			if(td.updateTraining(id,training)) {
				return "updateSuccess";
			}
			else {
				return "updateError";
			}
		}
		else {
			return "updateError";
		}
	}
}
