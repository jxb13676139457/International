package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.Admin;

public class UserDao {

	SessionFactory sessionFactory;
	
	public UserDao(){}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
		//boolean flag = false;
		System.out.println(admin.getAdminId()+","+oldPwd+","+newPwd+","+userName);
		try {
			session = sessionFactory.openSession();
			String hql = "from Admin where adminId=? and password=?";
			Query query = session.createQuery(hql);
			//System.out.println(admin.getAdminId()+","+oldPwd);
			query.setParameter(0,admin.getAdminId());
			query.setParameter(1,oldPwd);
			List<Admin> list = query.list();
			System.out.println(list);
			if(list.size()==0) {
				return false;
			}else {
				admin = list.get(0);
				//System.out.println(reqPwd);
				if(reqPwd.equals(newPwd)) {   //两次密码输入一致
					System.out.println("密码一致");
					admin.setPassword(newPwd);
					admin.setUserName(userName);
					tran = session.beginTransaction();
					session.update(admin);
					tran.commit();
					return true;
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
}
