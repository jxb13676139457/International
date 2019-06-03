package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.ExchangeStudent;
import com.international.model.InternationalStudent;

public class ExchangeStuDao {
	SessionFactory sessionFactory;
	ExchangeStudent exchangeStudent;
	
	public ExchangeStuDao() {
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ExchangeStudent getExchangeStudent() {
		return exchangeStudent;
	}

	public void setExchangeStudent(ExchangeStudent exchangeStudent) {
		this.exchangeStudent = exchangeStudent;
	}
	
	//查询所有交换生信息
	public List<ExchangeStudent> queryInterStudents(String value){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "studentNo like '%"+value+"%'";
			String hql2 = "studentName like '%"+value+"%'";
			String hql3 = "sex like '%"+value+"%'";
			String hql4 = "major like '%"+value+"%'";
			String hql5 = "className like '%"+value+"%'";
			String hql6 = "startTime like '%"+value+"%'";
			String hql7 = "endTime like '%"+value+"%'";
			String hql8 = "exchangeCollege like '%"+value+"%'";
			hql = "from ExchangeStudent where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "
					+ hql5+" or "+ hql6+" or "+hql7+" or "+hql8;
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
	
	//分页查询需要显示的数据(每次最多4条记录)
	public List<ExchangeStudent> queryByPage(String value,int pageNo,int pageSize){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "studentNo like '%"+value+"%'";
			String hql2 = "studentName like '%"+value+"%'";
			String hql3 = "sex like '%"+value+"%'";
			String hql4 = "major like '%"+value+"%'";
			String hql5 = "className like '%"+value+"%'";
			String hql6 = "startTime like '%"+value+"%'";
			String hql7 = "endTime like '%"+value+"%'";
			String hql8 = "exchangeCollege like '%"+value+"%'";
			hql = "from ExchangeStudent where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "
					+ hql5+" or "+ hql6+" or "+hql7+" or "+hql8;
			Query query = session.createQuery(hql);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			List<ExchangeStudent> list = query.list();
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
	
	//删除交换生信息
	public boolean delExStudent(int id) {
		System.out.println("从前台传过来的id："+id);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//根据id获取要删除的交换生对象
			ExchangeStudent exchangeStudent = (ExchangeStudent)session.get(ExchangeStudent.class,id);
			session.delete(exchangeStudent);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//根据id查询选中的交换生，以供修改
	public ExchangeStudent queryById(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			ExchangeStudent exchangeStudent = (ExchangeStudent)session.get(ExchangeStudent.class,id);
			//System.out.println(interStudent);
			return exchangeStudent;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//修改国际班学生信息  
	public boolean updateStudent(ExchangeStudent newStudent) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//直接修改一整个对象
			session.saveOrUpdate(newStudent);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//添加交换生信息    
	public boolean addExStudent(ExchangeStudent exchangeStudent) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.save(exchangeStudent);
			tran.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}
}
