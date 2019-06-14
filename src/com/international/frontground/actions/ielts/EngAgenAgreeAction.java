package com.international.frontground.actions.ielts;
import java.util.List;
import java.util.Map;

import com.international.frontground.dao.PriEngAgreeDao;
import com.international.model.AgencyAgreement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class EngAgenAgreeAction extends ActionSupport{
	PriEngAgreeDao pad;
	List<AgencyAgreement> showAgreement;
	public EngAgenAgreeAction(){
		
	}
	
	public List<AgencyAgreement> getShowAgreement() {
		return showAgreement;
	}

	public void setShowAgreement(List<AgencyAgreement> showAgreement) {
		this.showAgreement = showAgreement;
	}

	public PriEngAgreeDao getPad() {
		return pad;
	}
	public void setPad(PriEngAgreeDao pad) {
		this.pad = pad;
	}
	
	public String execute() {
		Map session=ActionContext.getContext().getSession();
		String hql="from AgencyAgreement";
		showAgreement=pad.queryAgencyAgreement(hql);
		for(int i=0;i<showAgreement.size();i++) {
			showAgreement.get(i).setTime(showAgreement.get(i).getTime().substring(0, 10));
		}
		//System.out.println(showCollege.get(0).getCollegeName());
		session.put("priagreement", showAgreement);
		return SUCCESS;
	}
}
