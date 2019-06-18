package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Agency;
import com.international.model.College;
import com.international.model.CollegeActivity;
import com.international.model.Training;

public class TrainingDao {

	SessionFactory sessionFactory;
	
	public TrainingDao() {}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Training> queryAllTraining() {
		System.out.println("queryAllTraining被调用");
		
		Session session=null;
		try {
			session=sessionFactory.openSession();
			System.out.println("session = "+session);

			//获取所有数据
			String queryString="from Training";		
			//创建查询
			Query query=session.createQuery(queryString);
			System.out.println("query = "+query);
			//执行查询获得的结果
			List list=query.list();
			System.out.println("****queryAllTraining方法的Training集合 = "+list+"****");
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

	public List<Training> queryTraining(String searchStartTime, String searchEndTime, int pageNo, int pageSize) {
		System.out.println("queryTraining被调用");
		Session session=null;
		try {
			//hql语句
			session=sessionFactory.openSession();
			
			/*String hql1 = "startTime like '%"+searchStartTime+"%'";
			String hql2 = "endTime like '%"+searchEndTime+"%'";*/
			String hql1="convert(varchar,startTime,120) like '%"+searchStartTime+"%'";
			String hql2="convert(varchar,endTime,120) like '%"+searchEndTime+"%'";
			
			String str = hql1+" and "+hql2;
			//获取所有数据
			String queryString="";
			/*if(!searchStartTime.equals("") && searchStartTime!=null && !searchEndTime.equals("") && searchEndTime!=null) {
				queryString="from Training where "+str;
			}else if(searchStartTime.equals("") || searchStartTime==null) {
				queryString="from Training where "+hql2;
			}else if(searchEndTime.equals("") || searchEndTime==null) {
				queryString="from Training where "+hql1;
			}*/
			queryString = "from Training where "+str;
	
			System.out.println("========queryString = "+queryString+" ========");
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<Training> list=query.list();
			System.out.println("list = "+list);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}

	public List<Training> queryByHql(String queryString,String value) {
		Session session=null;
		//String hql4="";
		try {
			session=sessionFactory.openSession();
			//查询名字
			String queryName="from Agency where agencyName="+"'"+value+"'";
			Query query=session.createQuery(queryName);
			List<Agency> list=query.list();
			System.out.println("AgencyList = "+list);
			
			int str;
			String hql4="";
			for(int i=0;i<list.size();i++) {
				if(i==0) {
					str=list.get(i).getAgencyId();
					hql4=hql4+" and agencyId = '"+str+"'";
				}
				else {
					str=list.get(i).getAgencyId();
					hql4=hql4+" or agencyId = '"+str+"'";
				}
				
			}
			System.out.println("hql4 = "+hql4);
			queryString += hql4;
			Query query1=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个CollegeActivity的对象
			List<Training> list1=query1.list();//list集合包含CollegeActivity表里所有数据
			System.out.println("TrainingList = "+list1);
			if(list1.size()>0)
				return list1;
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

	public int addTraining(Training training) {
		System.out.println("addTraining方法被调用");
		Session session=null;
		int num=0; //受影响的行数
		
		try{
			//获得Session对象
			session=sessionFactory.openSession();
			//开始事物
			Transaction trans=session.beginTransaction();
			//保存数据,返回受影响的行数
			num=Integer.parseInt(session.save(training).toString());
			//提交事物,才是真正写入数据库表中
			trans.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return num;
	}

	public boolean deleteTraining(int trainingId) {
		Session session=null;
		try{
			System.out.println("trainingId = "+trainingId);
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			Training classes=(Training)session.get(Training.class, trainingId);
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

	public Training getTrainingInforById(int id) {
		System.out.println("getTrainingInforById方法打印trainingId = "+id);
		Session session=null;	
		Training interNotice=null;
		try{	
			session=sessionFactory.openSession();	
			String hql="from Training where id=?";			
			Query query =session.createQuery(hql);		
			query.setParameter(0, id);
			List<Training> list=query.list();			 
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

	public boolean updateTraining(int id, Training training) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//保存数据回数据库
			Transaction trans=session.beginTransaction();
			//调用保存更新方法
			session.saveOrUpdate(training);
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{//关闭session
			session.close();//关闭Session
		}	
		/*Session session=null;
		try{
			session=sessionFactory.openSession();
			Training oldTraining=(Training)session.get(Training.class, id);
			System.out.println("updateTraining方法中的Id = "+id);
			System.out.println("oldTraining对象种的Id = "+training.getTraningId());
			oldTraining.setAgencies(training.getAgencies());
			oldTraining.setStartTime(training.getStartTime());
			oldTraining.setEndTime(training.getEndTime());
			oldTraining.setCourseFee(training.getCourseFee());
			oldTraining.setCourseHours(training.getCourseHours());
			System.out.println("oldTraining.getAgencies().getAgencyId()中的Id = "+oldTraining.getAgencies().getAgencyId());
			Transaction trans=session.beginTransaction();
			session.update(oldTraining);
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}	
		*/
	}

	
	
}
