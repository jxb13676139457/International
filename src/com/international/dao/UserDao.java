package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.international.model.User;

public class UserDao {

	SessionFactory sessionFactory;
	
	public UserDao(){}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean checkLogin(User users){
		Session session=null;
		try{
			session=sessionFactory.openSession();
			String hql="from User where UserName=? and Password=?";
			Query query=session.createQuery(hql);
			System.out.println("query = "+query);
			System.out.println("session = "+session);
			query.setParameter(0, users.getUserName());
			query.setParameter(1, users.getPassword());
			List<User> list=query.list();
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();;
			return false;
		}finally{
			session.close();
		}
	}
}
