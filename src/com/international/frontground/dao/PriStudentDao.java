package com.international.frontground.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.international.model.ExchangeStudent;
import com.international.model.InternationalStudent;
import com.international.model.OverseasStudent;
import com.international.model.StudentActivity;

public class PriStudentDao {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//教师查询国际班学生信息
	public List<InternationalStudent> queryInterStudent() {
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			hql = "from InternationalStudent";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("查询到的全部国际班学生："+list);
			if(list.size()>0) {
				return list;
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//教师查询交换生信息
	public List<ExchangeStudent> queryExStudent() {
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			hql = "from ExchangeStudent";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("查询到的全部交换生："+list);
			if(list.size()>0) {
				return list;
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//教师查询交换生信息
	public List<OverseasStudent> queryOverseas() {
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			hql = "from OverseasStudent";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("查询到的全部出国学生："+list);
			if(list.size()>0) {
				return list;
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//教师查询交换生信息
	public List<StudentActivity> queryActivity() {
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			hql = "from StudentActivity";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("查询到的全部学生活动（夏令营）："+list);
			if(list.size()>0) {
				return list;
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//分页查询需要显示的数据(每次最多7条记录)
	public <T> List<T> queryByPage(String obj,int pageNo,int pageSize){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			List<T> list = null;
			if(obj=="InternationalStudent") {
				hql = "from InternationalStudent";
				Query query = session.createQuery(hql);
				query.setFirstResult((pageNo-1)*pageSize);
				query.setMaxResults(pageSize);
				list = query.list();
			}else if(obj=="ExchangeStudent") {
				hql = "from ExchangeStudent";
				Query query = session.createQuery(hql);
				query.setFirstResult((pageNo-1)*pageSize);
				query.setMaxResults(pageSize);
				list = query.list();
			}else if(obj=="OverseasStudent") {
				hql = "from OverseasStudent";
				Query query = session.createQuery(hql);
				query.setFirstResult((pageNo-1)*pageSize);
				query.setMaxResults(pageSize);
				list = query.list();
			}else if(obj=="StudentActivity") {
				hql = "from StudentActivity";
				Query query = session.createQuery(hql);
				query.setFirstResult((pageNo-1)*pageSize);
				query.setMaxResults(pageSize);
				list = query.list();
			}
			if(list.size()>0) {
				return list;
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
}
