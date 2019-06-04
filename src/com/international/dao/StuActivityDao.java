package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.College;
import com.international.model.InternationalClass;
import com.international.model.InternationalStudent;
import com.international.model.OverseasStudent;
import com.international.model.StudentActivity;

public class StuActivityDao {

	SessionFactory sessionFactory;
	private StudentActivity studentActivity;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public StudentActivity getStudentActivity() {
		return studentActivity;
	}
	public void setStudentActivity(StudentActivity studentActivity) {
		this.studentActivity = studentActivity;
	}
	
	//查询所有学生夏令营活动信息
	public List<StudentActivity> queryStuActivities(String value){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "id like '%"+value+"%'";
			String hql2 = "studentId like '%"+value+"%'";
			String hql3 = "studentName like '%"+value+"%'";
			String hql4 = "activityName like '%"+value+"%'";
			String hql5 = "startTime like '%"+value+"%'";
			String hql6 = "endTime like '%"+value+"%'";
			String hql7 = "Fee like '%"+value+"%'";
			String hql8 = "Currency like '%"+value+"%'";
			String hql9 = "collegeId in (select collegeId from College where collegeName like '%"+value+"%')";
			hql = "from StudentActivity where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5+" or "+ hql6+" or "+ hql7+" or "+ hql8+" or "+ hql9;
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("查询到的全部学生夏令营活动："+list);
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
	public List<StudentActivity> queryByPage(String value,int pageNo,int pageSize){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "id like '%"+value+"%'";
			String hql2 = "studentId like '%"+value+"%'";
			String hql3 = "studentName like '%"+value+"%'";
			String hql4 = "activityName like '%"+value+"%'";
			String hql5 = "startTime like '%"+value+"%'";
			String hql6 = "endTime like '%"+value+"%'";
			String hql7 = "Fee like '%"+value+"%'";
			String hql8 = "Currency like '%"+value+"%'";
			String hql9 = "collegeId in (select collegeId from College where collegeName like '%"+value+"%')";
			hql = "from StudentActivity where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5+" or "+ hql6+" or "+ hql7+" or "+ hql8+" or "+ hql9;
			Query query = session.createQuery(hql);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			List<StudentActivity> list = query.list();
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
	
	//删除学生活动信息
	public boolean delStuActivity(int id) {
		System.out.println("从前台传过来的id："+id);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//根据id获取要删除的学生活动对象
			StudentActivity stuActivity = (StudentActivity)session.get(StudentActivity.class,id);
			session.delete(stuActivity);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//根据id查询选中的学生活动信息，以供修改
	public StudentActivity queryById(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StudentActivity stuActivity = (StudentActivity)session.get(StudentActivity.class,id);
			return stuActivity;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//根据学生活动的国外院校名称查找国外院校对象  
	public College queryByCollegeName(String collegeName){
		System.out.println("学生活动的国外院校："+collegeName);
		Session session = null;
		String hql = "from College where collegeName='"+collegeName+"'";
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			College college = (College) query.list().get(0);
			return college;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
		
	//修改学生活动信息  
	public boolean updateStuActivity(StudentActivity newStudentActivity) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//直接修改一整个对象
			session.saveOrUpdate(newStudentActivity);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//添加学生活动信息    
	public boolean addStuActivity(StudentActivity studentActivity) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.save(studentActivity);
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
