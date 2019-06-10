package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Agency;
import com.international.model.AgencyAgreement;
import com.international.model.Exam;
import com.international.model.InternationalStudent;
import com.international.model.Score;

public class ScoreDao {
	SessionFactory sessionFactory;
	public ScoreDao() {
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 *  查询所有成绩信息
	 * @return
	 */
	public List<Score> queryAllScore(String value){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//获取所有数据
			String hql1="studentId like '%"+value+"%'";
			
			//查询学号
			String queryName="from InternationalStudent where "+hql1;
			Query query1=session.createQuery(queryName);
			List<InternationalStudent> list1=query1.list();
			
			String v;
			String hql6="";
			for(int i=0;i<list1.size();i++) {
				if(i==0) {
					v=list1.get(i).getStudentId();
					hql6=hql6+" studentId = '"+v+"'";
				}
				else {
					v=list1.get(i).getStudentId();
					hql6=hql6+" or studentId = '"+v+"'";
				}
				
			}
			
			String str=hql6;
			//获取所有数据
			String queryString="from Score where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个Score的对象
			List list=query.list();//list集合包含Score表里所有数据
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
	 *  分页查询成绩信息
	 * @return
	 */
	public List<Score> queryScore(String value,int pageNo,int pageSize){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//获取所有数据
			String hql1="studentId like '%"+value+"%'";
			
			//查询学号
			String queryName="from InternationalStudent where "+hql1;
			Query query1=session.createQuery(queryName);
			List<InternationalStudent> list1=query1.list();
			
			String v;
			String hql6="";
			for(int i=0;i<list1.size();i++) {
				if(i==0) {
					v=list1.get(i).getStudentId();
					hql6=hql6+" studentId = '"+v+"'";
				}
				else {
					v=list1.get(i).getStudentId();
					hql6=hql6+" or studentId = '"+v+"'";
				}
				
			}
			
			String str=hql6;
			//获取所有数据
			String queryString="from Score where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<Score> list=query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  删除成绩信息
	 * @return
	 */
	public boolean deleteScore(int scoreId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			Score score=(Score)session.get(AgencyAgreement.class, scoreId);
			//删除plane数据
			Transaction trans=session.beginTransaction();
			session.delete(score);//删除数据
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	//根据studentId查询,若在国际班学生中有对应数据则获取过来，智能化操作
	public InternationalStudent queryByStuId(String studentId) {
		System.out.println("从前台传过来的id："+studentId);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			InternationalStudent interStudent = (InternationalStudent)session.get(InternationalStudent.class,studentId);
			//System.out.println(interStudent);
			return interStudent;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
		
	//根据agencyName查询,若在机构中有对应数据则获取过来，智能化操作
	public Agency queryByAgency(String agencyName) {
		System.out.println("从前台传过来的name："+agencyName);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Agency interAgency = (Agency)session.get(Agency.class,agencyName);
			//System.out.println(interStudent);
			return interAgency;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
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
			//执行查询获得的结果,list中的每一个元素代表一个Agency的对象
			List list=query.list();//list集合包含Agency表里所有数据
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
	 *  查询符合条件的考试
	 * @return
	 */
	public List<Exam> queryByhqlE(String queryString){
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
	 *  查询符合条件的成绩
	 * @return
	 */
	public List<Score> queryByhqlS(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			//执行查询获得的结果,list中的每一个元素代表一个Score的对象
			List list=query.list();//list集合包含Score表里所有数据
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
	 *  添加成绩
	 * @return
	 */
	public int addScore(Score c) {
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
	 *  根据id查询考试信息
	 * @return
	 */
	public Score getScoreInforById(int scoreId) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//根据id获取要修改的用户数据
			Score p=(Score)session.get(Score.class, scoreId);
			return p;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	/**
	 *  更新成绩
	 * @return
	 */
	public boolean updateScore(Score c) {
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
