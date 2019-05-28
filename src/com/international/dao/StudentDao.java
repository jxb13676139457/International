package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.international.model.Admin;
import com.international.model.InternationalStudent;

public class StudentDao {
	
	SessionFactory sessionFactory;
	InternationalStudent interStudent;
	
	public StudentDao() {
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public InternationalStudent getInterStudent() {
		return interStudent;
	}
	public void setInterStudent(InternationalStudent interStudent) {
		this.interStudent = interStudent;
	}
	
	//查询所有国际班学生信息
	public List<InternationalStudent> queryInterStudents(String value){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "studentId like '%"+value+"%'";
			String hql2 = "studentName like '%"+value+"%'";
			String hql3 = "sex like '%"+value+"%'";
			String hql4 = "password like '%"+value+"%'";
			String hql5 = "status like '%"+value+"%'";
			String hql6 = "classId like '%"+value+"%'";
			hql = "from InternationalStudent where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5+" or "+ hql6;
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
	
	//分页查询需要显示的数据(每次最多4条记录)
	public List<InternationalStudent> queryByPage(String value,int pageNo,int pageSize){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "studentId like '%"+value+"%'";
			String hql2 = "studentName like '%"+value+"%'";
			String hql3 = "sex like '%"+value+"%'";
			String hql4 = "password like '%"+value+"%'";
			String hql5 = "status like '%"+value+"%'";
			String hql6 = "classId like '%"+value+"%'";
			hql = "from InternationalStudent where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5+" or "+ hql6;
			Query query = session.createQuery(hql);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			List<InternationalStudent> list = query.list();
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
