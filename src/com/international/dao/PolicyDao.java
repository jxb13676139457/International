package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Notice;
import com.international.model.Policy;

public class PolicyDao {

	SessionFactory sessionFactory;
	
	public PolicyDao() {}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Policy> queryAllPolicy() {
		System.out.println("queryAllPolicy被调用");
		
		Session session=null;
		try {
			session=sessionFactory.openSession();
			System.out.println("session = "+session);
			//获取所有数据
			String queryString="from Policy";			
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

	public List<Policy> queryPolicy(String value, int pageNo, int pageSize) {
		Session session=null;
		try {
			//hql语句
			session=sessionFactory.openSession();
			String hql1="convert(varchar,time,120) like '%"+value+"%'";
			String str = hql1;
			//获取所有数据
			String queryString="from Policy where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<Policy> list=query.list();
			System.out.println("list = "+list);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}

	public boolean insertPolicy(Policy newPolicy) {
		Session session = null;
		System.out.println("insertPolicy方法被调用");
		try{
			session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			Integer.parseInt(session.save(newPolicy).toString());
			trans.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public boolean deletePolicy(int PolicyId) {
		Session session=null;
		try{
			System.out.println("PolicyId = "+PolicyId);
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			Policy classes=(Policy)session.get(Policy.class, PolicyId);
			//删除plane数据
			Transaction trans=session.beginTransaction();
			session.delete(classes);//删除数据
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}

	public Policy getPolicyInforById(int id) {
		System.out.println("getPolicyInforById方法打印PolicyId = "+id);
		Session session=null;	
		Policy interNotice=null;
		try{	
			session=sessionFactory.openSession();	
			String hql="from Policy where id=?";			
			Query query =session.createQuery(hql);		
			query.setParameter(0, id);
			List<Policy> list=query.list();			 
			if(list!=null && list.size()>0){
				interNotice=list.get(0);				
				return interNotice;
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

	public boolean updatePolicyByID(int id, Policy newPolicy) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Policy oldPolicy=(Policy)session.get(Policy.class, id);
			
			oldPolicy.setTitle(newPolicy.getTitle());;
			oldPolicy.setTime(newPolicy.getTime());
			oldPolicy.setSource(newPolicy.getSource());
			oldPolicy.setFileName(newPolicy.getFileName());

			Transaction trans=session.beginTransaction();
			session.update(oldPolicy);
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}	
	}

	public List<Policy> getInformationByTitle(String title) {
		System.out.println("getInformationByTitle被调用");
		
		Session session=null;
		try {
			session=sessionFactory.openSession();
			System.out.println("session = "+session);
			//获取所有数据
			String queryString="from Policy where title ='"+title+"'";			
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
