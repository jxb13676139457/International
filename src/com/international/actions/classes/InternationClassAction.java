package com.international.actions.classes;

import java.util.List;
import java.util.Map;

import com.international.dao.ClassesDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.international.model.InternationalClass;

public class InternationClassAction extends ActionSupport{

	private List<InternationalClass> classes;
	Map m;
	String searchClassName;
	ClassesDao clsd;
	InternationalClass currentClass;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String search="";
	
	public String getSearchClassName() {
		return searchClassName;
	}

	public void setSearchClassName(String searchClassName) {
		this.searchClassName = searchClassName;
	}

	public InternationClassAction() {
		
	}

	public List<InternationalClass> getClasses() {
		return classes;
	}

	public void setClasses(List<InternationalClass> classes) {
		this.classes = classes;
	}



	public Map getM() {
		return m;
	}

	public void setM(Map m) {
		this.m = m;
	}

	public ClassesDao getClsd() {
		return clsd;
	}

	public void setClsd(ClassesDao clsd) {
		this.clsd = clsd;
	}

	public InternationalClass getCurrentClass() {
		return currentClass;
	}

	public void setCurrentClass(InternationalClass currentClass) {
		this.currentClass = currentClass;
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
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	public String execute() {
		System.out.println("调用execute方法");
		System.out.println("clsd = "+clsd);
		m=ActionContext.getContext().getSession();
		classes=clsd.queryAllClass();
		System.out.println(search);
		//计算总页数
		if(classes.size()%pageSize==0){
			totalPage=classes.size()/pageSize;
		}else{
			totalPage=classes.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		classes=clsd.queryClass(search,pageNo,pageSize);
		//System.out.println("id = "+classes.get(0).getClassId());
		//设置当前页
		currentPage=pageNo;
		m.put("classes", classes);
		return SUCCESS;
	}
	
	
public String searchClass(){
	System.out.println("SearchClass方法被调用");
	m=ActionContext.getContext().getSession();
	classes=clsd.queryAllClass();
	System.out.println(search);
	//计算总页数
	if(classes.size()%pageSize==0){
		totalPage=classes.size()/pageSize;
	}else{
		totalPage=classes.size()/pageSize+1;
	}
	if(pageNo<=0){
		pageNo=1;
	}else if(pageNo>totalPage){
		pageNo=totalPage;
	}
	//根据当前页查询要在该页上显示的数据
	System.out.println("searchClassName = "+searchClassName);
	classes=clsd.queryClass(searchClassName,pageNo,pageSize);
	//设置当前页
	currentPage=pageNo;
	m.put("classes", classes);
	return SUCCESS;
}
	
	
	/**
	 *  删除班级信息
	 * @return
	 */
	public String deleteClass() {

		System.out.println("要删除的id:" + id);
		if(clsd.deleteClass(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	/**
	 *  修改班级信息
	 * @return
	 */
	public  String updateClassInfor(){
		
		Map  session =ActionContext.getContext().getSession();	
    	System.out.println("Class对象信息 = "+currentClass);
		System.out.println("Class对象ID = "+currentClass.getClassId());
		System.out.println("Class对象Grade = "+currentClass.getGrade());
		System.out.println("Class对象className = "+currentClass.getClassName());
		
		if(clsd.updateClassByID(id, currentClass))
			return "updateSuccess";
		else
			return "updateError";

    }
	
	
	
	public String searchObjectById() {
		System.out.println("id = "+id);
		
		currentClass = clsd.getClassInforById(id);
		if(currentClass!=null) {
			System.out.println("Class对象信息 = "+currentClass);
			System.out.println("Class对象ID = "+currentClass.getClassId());
			System.out.println("Class对象Grade = "+currentClass.getGrade());
			System.out.println("Class对象className = "+currentClass.getClassName());
			
			return "lookSuccess";
		}
		return "lookError";
	}
	
	
	/*
	 * 增加班级信息
	 * 
	*/
	public String addClass() {
		System.out.println("addClass方法被调用了");
		System.out.println("Class对象信息 = "+currentClass);
		System.out.println("Class对象Grade = "+currentClass.getGrade());
		System.out.println("Class对象className = "+currentClass.getClassName());
		if(clsd.insertClass(currentClass))
			return "addSuccess";
		return "addError";
	}

}
