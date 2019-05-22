package com.international.actions.college;

import com.opensymphony.xwork2.ActionSupport;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.international.actions.common.ajaxAction;
import com.international.dao.AgreementDao;
import com.international.dao.CollegeDao;
import com.international.model.CollegeAgreement;
import com.opensymphony.xwork2.ActionContext;

public class CollegeAgreementAction extends ActionSupport{
	private List<CollegeAgreement> collegeAgreements;
	CollegeAgreement addAgreement;//添加协议
	CollegeAgreement currentAgreement;//显示协议
	CollegeAgreement updateAgreement;//修改协议
	List<CollegeAgreement> agreementList;
	AgreementDao agd;
	CollegeDao cd;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String search="";//搜索的字段
	String protocolTime="";//存储时间
	String collegeName="";//存储院校名称
	
	File upload;//上传文件
	String uploadFileName;//上传文件名
	String savePath;//上传文件的保存路径
	Map m;
	public CollegeAgreementAction() {
		
	}
	
	
	public List<CollegeAgreement> getAgreementList() {
		return agreementList;
	}


	public void setAgreementList(List<CollegeAgreement> agreementList) {
		this.agreementList = agreementList;
	}


	public CollegeDao getCd() {
		return cd;
	}


	public void setCd(CollegeDao cd) {
		this.cd = cd;
	}


	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getSavePath() {
		return savePath;
	}


	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}


	public String getProtocolTime() {
		return protocolTime;
	}


	public void setProtocolTime(String protocolTime) {
		this.protocolTime = protocolTime;
	}


	public String getCollegeName() {
		return collegeName;
	}


	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}


	public CollegeAgreement getAddAgreement() {
		return addAgreement;
	}


	public void setAddAgreement(CollegeAgreement addAgreement) {
		this.addAgreement = addAgreement;
	}


	public CollegeAgreement getCurrentAgreement() {
		return currentAgreement;
	}


	public void setCurrentAgreement(CollegeAgreement currentAgreement) {
		this.currentAgreement = currentAgreement;
	}


	public CollegeAgreement getUpdateAgreement() {
		return updateAgreement;
	}


	public void setUpdateAgreement(CollegeAgreement updateAgreement) {
		this.updateAgreement = updateAgreement;
	}


	public List<CollegeAgreement> getCollegeAgreements() {
		return collegeAgreements;
	}

	public void setCollegeAgreements(List<CollegeAgreement> collegeAgreements) {
		this.collegeAgreements = collegeAgreements;
	}

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

	public AgreementDao getAgd() {
		return agd;
	}

	public void setAgd(AgreementDao agd) {
		this.agd = agd;
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
		
		collegeAgreements=agd.queryAllAgreement(search);
		//查询到是空直接返回
		if(collegeAgreements==null)
			return SUCCESS;
		//计算总页数
		if(collegeAgreements.size()%pageSize==0){
			totalPage=collegeAgreements.size()/pageSize;
		}else{
			totalPage=collegeAgreements.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		collegeAgreements=agd.queryAgreement(search, pageNo, pageSize);
		for(int i=0;i<collegeAgreements.size();i++) {
			collegeAgreements.get(i).setTime(collegeAgreements.get(i).getTime().substring(0, 10));
		}
		//设置当前页
		currentPage=pageNo;
		m.put("collegeAgreements", collegeAgreements);
		return SUCCESS;
	}
	
	/**
	 *  删除活动信息
	 * @return
	 */
	public String deleteObject() {
		System.out.println("要删除的id:" + id);
		if(agd.deleteAgreement(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	/**
	 *  修改前查询协议信息
	 * @return
	 */
	public String searchObjectById() {
		m=ActionContext.getContext().getSession();
		System.out.println("要查询的用户名id:" + id);
		currentAgreement=agd.getCollegeAgreementInforById(id);
		if(currentAgreement!=null) {
			protocolTime=currentAgreement.getTime();
			collegeName=currentAgreement.getColl().getCollegeName();
			currentAgreement.setTime(currentAgreement.getTime().substring(0, 10));
			m.put("currentAgreement", currentAgreement);
			return "lookSuccess";
		}
		else {
			String message="获取数据失败!";
			m.put("error", message);
			return "lookError";
		}
		
	}
	
	/**
	 *  添加协议信息
	 * @return
	 */
	public void addObject() throws IOException{
		//获取需要上传文件的文件路径  
		System.out.println("jlm");
		String path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
		//判断文件是否上传，如果上传的话将会创建该目录 
		//String dir=
		
		System.out.println("ccccwj");
		File target= new File(path); // 定义目标文件对象
		addAgreement.setFileName(this.uploadFileName);
		
		try {
			System.out.println("qqqqwj");
			FileUtils.copyFile(upload, target);
			System.out.println("hhhhwj");
		}catch(Exception e){
			e.printStackTrace();
		}
		//删除临时文件
		upload.delete();
		String message="";
		//hql语句
		String hql2="time = '"+addAgreement.getTime()+"'";
		String hql3="type = '"+addAgreement.getType()+"'";
		String hql4="fileName = '"+addAgreement.getFileName()+"'";
		String hql5="title like '%"+addAgreement.getTitle()+"%'";
		String hql="from CollegeAgreement where "  +hql2  +" and " +hql3 +" and " +hql4+" and "+ hql5;
		//查出是否有相同的数据在数据库中
		System.out.println("zzzcwj");
		agreementList=agd.queryByhql(hql,collegeName);
		if(agreementList==null || agreementList.size()==0) {
			String hh="from College where collegeName='"+collegeName+"'";
			addAgreement.setColl(cd.queryByhql(hh).get(0));
			if(agd.addAgreement(addAgreement)>0) {
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
}
