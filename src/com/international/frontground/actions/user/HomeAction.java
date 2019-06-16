package com.international.frontground.actions.user;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.UserDao;
import com.international.model.News;
import com.international.model.Notice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class HomeAction extends ActionSupport{
	UserDao ud;
	List<News> newsList;
	List<Notice> noticesList;
	public HomeAction(){
		
	}
	
	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public List<Notice> getNoticesList() {
		return noticesList;
	}

	public void setNoticesList(List<Notice> noticesList) {
		this.noticesList = noticesList;
	}

	public UserDao getUd() {
		return ud;
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	
	//获取新闻信息
	public void homeNews() throws IOException{
		String hql="from News";
		newsList=ud.queryByNews(hql);
		for(int i=0;i<newsList.size();i++) {
			newsList.get(i).setTime(newsList.get(i).getTime().substring(0, 10));
		}
		//System.out.println(newsList.get(0).getTime());
		//session.put("homeNewList", newsList);
		ajaxAction.toJson(ServletActionContext.getResponse(),newsList);
	}
	
	//获取通知信息
	public void homeNotice() throws IOException{
		String hql="from Notice";
		noticesList=ud.queryByNotice(hql);
		System.out.println(noticesList);
		for(int i=0;i<noticesList.size();i++) {
			noticesList.get(i).setTime(noticesList.get(i).getTime().substring(0, 10));
		}
		System.out.println(noticesList);
		//session.put("homeNewList", newsList);
		ajaxAction.toJson(ServletActionContext.getResponse(),noticesList);
	}
}
