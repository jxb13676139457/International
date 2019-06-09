package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.College;
import com.international.model.CollegeActivity;
import com.international.model.CollegeAgreement;

public class AgreementDao {
	SessionFactory sessionFactory;
	public AgreementDao() {
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public List<CollegeAgreement> queryAllAgreement(String value){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//获取所有数据
			String hql1="collegeName like '%"+value+"%'";
			String hql2="convert(varchar,time,120) like '%"+value+"%'";
			String hql3="type like '%"+value+"%'";
			String hql4="title like '%"+value+"%'";
			String hql5="fileName like '%"+value+"%'";
			
			//查询名字
			String queryName="from College where "+hql1;
			Query query1=session.createQuery(queryName);
			List<College> list1=query1.list();
			//int v=list1.get(0).getCollegeId();
			//String hql6="coll like '%"+v+"%'";
			int v;
			String hql6="";
			for(int i=0;i<list1.size();i++) {
				if(i==0) {
					v=list1.get(i).getCollegeId();
					hql6=hql6+" collegeId = '"+v+"'";
				}
				else {
					v=list1.get(i).getCollegeId();
					hql6=hql6+" or collegeId = '"+v+"'";
				}
				
			}
			
			String str=hql2+" or "+hql3+" or "+hql4+" or "+hql5+" and "+hql6;
			//获取所有数据
			String queryString="from CollegeAgreement where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个CollegeAgreement的对象
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
	public List<CollegeAgreement> queryAgreement(String value,int pageNo,int pageSize){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//获取所有数据
			String hql1="collegeName like '%"+value+"%'";
			String hql2="convert(varchar,time,120) like '%"+value+"%'";
			String hql3="type like '%"+value+"%'";
			String hql4="title like '%"+value+"%'";
			String hql5="fileName like '%"+value+"%'";
			
			//查询名字
			String queryName="from College where "+hql1;
			Query query1=session.createQuery(queryName);
			List<College> list1=query1.list();
			//int v=list1.get(0).getCollegeId();
			//String hql7="coll like '%"+v+"%'";
			int v;
			String hql6="";
			for(int i=0;i<list1.size();i++) {
				if(i==0) {
					v=list1.get(i).getCollegeId();
					hql6=hql6+" collegeId = '"+v+"'";
				}
				else {
					v=list1.get(i).getCollegeId();
					hql6=hql6+" or collegeId = '"+v+"'";
				}
				
			}
			
			String str=hql2+" or "+hql3+" or "+hql4+" or "+hql5+" and "+hql6;
			//获取所有数据
			String queryString="from CollegeAgreement where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<CollegeAgreement> list=query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  删除协议信息
	 * @return
	 */
	public boolean deleteAgreement(int agreementId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			CollegeAgreement collegeAgreement=(CollegeAgreement)session.get(CollegeAgreement.class, agreementId);
			//删除plane数据
			Transaction trans=session.beginTransaction();
			session.delete(collegeAgreement);//删除数据
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
	 *  根据id查询协议信息
	 * @return
	 */
	public CollegeAgreement getCollegeAgreementInforById(int agreementId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要修改的用户数据
			CollegeAgreement p=(CollegeAgreement)session.get(CollegeAgreement.class, agreementId);
			return p;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  查询符合条件的协议
	 * @return
	 */
	public List<CollegeAgreement> queryByhql(String queryString,String h){
		Session session=null;

		try {
			session=sessionFactory.openSession();
			//查询名字
			String queryName="from College where collegeName="+"'"+h+"'";
			Query query1=session.createQuery(queryName);
			List<College> list1=query1.list();
			//int value=list1.get(0).getCollegeId();
			//String hql4=" and collegeId like '%"+value+"%'";
			int value;
			String hql4="";
			for(int i=0;i<list1.size();i++) {
				if(i==0) {
					value=list1.get(i).getCollegeId();
					hql4=hql4+" and collegeId = '"+value+"'";
				}
				else {
					value=list1.get(i).getCollegeId();
					hql4=hql4+" or collegeId = '"+value+"'";
				}
				
			}
			
			queryString=queryString +hql4;
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个CollegeAgreement的对象
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
	 *  添加协议
	 * @return
	 */
	public int addAgreement(CollegeAgreement c) {
		Session session=null;
		int num=0; //受影响的行数
		
		try{
			//获得Session对象
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
		return num;
	}
	
	/**
	 *  更新协议
	 * @return
	 */
	public boolean updateAgreement(CollegeAgreement c) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//保存数据回数据库
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
