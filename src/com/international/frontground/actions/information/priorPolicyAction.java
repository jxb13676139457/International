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
		m.put("policyList", policyList);
		System.out.println("policyList = "+policyList);
		return SUCCESS;
	}
	
}
