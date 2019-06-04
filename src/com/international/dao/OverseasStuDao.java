package com.international.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.College;
import com.international.model.InternationalClass;
import com.international.model.InternationalStudent;
import com.international.model.OverseasStudent;
import com.opensymphony.xwork2.ActionContext;

public class OverseasStuDao {
	
	SessionFactory sessionFactory;
	private OverseasStudent oversea;
	
	public OverseasStuDao() {
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public OverseasStudent getOversea() {
		return oversea;
	}
	
	public void setOversea(OverseasStudent oversea) {
		this.oversea = oversea;
	}
	
	//查询所有出国学生信息
	public List<OverseasStudent> queryOverseas(String value){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "studentId like '%"+value+"%'";
			String hql2 = "studentName like '%"+value+"%'";
			String hql3 = "sex like '%"+value+"%'";
			String hql4 = "outTime like '%"+value+"%'";
			String hql5 = "outMajor like '%"+value+"%'";
			String hql6= "outMajor like '%"+value+"%'";
			String hql7 = "subsidy like '%"+value+"%'";
			//以下是根据子查询来实现多表搜索
			String hql8 = "classId in (select classId from InternationalClass where className like '%"+value+"%' or grade like '%"+value+"%' or major like '%"+value+"%')";
			String hql9 = "collegeId in (select collegeId from College where collegeName like '%"+value+"%' or type like '%"+value+"%')";
			//拼接hql语句
			hql = "from OverseasStudent where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5+" or "+ hql6+" or "+ hql7+" or "+ hql8+" or "+ hql9;
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("查询到的全部出国学生："+list);
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
	public List<OverseasStudent> queryByPage(String value,int pageNo,int pageSize){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "studentId like '%"+value+"%'";
			String hql2 = "studentName like '%"+value+"%'";
			String hql3 = "sex like '%"+value+"%'";
			String hql4 = "outTime like '%"+value+"%'";
			String hql5 = "outMajor like '%"+value+"%'";
			String hql6= "outMajor like '%"+value+"%'";
			String hql7 = "subsidy like '%"+value+"%'";
			//以下是根据子查询来实现多表搜索
			String hql8 = "classId in (select classId from InternationalClass where className like '%"+value+"%' or grade like '%"+value+"%' or major like '%"+value+"%')";
			String hql9 = "collegeId in (select collegeId from College where collegeName like '%"+value+"%' or type like '%"+value+"%')";
			//拼接hql语句
			hql = "from OverseasStudent where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5+" or "+ hql6+" or "+ hql7+" or "+ hql8+" or "+ hql9;
			Query query = session.createQuery(hql);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			List<OverseasStudent> list = query.list();
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
	
	//删除出国学生信息
	public boolean delOverseas(String studentId) {
		System.out.println("从前台传过来的id："+studentId);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//根据id获取要删除的出国学生对象
			OverseasStudent oversea = (OverseasStudent)session.get(OverseasStudent.class,studentId);
			//System.out.println(interStudent);
			session.delete(oversea);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//根据studentId查询选中的出国学生，以供修改
	public OverseasStudent queryById(String studentId) {
		System.out.println("从前台传过来的id："+studentId);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			OverseasStudent oversea = (OverseasStudent)session.get(OverseasStudent.class,studentId);
			//System.out.println(interStudent);
			return oversea;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//根据出国学生班级名称查找班级对象  
	public InternationalClass queryByClassName(String className){
		System.out.println("出国学生的班级："+className);
		Session session = null;
		String hql = "from InternationalClass where className='"+className+"'";
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			InternationalClass interClass = (InternationalClass) query.list().get(0);
			return interClass;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//根据出国学生出国院校名称查找班级对象  
	public College queryByCollegeName(String collegeName){
		System.out.println("出国学生的出国院校："+collegeName);
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
	
	//修改国际班学生信息  
	public boolean updateStudent(OverseasStudent newOversea) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//直接修改一整个对象
			session.saveOrUpdate(newOversea);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
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
		
	//添加出国学生信息    
	public boolean addStudent(OverseasStudent oversea) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.save(oversea);
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
