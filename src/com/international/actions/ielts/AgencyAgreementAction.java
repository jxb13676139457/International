package com.international.actions.ielts;

import java.util.List;
import java.util.Map;

import com.international.dao.AgencyAgreementDao;
import com.international.dao.AgreementDao;
import com.international.model.AgencyAgreement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AgencyAgreementAction extends ActionSupport{
	private List<AgencyAgreement> agencyAgreements;
	Map m;
	AgencyAgreementDao aad;
	
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String search="";//搜索机构名称
	public AgencyAgreementAction() {
		
	}

	public List<AgencyAgreement> getAgencyAgreements() {
		return agencyAgreements;
	}

	public void setAgencyAgreements(List<AgencyAgreement> agencyAgreements) {
		this.agencyAgreements = agencyAgreements;
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

	public AgencyAgreementDao getAad() {
		return aad;
	}

	public void setAad(AgencyAgreementDao aad) {
		this.aad = aad;
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
	public String execute() throws Exception{
		m=ActionContext.getContext().getSession();
		//System.out.println(ServletActionContext.getServletContext().getRealPath(this.getSavePath()+"\\"));
		//查询机构的名字
		agencyAgreements=aad.queryAllAgreement(search);
		//查询到是空直接返回
		if(agencyAgreements==null)
			return SUCCESS;
		//计算总页数
		if(agencyAgreements.size()%pageSize==0){
			totalPage=agencyAgreements.size()/pageSize;
		}else{
			totalPage=agencyAgreements.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		agencyAgreements=aad.queryAgreement(search, pageNo, pageSize);
		for(int i=0;i<agencyAgreements.size();i++) {
			agencyAgreements.get(i).setTime(agencyAgreements.get(i).getTime().substring(0, 10));
		}
		//设置当前页
		currentPage=pageNo;
		m.put("agencyAgreements", agencyAgreements);
		return SUCCESS;
	}
}
