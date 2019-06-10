package com.international.actions.ielts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.ScoreDao;
import com.international.model.Agency;
import com.international.model.Exam;
import com.international.model.InternationalStudent;
import com.international.model.Score;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ScoreAction extends ActionSupport{
	private List<Score> ListScore;
	private String studentId;//前台传来的id
	Score addScore;//添加成绩
	Score currScore;//修改前查询成绩
	Score updateScore;//修改成绩
	List<Score> CheckList;//检查是否有相同数据在数据库
	ScoreDao scd;
	Map m;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String search;		//搜索学号
	String agencyName;	//存储机构
	String startTime;	//存储考试时间
	String location;	//存储位置
	String examType;	//存储考试类型
	public ScoreAction() {
		
	}

	
	public Score getUpdateScore() {
		return updateScore;
	}


	public void setUpdateScore(Score updateScore) {
		this.updateScore = updateScore;
	}


	public Score getCurrScore() {
		return currScore;
	}


	public void setCurrScore(Score currScore) {
		this.currScore = currScore;
	}


	public List<Score> getCheckList() {
		return CheckList;
	}


	public void setCheckList(List<Score> checkList) {
		CheckList = checkList;
	}


	public String getExamType() {
		return examType;
	}


	public void setExamType(String examType) {
		this.examType = examType;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Score getAddScore() {
		return addScore;
	}


	public void setAddScore(Score addScore) {
		this.addScore = addScore;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Map getM() {
		return m;
	}

	public void setM(Map m) {
		this.m = m;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public ScoreDao getScd() {
		return scd;
	}

	public void setScd(ScoreDao scd) {
		this.scd = scd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Score> getListScore() {
		return ListScore;
	}

	public void setListScore(List<Score> listScore) {
		ListScore = listScore;
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
		//System.out.println(ServletActionContext.getServletContext().getRealPath(this.getSavePath()+"\\"));
		//查询学号
		ListScore=scd.queryAllScore(search);
		//查询到是空直接返回
		if(ListScore==null)
			return SUCCESS;
		//计算总页数
		if(ListScore.size()%pageSize==0){
			totalPage=ListScore.size()/pageSize;
		}else{
			totalPage=ListScore.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		ListScore=scd.queryScore(search, pageNo, pageSize);
		for(int i=0;i<ListScore.size();i++) {
			ListScore.get(i).getExm().setTime(ListScore.get(i).getExm().getTime().substring(0, 10));
		}
		//设置当前页
		currentPage=pageNo;
		m.put("ListScore", ListScore);
		return SUCCESS;
	}
	
	/**
	 *  删除成绩信息
	 * @return
	 */
	public String deleteObject() {
		System.out.println("要删除的id:" + id);
		if(scd.deleteScore(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	/**
	 *  添加成绩信息
	 * @return
	 */
	public void addObject() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String message="";

		
		//addScore.setTime(addScore.getTime().toString().substring(0,10));
		//ajax多出一个逗号，裁剪
		agencyName=agencyName.substring(0, agencyName.length()-2);
		location=location.substring(0, location.length()-2);
		startTime=startTime.substring(0, 16);
		examType=examType.substring(0, 4);
		System.out.println(location+","+examType+","+startTime+","+agencyName);
		//查询机构
		String hh="from Agency where agencyName='"+agencyName+"'";
		String hq="from Exam where agencyId='"+scd.queryByhqlA(hh).get(0).getAgencyId()+"' and convert(varchar,time,120) like '%"+startTime+"%' and location='"+location+"' and examType='"+examType+"'";
		addScore.setExm(scd.queryByhqlE(hq).get(0));
		addScore.setInterStu(scd.queryByStuId(studentId));
		String hql1="interStu = '"+addScore.getInterStu().getStudentId()+"'";
		String hql2="listening = '"+addScore.getListening()+"'";
		String hql3="oral = '"+addScore.getOral()+"'";
		String hql4="reading = '"+addScore.getReading()+"'";
		String hql5="writing like '%"+addScore.getWriting()+"%'";
		String hql6="score like '%"+addScore.getScore()+"%'";
		String hql7="examId = '"+addScore.getExm().getExamId()+"'";
		String hql="from Score where "  +hql2  +" and " +hql3 +" and " +hql4+" and "+ hql5+" and "+ hql6+" and "+ hql7;
		//查出是否有相同的数据在数据库中
		CheckList=scd.queryByhqlS(hql);
		
		if(CheckList==null || CheckList.size()==0) {
			if(scd.addScore(addScore)>0) {
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
	 *  修改前查询成绩信息
	 * @return
	 */
	public String searchObjectById() {
		m=ActionContext.getContext().getSession();
		System.out.println("要查询的用户名id:" + id);
		currScore=scd.getScoreInforById(id);
		if(currScore!=null) {
			startTime=currScore.getExm().getTime();
			agencyName=currScore.getExm().getAgen().getAgencyName();
			currScore.getExm().setTime(currScore.getExm().getTime().substring(0, 16));
			m.put("currScore", currScore);
			return "lookSuccess";
		}
		else {
			String message="获取数据失败!";
			m.put("error", message);
			return "lookError";
		}
		
	}
	
	/**
	 *  修改成绩信息
	 * @return
	 */
	public void updateInfor() throws IOException{
		//ajax多出一个逗号，裁剪
		//agencyName=agencyName.substring(2, agencyName.length()-2);
		//location=location.substring(2, location.length()-1);
		//startTime=startTime.substring(2, 16);
		//examType=examType.substring(2, 4);
		System.out.println(location+"|"+examType+"|"+startTime+"|"+agencyName);
		//查询机构
		String hh="from Agency where agencyName='"+agencyName+"'";
		String hq="from Exam where agencyId='"+scd.queryByhqlA(hh).get(0).getAgencyId()+"' and convert(varchar,time,120) like '%"+startTime+"%' and location='"+location+"' and examType='"+examType+"'";
		updateScore.setExm(scd.queryByhqlE(hq).get(0));
		updateScore.setInterStu(scd.queryByStuId(studentId));
			
		String message="";
		//hql语句
		String hql1="interStu = '"+updateScore.getInterStu().getStudentId()+"'";
		String hql2="listening = '"+updateScore.getListening()+"'";
		String hql3="oral = '"+updateScore.getOral()+"'";
		String hql4="reading = '"+updateScore.getReading()+"'";
		String hql5="writing like '%"+updateScore.getWriting()+"%'";
		String hql6="score like '%"+updateScore.getScore()+"%'";
		String hql7="examId = '"+updateScore.getExm().getExamId()+"'";
		String hql="from Score where "  +hql2  +" and " +hql3 +" and " +hql4+" and "+ hql5+" and "+ hql6+" and "+ hql7;
		//查出是否有相同的数据在数据库中
		CheckList=scd.queryByhqlS(hql);
		if(CheckList==null || CheckList.size()==0) {
			if(scd.updateScore(updateScore)) {
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
	
	//获取学生的信息通过学号	
    public void getStudentInformation() throws IOException{
    	//Map  session =ActionContext.getContext().getSession();
    	InternationalStudent student = new InternationalStudent();
    	System.out.println("获取到的学号："+ studentId);
    	student = scd.queryByStuId(studentId);
    	if(student!=null){
    		student.setStudentId(studentId);
    		student.setClasses(student.getClasses());
    		System.out.println("自动获取的班级对象班级："+student.getClasses().getClassName());
    	}
    	//System.out.println("获取到的学号2："+ studentId);
    	String autoStudentName = student.getStudentName();
    	String autoClassName = student.getClasses().getClassName();
    	String autoMajor = student.getClasses().getMajor();
    	String autoGrade = student.getClasses().getGrade();
    	String auto = autoStudentName+","+autoClassName+","+autoGrade+","+autoMajor;
    	//System.out.println(auto);
    	ajaxAction.toJson(ServletActionContext.getResponse(),auto);
    }
    
  //获取考试时间
    public void getStartTimeInformation() throws IOException{
    	System.out.println("获取到的机构名："+ agencyName);
    	List<Agency> interAgency=new ArrayList();
    	List<Exam> searchExam=new ArrayList();
    	String hx="from Agency where agencyName='"+agencyName+"'";
    	interAgency=scd.queryByhqlA(hx);
    	if(interAgency!=null) {
    		String hd="from Exam where agencyId ='"+interAgency.get(0).getAgencyId()+"'";
    		searchExam=scd.queryByhqlE(hd);
    	}
    	else {
    		String hq="from Exam where agencyId is null";
    		searchExam=scd.queryByhqlE(hq);
    	}
    	ajaxAction.toJson(ServletActionContext.getResponse(),searchExam);
    		
    }
    
    //获取考试地点
    public void getLocation() throws IOException{
    	System.out.println("获取到的机构名："+ agencyName);
    	startTime=startTime.substring(0, 16);
    	System.out.println("获取到的时间："+ startTime);
    	List<Agency> interAgency=new ArrayList();
    	List<Exam> searchExam=new ArrayList();
    	String hx="from Agency where agencyName='"+agencyName+"'";
    	interAgency=scd.queryByhqlA(hx);
    	if(interAgency!=null) {
    		String hd="from Exam where agencyId ='"+interAgency.get(0).getAgencyId()+"' and convert(varchar,time,120) like '%"+startTime+"%'";
    		searchExam=scd.queryByhqlE(hd);
    	}
    	else {
    		String hq="from Exam where agencyId is null and convert(varchar,time,120) like '%"+startTime+"%'";
    		searchExam=scd.queryByhqlE(hq);
    	}
    	
    	ajaxAction.toJson(ServletActionContext.getResponse(),searchExam);
    		
    }
}
