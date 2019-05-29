package com.international.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.College;
import com.international.model.InternationalClass;

import org.hibernate.Query;
import org.hibernate.Session;

public class ClassesDao {
	
	SessionFactory sessionFactory;
	
	public ClassesDao() {}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public List<InternationalClass> queryAllClass(){
		System.out.println("queryAllClass被调用");
		
		Session session=null;
		try {
			session=sessionFactory.openSession();
			System.out.println("session = "+session);
			//获取所有数据
			String queryString="from InternationalClass";			
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
	
	
	public List<InternationalClass> queryClass(String value,int pageNo,int pageSize){
		Session session=null;
		try {
			//hql语句
			session=sessionFactory.openSession();
			String hql1 = "className like '%"+value+"%'";
			String hql2 = "grade like '%"+value+"%'";
			String hql3 = "major like '%"+value+"%'";
			
			String str=hql1+" or "+hql2+" or "+hql3;
			//获取所有数据
			String queryString="from InternationalClass where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<InternationalClass> list=query.list();
			System.out.println("list = "+list);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	public boolean deleteClass(int classId) {
		Session session=null;
		try{
			System.out.println("classID = "+classId);
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			InternationalClass classes=(InternationalClass)session.get(InternationalClass.class, classId);
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
	
	
	public boolean updateClassByID(int id, InternationalClass newClass){
		Session session=null;
		try{
			session=sessionFactory.openSession();
			InternationalClass oldClass=(InternationalClass)session.get(InternationalClass.class, id);
			
			oldClass.setGrade(newClass.getGrade());
			oldClass.setMajor(newClass.getMajor());
			oldClass.setClassName(newClass.getClassName());

			Transaction trans=session.beginTransaction();
			session.update(oldClass);
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}	
	}
	
	
	public InternationalClass getClassInforById(int id){
		System.out.println("getClassInforById方法打印classId = "+id);
		Session session=null;	
		InternationalClass  interClass=null;
		try{	
			session=sessionFactory.openSession();	
			String hql="from InternationalClass where id=?";			
			Query query =session.createQuery(hql);		
			query.setParameter(0, id);
			List<InternationalClass> list=query.list();			 
			if(list!=null && list.size()>0){
				interClass=list.get(0);				
				return interClass;
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


	public boolean insertClass(InternationalClass newClass){
		Session session = null;
		System.out.println("insertClass方法被调用");
		try{
			session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			Integer.parseInt(session.save(newClass).toString());
			trans.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

}
