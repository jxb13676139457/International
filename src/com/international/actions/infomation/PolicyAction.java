package com.international.actions.infomation;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.international.dao.PolicyDao;
import com.international.model.Policy;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PolicyAction extends ActionSupport {

	private File upload;
	private String savePath;
	private String uploadFileName;
	private PolicyDao pd;
	private List<Policy> policyList;
	private String searchPolicy="";
	private Policy policy;
	private Map m;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
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

	public String getSearchPolicy() {
		return searchPolicy;
	}
	public void setSearchPolicy(String searchPolicy) {
		this.searchPolicy = searchPolicy;
	}
	public Map getM() {
		return m;
	}
	public void setM(Map m) {
		this.m = m;
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
		System.out.println("pd = "+pd);
		System.out.println("execute默认方法被调用");
		m=ActionContext.getContext().getSession();
		m.put("searchPolicy", searchPolicy);
		policyList=pd.queryAllPolicy();
		System.out.println(searchPolicy);
		//计算总页数
		System.out.println("policyList = "+policyList);
		if(policyList==null) {
			currentPage=0;
			totalPage=0;
			return SUCCESS;
		}
		if(policyList.size()%pageSize==0){
			totalPage=policyList.size()/pageSize;
		}else{
			totalPage=policyList.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		policyList=pd.queryPolicy(searchPolicy,pageNo,pageSize);
		for(int i=0;i<policyList.size();i++) {
			policyList.get(i).setTime(policyList.get(i).getTime().substring(0, 10));
		}
		//System.out.println("id = "+classes.get(0).getClassId());
		//设置当前页
		currentPage=pageNo;
		m.put("policyList", policyList);
		System.out.println("policyList = "+policyList);

		return SUCCESS;
	}
	
	
	public String searchPolicy(){
		System.out.println("searchPolicy方法被调用");
		m=ActionContext.getContext().getSession();
		policyList=pd.queryAllPolicy();
		System.out.println("searchPolicy = "+searchPolicy);
		//计算总页数
		if(policyList.size()%pageSize==0){
			totalPage=policyList.size()/pageSize;
		}else{
			totalPage=policyList.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		System.out.println("searchPolicy = "+searchPolicy);
		policyList=pd.queryPolicy(searchPolicy,pageNo,pageSize);
		//设置当前页
		currentPage=pageNo;
		m.put("policyList", policyList);
		return SUCCESS;
	}
	
	
	public String addPolicy() {
		
		policy.setTime((policy.getTime().substring(0, 10)));
		String path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
		System.out.println("*********"+path+"*********");
		File target= new File(path);
				
		policy.setFileName(this.uploadFileName);
		try{
	            FileUtils.copyFile(upload, target);
	    }catch (Exception e) {
	            e.printStackTrace();
	    }				
	     try{
	    	 upload.delete();
	     }catch (Exception e) {
	         e.printStackTrace();
	     }		
	     
	     
	    System.out.println("addPolicy方法被调用了");
		System.out.println("policy对象信息 = "+policy);
		System.out.println("policy对象Title = "+policy.getTitle());
		System.out.println("policy对象Time = "+policy.getTime());
		System.out.println("policy对象Source = "+policy.getSource());
		System.out.println("policy对象FileName = "+policy.getFileName());
		System.out.println("policy对象SavePath = "+policy.getSavePath());
			
		if(pd.insertPolicy(policy))
			return "addSuccess";
		return "addError";
	}
	
	
	/**
	 *  删除通知信息
	 */
	public String deletePolicy() {

		System.out.println("要删除的id:" + id);
		if(pd.deletePolicy(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	
	public String searchObjectById() {
		System.out.println("id = "+id);
		
		policy = pd.getPolicyInforById(id);
		if(policy!=null) {
			System.out.println("policy对象信息 = "+policy);
			System.out.println("policy对象ID = "+policy.getPolicyId());
			System.out.println("policy对象Title = "+policy.getTitle());
			System.out.println("policy对象Time = "+policy.getTime());
			System.out.println("policy对象Source = "+policy.getSource());
			System.out.println("policy对象fileName = "+policy.getFileName());
			
			return "lookSuccess";
		}
		return "lookError";
	}
	
	
	public  String updatePolicyInfor(){
		System.out.println("updatePolicyInfor被调用");
		Map  session =ActionContext.getContext().getSession();	
		System.out.println("policy对象信息 = "+policy);
		System.out.println("policy对象ID = "+policy.getPolicyId());
		System.out.println("policy对象Title = "+policy.getTitle());
		System.out.println("policy对象Time = "+policy.getTime());
		System.out.println("policy对象Source = "+policy.getSource());
		System.out.println("policy对象fileName = "+policy.getFileName());
		policy.setTime((policy.getTime().substring(0, 10)));
		System.out.println("要更新的id:" + id);
		String path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
		System.out.println("*********"+path+"*********");
		File target= new File(path);
				
		policy.setFileName(this.uploadFileName);
		try{
	            FileUtils.copyFile(upload, target);
	    }catch (Exception e) {
	            e.printStackTrace();
	    }				
	     try{
	    	 upload.delete();
	     }catch (Exception e) {
	         e.printStackTrace();
	     }		
		if(pd.updatePolicyByID(id, policy))
			return "updateSuccess";
		else
			return "updateError";

    }
}
