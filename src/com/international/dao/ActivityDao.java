package com.international.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.College;
import com.international.model.CollegeActivity;
import com.opensymphony.xwork2.ActionContext;

public class ActivityDao {
	SessionFactory sessionFactory;
	public ActivityDao() {
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/*
	 *维护活动信息
	 * */
	
	/**
	 *  查询所有活动信息
	 * @return
	 */
	public List<CollegeActivity> queryAllActivity(String title,String time,String name){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//hql语句
			String hql1="title like '%"+title+"%'";
			String hql2="convert(varchar,time,120) like '%"+time+"%'";
			//String hql3="collegeName like '%"+name+"%'";
			String hql3="";
			if(title.equals("")&&time.equals(""))
				hql3="collegeName like '%"+name+"%'";
			else if(!name.equals(""))
				hql3="collegeName like '%"+name+"%'";
			else
				hql3="collegeName = '"+name+"'";
			//查询名字
			String queryName="from College where "+hql3;
			Query query1=session.createQuery(queryName);
			List<College> list1=query1.list();
			/*if (list1.isEmpty()) {
				return null;
			}*/
			//int value=0;
			//int value=list1.get(0).getCollegeId();
			//String hql4="coll like '%"+value+"%'";
			int value;
			String hql4="";
			if (!list1.isEmpty()||!title.equals("")||!time.equals("")) {
				for(int i=0;i<list1.size();i++) {
					if(i==0) {
						value=list1.get(i).getCollegeId();
						hql4=hql4+"and collegeId = '"+value+"'";
					}
					else {
						value=list1.get(i).getCollegeId();
						hql4=hql4+" or collegeId = '"+value+"'";
					}
				}
				
			
			
			String str=hql1+" and "+hql2+hql4;
			//获取所有数据
			String queryString="from CollegeActivity where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个CollegeActivity的对象
			List list=query.list();//list集合包含CollegeActivity表里所有数据
			if(list.size()>0)
				return list;
			else{
				return null;
				}
			}
			else
				return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	/**
	 *  分页查询活动信息
	 * @return
	 */
	public List<CollegeActivity> queryActivity(String title,String time,String name,int pageNo,int pageSize){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//hql语句
			String hql1="title like '%"+title+"%'";
			String hql2="convert(varchar,time,120) like '%"+time+"%'";
			//String hql3="collegeName like '%"+name+"%'";
			String hql3="";
			if(title.equals("")&&time.equals(""))
				hql3="collegeName like '%"+name+"%'";
			else if(!name.equals(""))
				hql3="collegeName like '%"+name+"%'";
			else
				hql3="collegeName = '"+name+"'";
			
			//查询名字
			String queryName="from College where "+hql3;
			Query query1=session.createQuery(queryName);
			List<College> list1=query1.list();
			
			int value;
			String hql4="";
			if (!list1.isEmpty()||!title.equals("")||!time.equals("")) {
				for (int i = 0; i < list1.size(); i++) {
					if (i == 0) {
						value = list1.get(i).getCollegeId();
						hql4 = hql4 + "and collegeId = '" + value + "'";
					} else {
						value = list1.get(i).getCollegeId();
						hql4 = hql4 + " or collegeId = '" + value + "'";
					}

				}
			
			//int value=list1.get(0).getCollegeId();
			//String hql4="coll like '%"+value+"%'";
			
			String str=hql1+" and "+hql2+hql4;
			//获取所有数据
			String queryString="from CollegeActivity where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<CollegeActivity> list=query.list();
			return list;
			}
			else
				return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  删除活动信息
	 * @return
	 */
	public boolean deleteActicity(int activityId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			CollegeActivity collegeActivity=(CollegeActivity)session.get(CollegeActivity.class, activityId);
			//删除plane数据
			Transaction trans=session.beginTransaction();
			session.delete(collegeActivity);//删除数据
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
	 *  查询符合条件的活动
	 * @return
	 */
	public List<CollegeActivity> queryByhql(String queryString,String h){
		Session session=null;
		//String hql4="";
		try {
			session=sessionFactory.openSession();
			//查询名字
			String queryName="from College where collegeName="+"'"+h+"'";
			Query query1=session.createQuery(queryName);
			List<College> list1=query1.list();
			
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
			//int value=list1.get(0).getCollegeId();
			//String hql4=" and collegeId = '"+value+"'";
			queryString=queryString +hql4;
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个CollegeActivity的对象
			List<CollegeActivity> list=query.list();//list集合包含CollegeActivity表里所有数据
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
	 *  添加活动
	 * @return
	 */
	public int addActivity(CollegeActivity c) {
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
	 *  根据id查询活动信息
	 * @return
	 */
	public CollegeActivity getCollegeActivityInforById(int activityId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要修改的用户数据
			CollegeActivity p=(CollegeActivity)session.get(CollegeActivity.class, activityId);
			return p;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  更新活动
	 * @return
	 */
	public boolean updateActivity(CollegeActivity c) {
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
