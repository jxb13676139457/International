package com.international.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Admin;
import com.international.model.InternationalStudent;
import com.international.model.News;
import com.international.model.Notice;
import com.international.model.Teacher;
import com.opensymphony.xwork2.ActionContext;

public class UserDao {

	SessionFactory sessionFactory;
	Admin admin;
	Teacher teacher;
	InternationalStudent student;
	
	public UserDao(){}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public InternationalStudent getStudent() {
		return student;
	}
	public void setStudent(InternationalStudent student) {
		this.student = student;
	}

	//检验后台登录
	public Admin checkLogin(Admin admin){
		Session session=null;
		try{
			session=sessionFactory.openSession();
			String hql="from Admin where adminId=? and password=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, admin.getAdminId());
			query.setParameter(1, admin.getPassword());
			//query.setParameter(2,admin.getType());
			List<Admin> list=query.list();
			//System.out.println(list);
			if(list.size()>0){
				//System.out.println(list.get(0).getUserName());
				return list.get(0);
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
	
	//修改管理员信息
	public boolean updateAdminInfo(Admin admin,String oldPwd,String newPwd,String reqPwd,String userName) {
		Session session = null;
		Transaction tran;
		System.out.println(admin.getAdminId()+","+oldPwd+","+newPwd+","+userName);
		try {
			session = sessionFactory.openSession();
			String hql = "from Admin where adminId=? and password=?";
			Query query = session.createQuery(hql);
			//System.out.println(admin.getAdminId()+","+oldPwd);
			query.setParameter(0,admin.getAdminId());
			query.setParameter(1,oldPwd);
			List<Admin> list = query.list();
			//System.out.println(list);
			if(list.size()==0) {
				return false;
			}else {
				admin = list.get(0);
				if(reqPwd.equals(newPwd)) {   //两次密码输入一致
					if(admin.getPassword().equals(oldPwd)) {   //旧密码输入正确
						System.out.println("旧密码输入正确");
						admin.setPassword(newPwd);
						admin.setUserName(userName);
						tran = session.beginTransaction();
						session.update(admin);
						tran.commit();
						return true;
					}else {
						System.out.println("旧密码输入失败");
						return false;
					}
				}else {
					System.out.println("两次密码输入不一致");
					return false;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//前台用户修改密码
	public boolean updatePassword(String id,String password,String newPassword,String reqPassword) {
		Session session = null;
		Transaction tran;
		System.out.println(id+","+password+","+newPassword+","+reqPassword);
		try {
			session = sessionFactory.openSession();
			student = (InternationalStudent) session.get(InternationalStudent.class,id);
			teacher = (Teacher) session.get(Teacher.class,id);
			System.out.println(student+","+teacher);
			tran = session.beginTransaction();
			if(newPassword.equals(reqPassword)) {  //两次密码输入一致
				if(student!=null && teacher==null) {
					if(student.getPassword().equals(password)) {  //确认旧密码正确
						System.out.println("旧密码输入正确");
						student.setPassword(newPassword);
						session.update(student);
					}else {
						System.out.println("旧密码输入错误");
						return false;
					}
				}else if(student==null && teacher!=null) {
					if(teacher.getPassword().equals(password)) {
						System.out.println("旧密码输入正确");
						teacher.setPassword(newPassword);
						session.update(teacher);
					}else {
						System.out.println("旧密码输入错误");
						return false;
					}
				}
				tran.commit();
				return true;
			}else {
				System.out.println("两次密码输入不一致");
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//查询所有管理员的信息
	public List<Admin> queryAdmins(String value){
		Session session = null;
		try{
			String hql = "";
			session = sessionFactory.openSession();
			String hql1="adminId like '%"+value+"%'";		
			String hql2="userName like '%"+value+"%'";			
			String hql3="password like '%"+value+"%'";
			String hql4="sex like '%"+value+"%'";
			String hql5="type like '%"+value+"%'";
			hql="from Admin where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5;
			Query query = session.createQuery(hql);
			List list = query.list();
			if(list.size()>0){
				return list;
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	//分页查询需要显示的数据(每次最多4条记录)
	public List<Admin> queryByPage(String value,int pageNo,int pageSize){
		Session session = null;
		try{
			String hql = "";
			session = sessionFactory.openSession();
			String hql1="adminId like '%"+value+"%'";		
			String hql2="userName like '%"+value+"%'";			
			String hql3="password like '%"+value+"%'";
			String hql4="sex like '%"+value+"%'";
			String hql5="type like '%"+value+"%'";
			hql="from Admin where "+ hql1 +" or "+ hql2 +" or "+ hql3+" or "+ hql4+" or "+ hql5;
			Query query = session.createQuery(hql);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize);
			//每次最多4条记录
			List<Admin> list = query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	//加入后台操作员
	public int addOperator(Admin admin) {
		System.out.println(admin.getAdminId());
		int count = 0;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			count = Integer.valueOf(session.save(admin).toString());
			tran.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return count;
	}
	
	//删除操作员
	public boolean deleteOperator(String adminId){
		System.out.println("前台传过来的id："+adminId);
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			//根据id获取Admin对象 
			Admin admin = (Admin)session.get(Admin.class,adminId);
			System.out.println(admin);
			session.delete(admin);
			tran.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}
	
	//根据adminId查询选中的操作员，以供修改
	public Admin queryById(String adminId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Admin admin = (Admin)session.get(Admin.class,adminId);
			return admin;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
	//修改操作员
	public boolean updateOperator(String adminId,Admin newAdmin) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Admin oldAdmin = (Admin)session.get(Admin.class,adminId);
			oldAdmin.setPassword(newAdmin.getPassword());
			oldAdmin.setUserName(newAdmin.getUserName());
			oldAdmin.setSex(newAdmin.getSex());
			oldAdmin.setType(newAdmin.getType());
			Transaction trans = session.beginTransaction();
			//新修改的数据保存到数据库中
			session.saveOrUpdate(oldAdmin);
			trans.commit();
			//System.out.println(newAdmin.getSex()+","+oldAdmin.getSex());
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	//前台登录验证
	public boolean validLogin(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			List list=query.list();
			if(list.size()>0)
				return true;
			else{
				return false;
				}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	//前台学生获取登录信息
	public List<InternationalStudent> studentLogin(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			List list=query.list();
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
		
	//前台其他获取登录信息   不需要了
	/*public List<Admin> otherLogin(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			List list=query.list();
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
	}*/
	
	//前台老师获取登录信息
	public List<Teacher> teacherLogin(String queryString){
		Session session=null;
		try {
			session=sessionFactory.openSession();
			//创建查询
			Query query=session.createQuery(queryString);
			List list=query.list();
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
		
	//前台获取新闻(每次最多5条记录)
	public List<News> queryByNews(String queryString){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			//创建查询
			Query query = session.createQuery(queryString);
			//每次获取第一条数据的索引
			if(query.list().size()<=5)//新闻条数小于5
				query.setFirstResult(0);
			else
				query.setFirstResult((query.list().size()-5)); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(5);
			//每次最多5条记录
			List<News> list = query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
		
	//前台获取通知(每次最多5条记录)
	public List<Notice> queryByNotice(String queryString){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			//创建查询
			Query query = session.createQuery(queryString);
			//每次获取第一条数据的索引
			if(query.list().size()<=5)//通知条数小于5
				query.setFirstResult(0);
			else
				query.setFirstResult((query.list().size()-5)); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(5);
			//每次最多5条记录
			List<Notice> list = query.list();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
}
