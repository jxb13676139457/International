package com.international.frontground.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.international.model.AgencyAgreement;
import com.international.model.Score;

public class PriEngAgreeDao {
	SessionFactory sessionFactory;
	
	public PriEngAgreeDao() {
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//查询雅思协议信息
	public List<AgencyAgreement> queryAgencyAgreement(String queryString) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			// 创建查询
			Query query = session.createQuery(queryString);
			List list = query.list();
			if (list.size() > 0)
				return list;
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {// 关闭session
			session.close();// 关闭Session
		}
	}
}
