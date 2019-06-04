package com.international.actions.ielts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.AgencyDao;
import com.international.model.Agency;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AgencyAction extends ActionSupport {

	private AgencyDao ad;
	private List<Agency> agencyList;
	private Agency agency;
	Map m;
	private String searchAgency="";
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
	public List<Agency> getAgencyList() {
		return agencyList;
	}
	public void setAgencyList(List<Agency> agencyList) {
		this.agencyList = agencyList;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	public Map getM() {
		return m;
	}
	public void setM(Map m) {
		this.m = m;
	}
	public String getSearchAgency() {
		return searchAgency;
	}
	public void setSearchAgency(String searchAgency) {
		this.searchAgency = searchAgency;
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
		System.out.println("ad = "+ad);
		System.out.println("execute默认方法被调用");
		
		m=ActionContext.getContext().getSession();
		m.put("searchAgency", searchAgency);
		agencyList=ad.queryAllAgency();
		System.out.println("searchAgency = "+searchAgency);
		//计算总页数
		System.out.println("newsList = "+agencyList);
		if(agencyList.size()%pageSize==0){
			totalPage=agencyList.size()/pageSize;
		}else{
			totalPage=agencyList.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		agencyList=ad.queryAgency(searchAgency,pageNo,pageSize);
		for(int i=0;i<agencyList.size();i++) {
			agencyList.get(i).setTime(agencyList.get(i).getTime().substring(0, 10));
		}
		//System.out.println("id = "+classes.get(0).getClassId());
		//设置当前页
		currentPage=pageNo;
		m.put("agencyList", agencyList);
		System.out.println("agencyList = "+agencyList);
	
		
		return SUCCESS;
	}
	
	
	public String searchAgency(){
		System.out.println("searchAgency方法被调用");
		m=ActionContext.getContext().getSession();
		agencyList=ad.queryAllAgency();
		System.out.println("searchAgency = "+searchAgency);
		//计算总页数
		if(agencyList.size()%pageSize==0){
			totalPage=agencyList.size()/pageSize;
		}else{
			totalPage=agencyList.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		System.out.println("searchAgency = "+searchAgency);
		agencyList=ad.queryAgency(searchAgency,pageNo,pageSize);
		
		//设置当前页
		currentPage=pageNo;
		m.put("agencyList", agencyList);
		return SUCCESS;
	}
	
	
	/*
	 * 增加信息
	 */
	public String addAgency() {
		System.out.println("addAgency方法被调用了");
		System.out.println("agency对象agencyName = "+agency.getAgencyName());
		System.out.println("agency对象Time = "+agency.getTime());
		System.out.println("agency对象cotactPerson = "+agency.getCotactPerson());
		System.out.println("agency对象position = "+agency.getPosition());
		System.out.println("agency对象phone = "+agency.getPhone());
		System.out.println("agency对象email = "+agency.getEmail());
		agency.setTime((agency.getTime().substring(0, 10)));
		if(ad.insertAgency(agency))
			return "addSuccess";
		return "addError";
	}
	
	
	
	/**
	 *  删除通知信息
	 */
	public String deleteAgency() {

		System.out.println("要删除的id:" + id);
		if(ad.deleteAgency(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	
	
	public String searchObjectById() {
		System.out.println("id = "+id);
		
		agency = ad.getAgencyInforById(id);
		if(agency!=null) {
			System.out.println("agency对象信息 = "+agency);
			System.out.println("agency对象ID = "+agency.getAgencyId());
			System.out.println("agency对象agencyName = "+agency.getAgencyName());
			System.out.println("agency对象Time = "+agency.getTime());
			System.out.println("agency对象contactPerson = "+agency.getCotactPerson());
			System.out.println("agency对象position = "+agency.getPosition());
			System.out.println("agency对象phone = "+agency.getPhone());
			System.out.println("agency对象email = "+agency.getEmail());
			
			return "lookSuccess";
		}
		return "lookError";
	}
	
	
	
	public  String updateAgencyInfor(){
		System.out.println("updatePolicyInfor被调用");
		Map  session =ActionContext.getContext().getSession();	
		System.out.println("agency对象信息 = "+agency);
		System.out.println("agency对象ID = "+agency.getAgencyId());
		System.out.println("agency对象agencyName = "+agency.getAgencyName());
		System.out.println("agency对象Time = "+agency.getTime());
		System.out.println("agency对象contactPerson = "+agency.getCotactPerson());
		System.out.println("agency对象position = "+agency.getPosition());
		System.out.println("agency对象phone = "+agency.getPhone());
		System.out.println("agency对象email = "+agency.getEmail());
		agency.setTime((agency.getTime().substring(0, 10)));
		System.out.println("要更新的id:" + id);
		if(ad.updateAgencyByID(id, agency))
			return "updateSuccess";
		else
			return "updateError";

    }

	/**
	 *  获取国际院校信息到select列表中
	 * @return
	 */
	public void getAgencyInformation() throws IOException{
		List<Agency> agencyList = new ArrayList<Agency>();
		agencyList = ad.queryAllAgency();
		ajaxAction.toJson(ServletActionContext.getResponse(),agencyList);
	}
}
