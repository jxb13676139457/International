package com.international.actions.ielts;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.AgencyAgreementDao;
import com.international.dao.AgreementDao;
import com.international.model.AgencyAgreement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AgencyAgreementAction extends ActionSupport{
	private List<AgencyAgreement> agencyAgreements;
	Map m;
	AgencyAgreementDao aad;
	AgencyAgreement currAgreement;//显示要修改的雅思协议
	AgencyAgreement updateAgreement;//修改雅思协议
	AgencyAgreement addAgreement;//添加雅思协议
	List<AgencyAgreement> agreementList;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String search="";//搜索机构名称
	String agencyName="";//存储机构名字
	String agencyTime="";//存储时间
	
	File upload;//上传文件
	String uploadFileName;//上传文件名
	String savePath;//上传文件的保存路径
	String uploadContentType;//封装上传文件的类型
	
	public AgencyAgreementAction() {
		
	}

	public AgencyAgreement getAddAgreement() {
		return addAgreement;
	}

	public void setAddAgreement(AgencyAgreement addAgreement) {
		this.addAgreement = addAgreement;
	}

	public List<AgencyAgreement> getAgreementList() {
		return agreementList;
	}

	public void setAgreementList(List<AgencyAgreement> agreementList) {
		this.agreementList = agreementList;
	}

	public AgencyAgreement getCurrAgreement() {
		return currAgreement;
	}

	public void setCurrAgreement(AgencyAgreement currAgreement) {
		this.currAgreement = currAgreement;
	}

	public AgencyAgreement getUpdateAgreement() {
		return updateAgreement;
	}

	public void setUpdateAgreement(AgencyAgreement updateAgreement) {
		this.updateAgreement = updateAgreement;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyTime() {
		return agencyTime;
	}

	public void setAgencyTime(String agencyTime) {
		this.agencyTime = agencyTime;
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

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
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
	
	/**
	 *  删除雅思协议信息
	 * @return
	 */
	public String deleteObject() {
		System.out.println("要删除的id:" + id);
		if(aad.deleteAgreement(id))
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
		currAgreement=aad.getAgencyAgreementInforById(id);
		if(currAgreement!=null) {
			agencyTime=currAgreement.getTime();
			agencyName=currAgreement.getAgen().getAgencyName();
			currAgreement.setTime(currAgreement.getTime().substring(0, 10));
			m.put("currAgreement", currAgreement);
			return "lookSuccess";
		}
		else {
			String message="获取数据失败!";
			m.put("error", message);
			return "lookError";
		}
		
	}
	
	/**
	 *  修改协议信息
	 * @return
	 */
	public void updateInfor() throws IOException{
		String message="";
		//获取需要上传文件的文件路径  
		String path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
		String deletepath=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +updateAgreement.getFileName());
		//判断文件是否上传，如果上传的话将会创建该目录 
		//System.out.println(ServletActionContext.getServletContext().getResourceAsStream("/upload"+"\\" +"jsp作用.txt"));
		System.out.println(path);
		if(uploadFileName!=null&&!uploadFileName.equals("")) {
			File target= new File(path); // 定义目标文件对象
			File deleteFile=new File(deletepath);//要删除的文件对象
			if (deleteFile.exists() && deleteFile.isFile())
				deleteFile.delete();
			try {
				FileUtils.copyFile(upload, target);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// 删除临时文件
			upload.delete();
			// 更新文件名
			updateAgreement.setFileName(this.uploadFileName);
			updateAgreement.setTime(updateAgreement.getTime().substring(0, 10));
			// 查询agencyId
			String hh = "from Agency where agencyName='" + agencyName + "'";
			updateAgreement.setAgen(aad.queryByhqlA(hh).get(0));
			// hql语句
			String hql1 = "agen = '" + updateAgreement.getAgen().getAgencyId() + "'";
			String hql2 = "time = '" + updateAgreement.getTime() + "'";
			String hql3 = "type = '" + updateAgreement.getType() + "'";
			String hql4 = "fileName = '" + updateAgreement.getFileName() + "'";
			String hql5 = "title like '%" + updateAgreement.getTitle() + "%'";
			String hql = "from AgencyAgreement where " + hql1 + " and " + hql2 + " and " + hql3 + " and " + hql4
					+ " and " + hql5;
			agreementList = aad.queryByhql(hql);
			if (agreementList == null || agreementList.size() == 0) {
				if (aad.updateAgreement(updateAgreement)) {
					message = "更新成功";
				} else {
					message = "更新失败";
				}
			} else {
				message = "已存在该条信息";
			}
		}
		else {
			message="以上信息不能为空";
		}
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
	

	/**
	 *  添加协议信息
	 * @return
	 */
	public void addObject() throws IOException{
		String message="";
		//获取需要上传文件的文件路径  
		String path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
		//判断文件是否上传，如果上传的话将会创建该目录 
		
		System.out.println(path);
		File target= new File(path); // 定义目标文件对象
		//有协议才能进行添加
		if(uploadFileName!=null&&!uploadFileName.equals("")) {
			addAgreement.setFileName(this.uploadFileName);
			addAgreement.setTime(addAgreement.getTime().toString().substring(0, 10));
			try {
				FileUtils.copyFile(upload, target);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 删除临时文件
			upload.delete();

			// 查询agencyId
			String hh = "from Agency where agencyName='" + agencyName + "'";
			addAgreement.setAgen(aad.queryByhqlA(hh).get(0));
			// hql语句
			String hql1 = "agen = '" + addAgreement.getAgen().getAgencyId() + "'";
			String hql2 = "time = '" + addAgreement.getTime() + "'";
			String hql3 = "type = '" + addAgreement.getType() + "'";
			String hql4 = "fileName = '" + addAgreement.getFileName() + "'";
			String hql5 = "title like '%" + addAgreement.getTitle() + "%'";
			String hql = "from AgencyAgreement where " + hql1 + " and " + hql2 + " and " + hql3 + " and " + hql4 + " and " + hql5;
			// 查出是否有相同的数据在数据库中
			//System.out.println(addAgreement.getTime());
			agreementList = aad.queryByhql(hql);
			if (agreementList == null || agreementList.size() == 0) {
				if (aad.addAgreement(addAgreement) > 0) {
					message = "添加成功";
				} else {
					message = "添加失败";
				}
			} else {
				message = "已存在该条信息";
			}
		}
		else {
			message="以上不能为空";
		}
			
		ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
}
