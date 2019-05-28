package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Agency;
import com.international.model.News;
import com.international.model.Policy;

public class AgencyDao {

	SessionFactory sessionFactory;
	
	public AgencyDao() {}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

	public List<Agency> queryAllAgency() {
		System.out.println("queryAllAgency被调用");
		
		Session session=null;
		try {
			session=sessionFactory.openSession();
			System.out.println("session = "+session);
			//获取所有数据
			String queryString="from Agency";			
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

	public List<Agency> queryAgency(String value, int pageNo, int pageSize) {
		Session session=null;
		try {
			//hql语句
			session=sessionFactory.openSession();
			String hql1 = "agencyName like '%"+value+"%'";
			
			String str = hql1;
			//获取所有数据
			String queryString="from Agency where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<Agency> list=query.list();
			System.out.println("list = "+list);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}

	public boolean insertAgency(Agency newAgency) {
		Session session = null;
		System.out.println("insertAgency方法被调用");
		try{
			session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			Integer.parseInt(session.save(newAgency).toString());
			trans.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public boolean deleteAgency(int AgencyId) {
		Session session=null;
		try{
			System.out.println("AgencyId = "+AgencyId);
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			Agency classes=(Agency)session.get(Agency.class, AgencyId);
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

	public Agency getAgencyInforById(int id) {
		System.out.println("getAgencyInforById方法打印agencyId = "+id);
		Session session=null;	
		Agency interNotice=null;
		try{	
			session=sessionFactory.openSession();	
			String hql="from Agency where id=?";			
			Query query =session.createQuery(hql);		
			query.setParameter(0, id);
			List<Agency> list=query.list();			 
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

	public boolean updateAgencyByID(int id, Agency newAgency) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Agency oldAgency=(Agency)session.get(Agency.class, id);
			
			oldAgency.setAgencyName(newAgency.getAgencyName());
			oldAgency.setTime(newAgency.getTime());
			oldAgency.setCotactPerson(newAgency.getCotactPerson());
			oldAgency.setPosition(newAgency.getPosition());
			oldAgency.setPhone(newAgency.getPhone());
			oldAgency.setEmail(newAgency.getEmail());

			Transaction trans=session.beginTransaction();
			session.update(oldAgency);
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
