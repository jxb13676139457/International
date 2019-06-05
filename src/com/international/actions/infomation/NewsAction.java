package com.international.actions.infomation;

import java.util.List;
import java.util.Map;

import com.international.dao.NewsDao;
import com.international.model.News;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class NewsAction extends ActionSupport{

	NewsDao nd;
	private List<News> newsList;
	private News news;
	Map m;
	private String searchNews="";
	private int id; //界面显示数据的索引
	private final int pageSize=6; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage=0; //当前页
	private int totalPage=0; //总页数
	
	
	public String getSearchNews() {
		return searchNews;
	}
	public void setSearchNews(String searchNews) {
		this.searchNews = searchNews;
	}
	public NewsDao getNd() {
		return nd;
	}
	public void setNd(NewsDao nd) {
		this.nd = nd;
	}
	
	public List<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
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
			
		System.out.println("nd = "+nd);
		System.out.println("execute默认方法被调用");
		m=ActionContext.getContext().getSession();
		m.put("searchNews", searchNews);
		newsList=nd.queryAllNews();
		System.out.println("searchNews = "+searchNews);
		//计算总页数
		System.out.println("newsList = "+newsList);
		if(newsList==null) {
			currentPage=0;
			totalPage=0;
			return SUCCESS;
		}
		if(newsList.size()%pageSize==0){
			totalPage=newsList.size()/pageSize;
		}else{
			totalPage=newsList.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		newsList=nd.queryNews(searchNews,pageNo,pageSize);
		for(int i=0;i<newsList.size();i++) {
			newsList.get(i).setTime(newsList.get(i).getTime().substring(0, 10));
		}
		//System.out.println("id = "+classes.get(0).getClassId());
		//设置当前页
		currentPage=pageNo;
		m.put("newsList", newsList);
		System.out.println("newsList = "+newsList);
	
		return SUCCESS;
	}


	public String searchNotice(){
		System.out.println("SearchClass方法被调用");
		m=ActionContext.getContext().getSession();
		newsList=nd.queryAllNews();
		System.out.println(searchNews);
		//计算总页数
		if(newsList.size()%pageSize==0){
			totalPage=newsList.size()/pageSize;
		}else{
			totalPage=newsList.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//根据当前页查询要在该页上显示的数据
		System.out.println("searchNews = "+searchNews);
		newsList=nd.queryNews(searchNews,pageNo,pageSize);
		//设置当前页
		currentPage=pageNo;
		m.put("newsList", newsList);
		return SUCCESS;
	}
	
	
	public String addNews() {
		System.out.println("addClass方法被调用了");
		System.out.println("news对象信息 = "+news);
		System.out.println("news对象Title = "+news.getTitle());
		System.out.println("news对象Time = "+news.getTime());
		System.out.println("news对象Source = "+news.getSource());
		System.out.println("news对象Author = "+news.getAuthor());
		System.out.println("news对象NewsUrl = "+news.getNewsUrl());
		news.setTime((news.getTime().substring(0, 10)));
		if(nd.insertNews(news))
			return "addSuccess";
		return "addError";
	}
	
	
	/**
	 *  删除通知信息
	 */
	public String deleteNotice() {

		System.out.println("要删除的id:" + id);
		if(nd.deleteNews(id))
			return "deleteSuccess";
		else
			return "deleteError";
	}
	
	
	public String searchObjectById() {
		System.out.println("id = "+id);
		
		news = nd.getNewsInforById(id);
		if(news!=null) {
			System.out.println("news对象信息 = "+news);
			System.out.println("news对象ID = "+news.getNewsId());
			System.out.println("news对象Title = "+news.getTitle());
			System.out.println("news对象Time = "+news.getTime());
			System.out.println("news对象Source = "+news.getSource());
			
			return "lookSuccess";
		}
		return "lookError";
	}
	
	
	/*
	 * 更新新闻信息
	 * */
	public  String updateNewsInfor(){
		System.out.println("updateNewsInfor被调用");
		Map  session =ActionContext.getContext().getSession();	
		System.out.println("news对象信息 = "+news);
		System.out.println("news对象ID = "+news.getNewsId());
		System.out.println("news对象Title = "+news.getTitle());
		System.out.println("news对象Time = "+news.getTime());
		System.out.println("news对象Source = "+news.getSource());
		news.setTime((news.getTime().substring(0, 10)));
		System.out.println("要更新的id:" + id);
		if(nd.updateNewsByID(id, news))
			return "updateSuccess";
		else
			return "updateError";

    }
	
}
