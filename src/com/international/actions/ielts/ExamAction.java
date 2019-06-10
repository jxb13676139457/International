package com.international.actions.ielts;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.ExamDao;
import com.international.model.Exam;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExamAction extends ActionSupport{
	private List<Exam> ListExam;
	Exam addExam;//用于添加信息
	Exam currExam;//用于显示修改信息
	Exam updateExam;//用于修改信息
	ExamDao exd;
	Map m;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String ExamTime="";		//获取时间
	String agencyName="";//添加时获取机构的名字
	List<Exam> CheckList;//查找是否在数据库中已存在数据
	public ExamAction(){
		
	}
	
	public Exam getUpdateExam() {
		return updateExam;
	}

	public void setUpdateExam(Exam updateExam) {
		this.updateExam = updateExam;
	}

	public Exam getCurrExam() {
		return currExam;
	}

	public void setCurrExam(Exam currExam) {
		this.currExam = currExam;
	}

	public List<Exam> getCheckList() {
		return CheckList;
	}

	public void setCheckList(List<Exam> checkList) {
		CheckList = checkList;
	}

	public Exam getAddExam() {
		return addExam;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public void setAddExam(Exam addExam) {
		this.addExam = addExam;
	}

	public List<Exam> getListExam() {
		return ListExam;
	}

	public void setListExam(List<Exam> listExam) {
		ListExam = listExam;
	}

	public String getExamTime() {
		return ExamTime;
	}

	public void setExamTime(String examTime) {
		ExamTime = examTime;
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

	public ExamDao getExd() {
		return exd;
	}

	public void setExd(ExamDao exd) {
		this.exd = exd;
	}
	
	public String execute() throws Exception{
		m=ActionContext.getContext().getSession();
		if(!ExamTime.equals(""))
			ExamTime=ExamTime.substring(0, 10);
		ListExam=exd.queryAllExam(ExamTime);
		//查询到是空直接返回
		if(ListExam==null) {
			//totalPage=0;
			//currentPage=0;
			return SUCCESS;
		}
			
		//计算总页数
		if(ListExam.size()%pageSize==0){
			totalPage=ListExam.size()/pageSize;
		}else{
			totalPage=ListExam.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		ListExam=exd.queryExam(ExamTime, pageNo, pageSize);
		for(int i=0;i<ListExam.size();i++) {
			ListExam.get(i).setTime(ListExam.get(i).getTime().substring(0, 10));
		}
		//设置当前页
		currentPage=pageNo;
		m.put("ListExam", ListExam);
		return SUCCESS;
	}
	
	/**
	 *  删除活动信息
	 * @return
	 */
	public String deleteObject() {
		System.out.println("要删除的id:" + id);
		if(exd.deleteExam(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	/**
	 *  添加考试信息
	 * @return
	 */
	public void addObject() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String message="";
		System.out.println(agencyName);
		
		addExam.setTime(addExam.getTime().toString().substring(0,10));
		//ajax多出一个逗号，裁剪
		agencyName=agencyName.substring(0, agencyName.length()-2);
		//查询机构
		String hh="from Agency where agencyName='"+agencyName+"'";
		
			
		addExam.setAgen(exd.queryByhqlA(hh).get(0));
		
		String hql1="agencyId = '"+addExam.getAgen().getAgencyId()+"'";
		String hql2="time = '"+addExam.getTime()+"'";
		String hql3="examType = '"+addExam.getExamType()+"'";
		String hql4="location like '%"+addExam.getLocation()+"%'";
		String hql="from Exam where "  +hql1+ " and " +hql2  +" and " +hql3 +" and " +hql4;
		//查出是否有相同的数据在数据库中
		CheckList=exd.queryByhqlE(hql);
		
		if(CheckList==null || CheckList.size()==0) {
			if(exd.addExam(addExam)>0) {
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
	 *  修改前查询考试信息
	 * @return
	 */
	public String searchObjectById() {
		m=ActionContext.getContext().getSession();
		System.out.println("要查询的用户名id:" + id);
		currExam=exd.getExamInforById(id);
		if(currExam!=null) {
			ExamTime=currExam.getTime();
			agencyName=currExam.getAgen().getAgencyName();
			currExam.setTime(currExam.getTime().substring(0, 10));
			m.put("currExam", currExam);
			return "lookSuccess";
		}
		else {
			String message="获取数据失败!";
			m.put("error", message);
			return "lookError";
		}
		
	}
	
	/**
	 *  修改考试信息
	 * @return
	 */
	public void updateInfor() throws IOException{
		updateExam.setTime(updateExam.getTime().substring(0, 10));
		//agencyName=agencyName.substring(0, agencyName.length()-2);
		//查询机构
		String hh="from Agency where agencyName='"+agencyName+"'";
		updateExam.setAgen(exd.queryByhqlA(hh).get(0));
		//hql语句
		String message="";
		String hql1="agencyId = '"+updateExam.getAgen().getAgencyId()+"'";
		String hql2="time = '"+updateExam.getTime()+"'";
		String hql3="examType = '"+updateExam.getExamType()+"'";
		String hql4="location like '%"+updateExam.getLocation()+"%'";
		String hql="from Exam where "+ hql1 +" and " +hql2  +" and " +hql3 +" and " +hql4;
		CheckList=exd.queryByhqlE(hql);
		if(CheckList==null || CheckList.size()==0) {
			if(exd.updateExam(updateExam)) {
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
