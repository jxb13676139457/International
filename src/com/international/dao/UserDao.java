package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

	public boolean checkLogin(Admin admin){
		Session session=null;
		try{
			session=sessionFactory.openSession();
			String hql="from Admin where adminId=? and password=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, admin.getAdminId());
			query.setParameter(1, admin.getPassword());
			//System.out.println(hql);
			List<Admin> list=query.list();
			System.out.println(list);
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}
}
