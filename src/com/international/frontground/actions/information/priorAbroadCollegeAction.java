package com.international.frontground.actions.information;

import java.util.List;
import java.util.Map;

import com.international.dao.AgreementDao;
import com.international.dao.CollegeDao;
import com.international.model.CollegeAgreement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class priorAbroadCollegeAction extends ActionSupport {

	Map m;
	private List<CollegeAgreement> collegeAgreements;
	CollegeAgreement currentAgreement;//显示协议
	List<CollegeAgreement> agreementList;
	AgreementDao agd;
	CollegeDao cd;
	String search="";//搜索的字段
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Map getM() {
		return m;
	}
	public void setM(Map m) {
		this.m = m;
	}
	public List<CollegeAgreement> getCollegeAgreements() {
		return collegeAgreements;
	}
	public void setCollegeAgreements(List<CollegeAgreement> collegeAgreements) {
		this.collegeAgreements = collegeAgreements;
	}
	public CollegeAgreement getCurrentAgreement() {
		return currentAgreement;
	}
	public void setCurrentAgreement(CollegeAgreement currentAgreement) {
		this.currentAgreement = currentAgreement;
	}
	public List<CollegeAgreement> getAgreementList() {
		return agreementList;
	}
	public void setAgreementList(List<CollegeAgreement> agreementList) {
		this.agreementList = agreementList;
	}
	public AgreementDao getAgd() {
		return agd;
	}
	public void setAgd(AgreementDao agd) {
		this.agd = agd;
	}
	public CollegeDao getCd() {
		return cd;
	}
	public void setCd(CollegeDao cd) {
		this.cd = cd;
	}
	
	public String execute() {
		System.out.println("search = "+search);
		m=ActionContext.getContext().getSession();
		//System.out.println(ServletActionContext.getServletContext().getRealPath(this.getSavePath()+"\\"));
		
		collegeAgreements=agd.queryAllAgreement(search);
		m.put("collegeAgreements", collegeAgreements);
		System.out.println("collegeAgreements = "+collegeAgreements);

		return SUCCESS;
	}
}
