package com.international.actions.infomation;

import java.util.List;
import java.util.Map;

import com.international.dao.NoticeDao;
import com.international.model.Notice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class NoticeAction extends ActionSupport{

	private NoticeDao nd;
	private List<Notice> notices;
	private Notice notice;
	Map m;
	String searchClassName;
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	String searchNoticeTime = "";
	

	
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public NoticeDao getNd() {
		return nd;
	}
	public void setNd(NoticeDao nd) {
		this.nd = nd;
	}
	public List<Notice> getNotices() {
		return notices;
	}
	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}
	public Map getM() {
		return m;
	}
	public void setM(Map m) {
		this.m = m;
	}
	public String getSearchClassName() {
		return searchClassName;
	}
	public void setSearchClassName(String searchClassName) {
		this.searchClassName = searchClassName;
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
	public String getSearchNoticeTime() {
		return searchNoticeTime;
	}
	public void setSearchNoticeTime(String searchNoticeTime) {
		this.searchNoticeTime = searchNoticeTime;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	
	public String execute() {
		System.out.println("nd = "+nd);
		System.out.println("execute默认方法被调用");
		m=ActionContext.getContext().getSession();
		notices=nd.queryAllNotice();
		System.out.println(searchNoticeTime);
		//计算总页数
		System.out.println("notices = "+notices);
		if(notices.size()%pageSize==0){
			totalPage=notices.size()/pageSize;
		}else{
			totalPage=notices.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		notices=nd.queryNotice(searchNoticeTime,pageNo,pageSize);
		//System.out.println("id = "+classes.get(0).getClassId());
		//设置当前页
		currentPage=pageNo;
		m.put("notices", notices);
		System.out.println("notices = "+notices);

		return SUCCESS;
	}
	
	
	public String searchNotice(){
		System.out.println("SearchClass方法被调用");
		m=ActionContext.getContext().getSession();
		notices=nd.queryAllNotice();
		System.out.println(searchNoticeTime);
		//计算总页数
		if(notices.size()%pageSize==0){
			totalPage=notices.size()/pageSize;
		}else{
			totalPage=notices.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		System.out.println("searchNoticeTime = "+searchNoticeTime);
		notices=nd.queryNotice(searchNoticeTime,pageNo,pageSize);
		//设置当前页
		currentPage=pageNo;
		m.put("notices", notices);
		return SUCCESS;
	}
	
	
	public String addNotice() {
		System.out.println("addClass方法被调用了");
		System.out.println("Notice对象信息 = "+notice);
		System.out.println("Notice对象Title = "+notice.getTitle());
		System.out.println("Notice对象Time = "+notice.getTime());
		System.out.println("Notice对象Content = "+notice.getContent());
		System.out.println("Notice对象Source = "+notice.getSource());
		System.out.println("Notice对象Author = "+notice.getAuthor());
		notice.setTime((notice.getTime().substring(0, 10)));
		if(nd.insertNotice(notice))
			return "addSuccess";
		return "addError";
	}
		
}
