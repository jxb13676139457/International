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
}
