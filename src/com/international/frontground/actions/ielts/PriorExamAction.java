package com.international.frontground.actions.ielts;
import java.util.List;
import java.util.Map;

import com.international.frontground.dao.PriExamDao;
import com.international.model.AttendTraining;
import com.international.model.InternationalStudent;
import com.international.model.Score;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class PriorExamAction extends ActionSupport{
	PriExamDao ped;
	List<Score> showSimExam;
	List<AttendTraining> showTraining;
	public PriorExamAction(){
		
	}
	
	public List<AttendTraining> getShowTraining() {
		return showTraining;
	}

	public void setShowTraining(List<AttendTraining> showTraining) {
		this.showTraining = showTraining;
	}

	public List<Score> getShowSimExam() {
		return showSimExam;
	}

	public void setShowSimExam(List<Score> showSimExam) {
		this.showSimExam = showSimExam;
	}

	public PriExamDao getPed() {
		return ped;
	}

	public void setPed(PriExamDao ped) {
		this.ped = ped;
	}
	//教师查询模拟考试
	public String otherExamSearch() {
		Map session=ActionContext.getContext().getSession();
		String hql="from Score where examId in (select examId from Exam where examType='模拟考试')";
		System.out.println(hql);
		showSimExam=ped.querySimScore(hql);
		for(int i=0;i<showSimExam.size();i++) {
			showSimExam.get(i).getExm().setTime(showSimExam.get(i).getExm().getTime().substring(0, 16));
		}
		session.put("prisimscore", showSimExam);
		return "othersearch";
	}
	
	//教师查询正式考试
	public String otherScoreSearch() {
		Map session=ActionContext.getContext().getSession();
		String hql="from Score where examId in (select examId from Exam where examType='正式考试')";
		System.out.println(hql);
		showSimExam=ped.querySimScore(hql);
		for(int i=0;i<showSimExam.size();i++) {
			showSimExam.get(i).getExm().setTime(showSimExam.get(i).getExm().getTime().substring(0, 16));
		}
		session.put("priforscore", showSimExam);
		return "otherfor";
	}
	
	//学生查询雅思信息
	public String studentAgencySearch() {
		Map session=ActionContext.getContext().getSession();
		InternationalStudent stname=(InternationalStudent)session.get("loginUser");
		System.out.println(stname);
		//存正式考试
		String hql1="from Score where examId in (select examId from Exam where examType='正式考试') and studentId='"+stname.getStudentId()+"'";
		showSimExam=ped.querySimScore(hql1);
		for(int i=0;i<showSimExam.size();i++) {
			showSimExam.get(i).getExm().setTime(showSimExam.get(i).getExm().getTime().substring(0, 16));
		}
		session.put("priforscore", showSimExam);
		//存模拟考试
		String hql2="from Score where examId in (select examId from Exam where examType='模拟考试') and studentId='"+stname.getStudentId()+"'";
		showSimExam=ped.querySimScore(hql2);
		for(int i=0;i<showSimExam.size();i++) {
			showSimExam.get(i).getExm().setTime(showSimExam.get(i).getExm().getTime().substring(0, 16));
		}
		session.put("priexmscore", showSimExam);
		//存雅思培训
		String hql3="from AttendTraining where studentId='"+stname.getStudentId()+"'";
		showTraining=ped.queryTraining(hql3);
		for(int i=0;i<showTraining.size();i++) {
			showTraining.get(i).getTraining().setStartTime(showTraining.get(i).getTraining().getStartTime().substring(0, 10));
			showTraining.get(i).getTraining().setEndTime(showTraining.get(i).getTraining().getEndTime().substring(0, 10));
		}
		session.put("pritrain", showTraining);
		return "studentfor";
	}
}
