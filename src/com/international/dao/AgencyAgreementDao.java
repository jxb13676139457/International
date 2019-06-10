package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Agency;
import com.international.model.AgencyAgreement;
import com.international.model.College;
import com.international.model.CollegeAgreement;
import com.international.model.Exam;

public class AgencyAgreementDao {
	SessionFactory sessionFactory;
	public AgencyAgreementDao() {
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 *  查询所有机构协议信息
	 * @return
	 */
	public List<AgencyAgreement> queryAllAgreement(String value){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//获取所有数据
			String hql1="agencyName like '%"+value+"%'";
			
			//查询名字
			String queryName="from Agency where "+hql1;
			Query query1=session.createQuery(queryName);
			List<Agency> list1=query1.list();
			
			int v;
			String hql6="";
			for(int i=0;i<list1.size();i++) {
				if(i==0) {
					v=list1.get(i).getAgencyId();
					hql6=hql6+" agencyId = '"+v+"'";
				}
				else {
					v=list1.get(i).getAgencyId();
					hql6=hql6+" or agencyId = '"+v+"'";
				}
				
			}
			
			String str=hql6;
			//获取所有数据
			String queryString="from AgencyAgreement where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个AgencyAgreement的对象
			List list=query.list();//list集合包含AgencyAgreement表里所有数据
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
	 *  分页查询机构协议信息
	 * @return
	 */
	public List<AgencyAgreement> queryAgreement(String value,int pageNo,int pageSize){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//获取所有数据
			String hql1="agencyName like '%"+value+"%'";
			
			//查询名字
			String queryName="from Agency where "+hql1;
			Query query1=session.createQuery(queryName);
			List<Agency> list1=query1.list();
			
			int v;
			String hql6="";
			for(int i=0;i<list1.size();i++) {
				if(i==0) {
					v=list1.get(i).getAgencyId();
					hql6=hql6+" agencyId = '"+v+"'";
				}
				else {
					v=list1.get(i).getAgencyId();
					hql6=hql6+" or agencyId = '"+v+"'";
				}
				
			}
			
			String str=hql6;
			//获取所有数据
			String queryString="from AgencyAgreement where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<AgencyAgreement> list=query.list();
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
			AgencyAgreement agencyAgreement=(AgencyAgreement)session.get(AgencyAgreement.class, agreementId);
			//删除plane数据
			Transaction trans=session.beginTransaction();
			session.delete(agencyAgreement);//删除数据
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
	 *  根据id查询机构协议信息
	 * @return
	 */
	public AgencyAgreement getAgencyAgreementInforById(int agreementId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要修改的用户数据
			AgencyAgreement p=(AgencyAgreement)session.get(AgencyAgreement.class, agreementId);
			return p;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  查询符合条件的机构
	 * @return
	 */
	public List<Agency> queryByhqlA(String queryString){
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
	 *  查询符合条件的机构协议
	 * @return
	 */
	public List<AgencyAgreement> queryByhql(String queryString){
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
	 *  更新机构协议
	 * @return
	 */
	public boolean updateAgreement(AgencyAgreement c) {
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
	
	/**
	 *  添加协议
	 * @return
	 */
	public int addAgreement(AgencyAgreement c) {
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
}
