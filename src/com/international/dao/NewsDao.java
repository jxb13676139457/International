package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.News;
import com.international.model.Notice;

public class NewsDao {

	SessionFactory sessionFactory;
	
	public NewsDao() {}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<News> queryAllNews() {
		System.out.println("queryAllNews被调用");
		
		Session session=null;
		try {
			session=sessionFactory.openSession();
			System.out.println("session = "+session);
			//获取所有数据
			String queryString="from News";			
			//创建查询
			Query query=session.createQuery(queryString);
			System.out.println("query = "+query);
			//执行查询获得的结果,list中的每一个元素代表一个College的对象
			List list=query.list();//list集合包含InternationalClass表里所有数据
			if(list.size()>0)
				return list;
			else{
				return null;
				}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}

	public List<News> queryNews(String value, int pageNo, int pageSize) {
		Session session=null;
		try {
			//hql语句
			session=sessionFactory.openSession();
			String hql1 = "convert(varchar,time,120) like '%"+value+"%'";
			
			String str = hql1;
			//获取所有数据
			String queryString="from News where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<News> list=query.list();
			System.out.println("list = "+list);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}

	public boolean insertNews(News newNews) {
		Session session = null;
		System.out.println("insertNews方法被调用");
		try{
			session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			Integer.parseInt(session.save(newNews).toString());
			trans.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public boolean deleteNews(int NewsId) {
		Session session=null;
		try{
			System.out.println("NewsId = "+NewsId);
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			News newses=(News)session.get(News.class, NewsId);
			//删除plane数据
			Transaction trans=session.beginTransaction();
			session.delete(newses);//删除数据
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}

	public News getNewsInforById(int id) {
		System.out.println("getNewsInforById方法打印NewsId = "+id);
		Session session=null;	
		News interNews=null;
		try{	
			session=sessionFactory.openSession();	
			String hql="from News where id=?";			
			Query query =session.createQuery(hql);		
			query.setParameter(0, id);
			List<News> list=query.list();			 
			if(list!=null && list.size()>0){
				interNews=list.get(0);				
				return interNews;
			}else{				
				return null;
			}	
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{        	
        	session.close();
        }	
	}

	public boolean updateNewsByID(int id, News newNews) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			News oldNews=(News)session.get(News.class, id);
			
			oldNews.setTitle(newNews.getTitle());;
			oldNews.setTime(newNews.getTime());
			oldNews.setSource(newNews.getSource());
			oldNews.setAuthor(newNews.getAuthor());
			oldNews.setNewsUrl(newNews.getNewsUrl());

			Transaction trans=session.beginTransaction();
			session.update(oldNews);
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}	
	}
	
	
}
