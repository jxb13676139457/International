package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.College;

public class CollegeDao {
	SessionFactory sessionFactory;
	public CollegeDao() {
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  查询所有国外院校
	 * @return
	 */
	public List<College> queryAllCollege(){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//获取所有数据
			String queryString="from College";
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个College的对象
			List list=query.list();//list集合包含College表里所有数据
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
	
	/**
	 *  分页查询国际院校信息
	 * @return
	 */
	public List<College> queryCollege(String value,int pageNo,int pageSize){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//hql语句
			String hql1="collegeName like '%"+value+"%'";
			String hql2="startTime like '%"+value+"%'";
			String hql3="type like '%"+value+"%'";
			String hql4="status like '%"+value+"%'";
			String hql5="country like '%"+value+"%'";
			String hql6="contactPerson like '%"+value+"%'";
			String hql7="phone like '%"+value+"%'";
			String str=hql1+" or "+hql2+" or "+hql3+" or "+hql4+" or "+hql5+" or "+hql6+" or "+hql7;
			//获取所有数据
			String queryString="from College where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<College> list=query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  删除国际院校信息
	 * @return
	 */
	public boolean deleteCollege(int collegeId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			College college=(College)session.get(College.class, collegeId);
			//删除plane数据
			Transaction trans=session.beginTransaction();
			session.delete(college);//删除数据
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  根据id查询国际院校信息
	 * @return
	 */
	public College getAbroadCollegeInforById(int collegeId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要修改的用户数据
			College p=(College)session.get(College.class, collegeId);
			return p;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	/**
	 *  查询符合条件的国外院校
	 * @return
	 */
	public List<College> queryByhql(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个College的对象
			List list=query.list();//list集合包含College表里所有数据
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
	/**
	 *  添加国外院校
	 * @return
	 */
	public int addCollege(College c) {
		Session session=null;
		int num=0; //受影响的行数
		
		try{
			//获得Sesion对象
			session=sessionFactory.openSession();
			//开始事物
			Transaction trans=session.beginTransaction();
			//保存数据,返回受影响的行数
			num=Integer.parseInt(session.save(c).toString());
			//提交事物,才是真正写入数据库表中
			trans.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		System.out.println(num);
		return num;
	}
	/**
	 *  更新国外院校
	 * @return
	 */
	public boolean updateCollege(College c) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//保存oldUser数据回数据库
			Transaction trans=session.beginTransaction();
			//调用保存更新方法
			session.saveOrUpdate(c);
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{//关闭session
			session.close();//关闭Session
		}	
	}
}
