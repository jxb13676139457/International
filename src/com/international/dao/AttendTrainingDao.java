package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Agency;
import com.international.model.AttendTraining;
import com.international.model.InternationalClass;
import com.international.model.InternationalStudent;
import com.international.model.OverseasStudent;
import com.international.model.Training;

public class AttendTrainingDao {
	SessionFactory sessionFactory;
	AttendTraining attendTraining;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public AttendTraining getAttendTraining() {
		return attendTraining;
	}
	public void setAttendTraining(AttendTraining attendTraining) {
		this.attendTraining = attendTraining;
	}
	
	//查询学生参与的所有雅思培训计划
	public List<AttendTraining> queryAttends(String value){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			//以下是根据子查询来实现多表搜索
			//a.classId=b.classId and     a.agencyId=b.agencyId and
			String hql1 = "studentId in (select studentId from InternationalStudent where studentId like '%"
					+value+"%' or studentName like '%"+value+"%')";
			String hql2 = "trainingId in (select trainingId from Training where startTime='"+value+"' or endTime ='"+value+"' or courseHours like '%"+value+
					"%' or courseFee like '%"+value+"%')";
			//System.out.println("hql1:"+hql1);
			//System.out.println("hql2:"+hql2);
			//拼接hql语句
			hql = "from AttendTraining where "+ hql1 +" or "+ hql2;
			//System.out.println("拼接的hql:"+hql);
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("查询到的学生参与的全部培训计划："+list);
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
	public List<AttendTraining> queryByPage(String value,int pageNo,int pageSize){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			//以下是根据子查询来实现多表搜索
			String hql1 = "studentId in (select studentId from InternationalStudent where studentId like '%"
					+value+"%' or studentName like '%"+value+"%')";
			String hql2 = "trainingId in (select trainingId from Training where startTime='"+value+"' or endTime ='"+value+"' or courseHours like '%"+value+
					"%' or courseFee like '%"+value+"%')";
			//System.out.println("hql1:"+hql1);
			//System.out.println("hql2:"+hql2);
			//拼接hql语句
			hql = "from AttendTraining where "+ hql1 +" or "+ hql2;
			//System.out.println("拼接的hql:"+hql);
			Query query = session.createQuery(hql);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			List<AttendTraining> list = query.list();
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
	
	//删除学生参与培训计划的记录
	public boolean delAttendTraining(int attendId) {
		System.out.println("从前台传过来的id："+attendId);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//根据id获取要删除的学生参与培训计划记录
			AttendTraining attendTraining = (AttendTraining)session.get(AttendTraining.class,attendId);
			//System.out.println(interStudent);
			session.delete(attendTraining);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	/*//根据attendId查询选中的学生参与培训计划的记录，以供修改   不符合需求所以不做修改了
	public AttendTraining queryById(int attendId) {
		System.out.println("从前台传过来的id："+attendId);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			AttendTraining attendTraining = (AttendTraining)session.get(AttendTraining.class,attendId);
			//System.out.println(interStudent);
			return attendTraining;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}*/
	
	//根据studentId查询,从国际班学生中把对应数据则获取过来，智能化操作
	public InternationalStudent queryByStuId(String studentId) {
		System.out.println("从前台传过来的id："+studentId);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			InternationalStudent interStudent = (InternationalStudent)session.get(InternationalStudent.class,studentId);
			return interStudent;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//查找培训计划对象
	public Training queryByTraining(String startTime,String endTime,int courseHours,int courseFee) {
		System.out.println("从前台传过来的startTime："+startTime);
		System.out.println("从前台传过来的endTime："+endTime);
		System.out.println("从前台传过来的courseHours："+courseHours);
		System.out.println("从前台传过来的courseFee："+courseFee);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from Training where startTime='"+startTime+"' and endTime='"+endTime+"' and courseHours="+courseHours+" and courseFee="+courseFee;
			System.out.println("hql语句："+hql);
			Query query = session.createQuery(hql);
			Training training = (Training) query.list().get(0);
			return training;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//根据培训机构名字获取培训计划对象
	public List<Training> queryByAgencyName(String agencyName){
		Session session = null;
		String hql = "from Training where agencyId=(select agencyId from Agency where agencyName='"+agencyName+"')";
		//System.out.println("hql语句"+hql);
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<Training> list = query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//添加学生参与培训计划
	public boolean addAttend(AttendTraining attendTraining) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.save(attendTraining);
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
