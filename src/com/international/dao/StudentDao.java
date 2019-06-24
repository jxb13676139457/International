package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Admin;
import com.international.model.InternationalClass;
import com.international.model.InternationalStudent;

public class StudentDao {
	
	SessionFactory sessionFactory;
	InternationalStudent interStudent;
	
	public StudentDao() {
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public InternationalStudent getInterStudent() {
		return interStudent;
	}
	public void setInterStudent(InternationalStudent interStudent) {
		this.interStudent = interStudent;
	}
	
	//查询所有国际班学生信息
	public List<InternationalStudent> queryInterStudents(String value){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "studentId like '%"+value+"%'";
			String hql2 = "studentName like '%"+value+"%'";
			String hql3 = "sex like '%"+value+"%'";
			String hql4 = "password like '%"+value+"%'";
			String hql5 = "status like '%"+value+"%'";
			String hql6 = "classId in (select classId from InternationalClass where className like '%"+value+"%' or grade like '%"+value+"%' or major like '%"+value+"%')";
			hql = "from InternationalStudent where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5+" or "+ hql6;
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("查询到的全部国际班学生："+list);
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
	public List<InternationalStudent> queryByPage(String value,int pageNo,int pageSize){
		Session session = null;
		try {
			String hql = "";
			session = sessionFactory.openSession();
			String hql1 = "studentId like '%"+value+"%'";
			String hql2 = "studentName like '%"+value+"%'";
			String hql3 = "sex like '%"+value+"%'";
			String hql4 = "password like '%"+value+"%'";
			String hql5 = "status like '%"+value+"%'";
			String hql6 = "classId in (select classId from InternationalClass where className like '%"+value+"%' or grade like '%"+value+"%' or major like '%"+value+"%')";
			hql = "from InternationalStudent where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5+" or "+ hql6;
			Query query = session.createQuery(hql);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			List<InternationalStudent> list = query.list();
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
	
	//删除国际班学生信息
	public boolean delInterStudent(String studentId) {
		System.out.println("从前台传过来的id："+studentId);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//根据id获取要删除的国际班学生对象
			InternationalStudent interStudent = (InternationalStudent)session.get(InternationalStudent.class,studentId);
			//System.out.println(interStudent);
			session.delete(interStudent);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//根据studentId查询选中的国际班学生，以供修改
	public InternationalStudent queryById(String studentId) {
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
	
	//根据国际班级名称查找国际班对象  
	public InternationalClass queryByClassName(String className){
		//System.out.println("学生的班级："+className);
		//这一句是为了截取ajax传参过来的时候参数后面加多了", "这两个字符
		//className = className.substring(0,className.length()-2);
		System.out.println("传过来的班级名称："+className);
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
	
	//根据年级查找国际班对象    以便界面动态显示
	public List<InternationalClass> queryByGrade(String grade){
		Session session = null;
		String hql = "from InternationalClass where grade='"+grade+"'";
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<InternationalClass> list = query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//根据年级和专业查找国际班对象  以便界面动态显示
	public List<InternationalClass> queryByMajor(String grade,String major){
		Session session = null;
		String hql1="major = '"+major+"'";
		String hql2="grade = '"+grade+"'";
		String hql="from InternationalClass where " + hql1 +" and "+  hql2 + "order by className";
		System.out.println("hql语句："+hql);
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<InternationalClass> list = query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//修改国际班学生信息  
	public boolean updateStudent(InternationalStudent newStudent) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//直接修改一整个对象
			session.saveOrUpdate(newStudent);
			tran.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//添加国际班学生信息    
	public boolean addStudent(InternationalStudent interStudent) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.save(interStudent);
			tran.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}
	
	//根据国际班级名称查找国际班对象  
	public int queryByClassId(String className){
		//System.out.println("学生的班级："+className);
		//这一句是为了截取ajax传参过来的时候参数后面加多了", "这两个字符
		//className = className.substring(0,className.length()-2);
		System.out.println("传过来的班级名称："+className);
		Session session = null;
		String hql = "select classId from InternationalClass where className='"+className+"'";
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			InternationalClass interClass = (InternationalClass) query.list().get(0);
			return interClass.getClassId();
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			session.close();
		}
	}
	
	/*//根据学号查询数据库是否有这个国际班学生
	public boolean queryByStudentId(String stuId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "from InternationalStudent where studentId="+stuId;
			Query query = session.createQuery(hql);
			List list = query.list();
			if(list.size()>0) {
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}*/
}
