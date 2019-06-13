package com.international.frontground.actions.information;

import java.util.List;
import java.util.Map;

import com.international.dao.NoticeDao;
import com.international.model.Notice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class priorNoticeAction extends ActionSupport {

	

	private NoticeDao nd;
	private List<Notice> notices;
	private Notice notice;
	Map<String, Object> m;
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
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
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
		notices=nd.queryAllNotice();
		m.put("notices", notices);
		System.out.println("notices = "+notices);

		return SUCCESS;
	}
}
