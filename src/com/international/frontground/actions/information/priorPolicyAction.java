package com.international.frontground.actions.information;

import java.util.List;
import java.util.Map;

import com.international.dao.PolicyDao;
import com.international.model.Policy;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class priorPolicyAction extends ActionSupport {

	private PolicyDao pd;
	private List<Policy> policyList;
	private Policy policy;
	private Map m;
	private String title;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public PolicyDao getPd() {
		return pd;
	}
	public void setPd(PolicyDao pd) {
		this.pd = pd;
	}
	public List<Policy> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<Policy> policyList) {
		this.policyList = policyList;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public Map getM() {
		return m;
	}
	public void setM(Map m) {
		this.m = m;
	}
	
	
	public String execute() {
		System.out.println("pd = "+pd);
		System.out.println("execute默认方法被调用");
		m=ActionContext.getContext().getSession();
		policyList=pd.queryAllPolicy();
		for(int i=0;i<policyList.size();i++) {
			policyList.get(i).setTime(policyList.get(i).getTime().substring(0, 10));
		}
		m.put("policyList", policyList);
		System.out.println("policyList = "+policyList);
		return SUCCESS;
	}
	
	
	public String getInformationByTitle() {
		m=ActionContext.getContext().getSession();
		System.out.println("title = "+title);
		policyList=pd.getInformationByTitle(title);
		policy=policyList.get(0);
		policy.setTime(policy.getTime().substring(0, 10));
		System.out.println("policy = "+policy);
		m.put("policy", policy);
		return "successSearch";
	}
}
