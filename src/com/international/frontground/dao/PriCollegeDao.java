package com.international.frontground.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.international.model.College;
import com.international.model.CollegeActivity;
import com.international.model.CollegeAgreement;
import com.international.model.Teacher;

public class PriCollegeDao {
	SessionFactory sessionFactory;
	public PriCollegeDao() {
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//查询学院信息
	public List<College> queryCollege(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			List list=query.list();
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
	
	//查询院校活动信息
	public List<CollegeActivity> queryCollegeActivity(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			List list=query.list();
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
		
	//查询院校协议信息
	public List<CollegeAgreement> queryCollegeAgreement(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			List list=query.list();
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
	public List<CollegeAgreement> getInformationByTitle(String title) {
		System.out.println("getInformationByTitle被调用");
		
		Session session=null;
		try {
			session=sessionFactory.openSession();
			System.out.println("session = "+session);
			//获取所有数据
			String queryString="from CollegeAgreement where title ='"+title+"'";			
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
}
