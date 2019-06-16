package com.international.frontground.actions.college;
import java.util.List;
import java.util.Map;

import com.international.frontground.dao.PriCollegeDao;
import com.international.model.College;
import com.international.model.CollegeActivity;
import com.international.model.CollegeAgreement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class PriorCollegeAction extends ActionSupport{
	PriCollegeDao pcd;
	List<College> showCollege;
	List<CollegeActivity> showCollegeActivity;
	List<CollegeAgreement> showCollegeAgreement;
	private String title;
	CollegeAgreement collegeAgreement;
	Map<String, Object> m;
	
	public PriorCollegeAction(){
		
	}
	
	
	
	public CollegeAgreement getCollegeAgreement() {
		return collegeAgreement;
	}



	public void setCollegeAgreement(CollegeAgreement collegeAgreement) {
		this.collegeAgreement = collegeAgreement;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public List<CollegeAgreement> getShowCollegeAgreement() {
		return showCollegeAgreement;
	}

	public void setShowCollegeAgreement(List<CollegeAgreement> showCollegeAgreement) {
		this.showCollegeAgreement = showCollegeAgreement;
	}

	public List<CollegeActivity> getShowCollegeActivity() {
		return showCollegeActivity;
	}

	public void setShowCollegeActivity(List<CollegeActivity> showCollegeActivity) {
		this.showCollegeActivity = showCollegeActivity;
	}

	public List<College> getShowCollege() {
		return showCollege;
	}

	public void setShowCollege(List<College> showCollege) {
		this.showCollege = showCollege;
	}

	public PriCollegeDao getPcd() {
		return pcd;
	}

	public void setPcd(PriCollegeDao pcd) {
		this.pcd = pcd;
	}

	public  String execute() {
		Map session=ActionContext.getContext().getSession();
		String hql="from College";
		showCollege=pcd.queryCollege(hql);
		for(int i=0;i<showCollege.size();i++) {
			showCollege.get(i).setStartTime(showCollege.get(i).getStartTime().substring(0, 10));
		}
		//System.out.println(showCollege.get(0).getCollegeName());
		session.put("pricollege", showCollege);
		return SUCCESS;
	}
	
	//查询国外院校活动
	public String exqueryActivity() {
		Map session=ActionContext.getContext().getSession();
		String hql="from CollegeActivity";
		showCollegeActivity=pcd.queryCollegeActivity(hql);
		for(int i=0;i<showCollegeActivity.size();i++) {
			showCollegeActivity.get(i).setTime(showCollegeActivity.get(i).getTime().substring(0, 10));
		}
		session.put("pricollActivity", showCollegeActivity);
		return "activity";
	}
	
	//查询国外院校协议
	public String exqueryAgreement() {
		Map session=ActionContext.getContext().getSession();
		String hql="from CollegeAgreement";
		showCollegeAgreement=pcd.queryCollegeAgreement(hql);
		for(int i=0;i<showCollegeAgreement.size();i++) {
			showCollegeAgreement.get(i).setTime(showCollegeAgreement.get(i).getTime().substring(0, 10));
		}
		session.put("pricollAgreement", showCollegeAgreement);
		return "agreement";
	}
	
	public String exqueryAgreement1() {
		Map session=ActionContext.getContext().getSession();
		String hql="from CollegeAgreement";
		showCollegeAgreement=pcd.queryCollegeAgreement(hql);
		for(int i=0;i<showCollegeAgreement.size();i++) {
			showCollegeAgreement.get(i).setTime(showCollegeAgreement.get(i).getTime().substring(0, 10));
		}
		session.put("showCollegeAgreement", showCollegeAgreement);
		System.out.println("showCollegeAgreement = "+showCollegeAgreement);
		return "agreement1";
	}
	
	public String getInformationByTitle() {
		m=ActionContext.getContext().getSession();
		System.out.println("title = "+title);
		showCollegeAgreement=pcd.getInformationByTitle(title);
		collegeAgreement=showCollegeAgreement.get(0);
		collegeAgreement.setTime(collegeAgreement.getTime().substring(0, 10));
		System.out.println("collegeAgreement = "+collegeAgreement);
		m.put("collegeAgreement", collegeAgreement);
		return "successSearch";
	}
}
