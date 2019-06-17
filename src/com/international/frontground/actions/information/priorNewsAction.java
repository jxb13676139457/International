package com.international.frontground.actions.information;

import java.util.List;
import java.util.Map;

import com.international.dao.NewsDao;
import com.international.model.News;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class priorNewsAction extends ActionSupport {

	NewsDao nd;
	private List<News> newsList;
	private News news;
	Map<String, Object> m;
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
	
	public String execute() {
		System.out.println("nd = "+nd);
		System.out.println("execute默认方法被调用");
		m=ActionContext.getContext().getSession();
		newsList=nd.queryAllNews();
		for(int i=0;i<newsList.size();i++) {
			newsList.get(i).setTime(newsList.get(i).getTime().substring(0, 10));
		}
		m.put("newsList", newsList);
		System.out.println("newsList = "+newsList);

		return SUCCESS;
	}
}