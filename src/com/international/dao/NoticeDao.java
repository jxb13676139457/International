package com.international.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.international.model.InternationalClass;
import com.international.model.Notice;

public class NoticeDao {

	SessionFactory sessionFactory;
	
	public NoticeDao() {}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public Notice getNoticeInforById(int id){
		System.out.println("getNoticeInforById方法打印NoticeId = "+id);
		Session session=null;	
		Notice interNotice=null;
		try{	
			session=sessionFactory.openSession();	
			String hql="from Notice where id=?";			
			Query query =session.createQuery(hql);		
			query.setParameter(0, id);
			List<Notice> list=query.list();			 
			if(list!=null && list.size()>0){
				interNotice=list.get(0);				
				return interNotice;
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
	
	
	public List<Notice> queryAllNotice(){
		System.out.println("Notice被调用");
		
		Session session=null;
		try {
			session=sessionFactory.openSession();
			System.out.println("session = "+session);
			//获取所有数据
			String queryString="from Notice";			
			//创建查询
			Query query=session.createQuery(queryString);
			System.out.println("query = "+query);
			//执行查询获得的结果,list中的每一个元素代表一个College的对象
			List list=query.list();//list集合包含InternationalClass表里所有数据
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
	
	
	public List<Notice> queryNotice(String value,int pageNo,int pageSize){
		Session session=null;
		try {
			//hql语句
			session=sessionFactory.openSession();
			/*String hql1 = "title like '%"+value+"%'";*/
			String hql2 = "convert(varchar,time,120) like '%"+value+"%'";
			/*String hql3 = "content like '%"+value+"%'";
			String hql4 = "source like '%"+value+"%'";
			String hql5 = "author like '%"+value+"%'";*/
			
			/*String str=hql1+" or "+hql2+" or "+hql3+" or "+hql4+" or "+hql5;*/
			String str=hql2;
			//获取所有数据
			String queryString="from Notice where "+str;
			//创建查询
			Query query=session.createQuery(queryString);
			//每次获取第一条数据的索引
			query.setFirstResult((pageNo-1)*pageSize); //设置这一页显示的第一条记录的索引
			//这一页显示的记录个数
			query.setMaxResults(pageSize); 

			//每次最多6条记录
			List<Notice> list=query.list();
			System.out.println("list = "+list);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}
	
	
	public boolean insertNotice(Notice newNotice){
		Session session = null;
		System.out.println("insertNotice方法被调用");
		try{
			session=sessionFactory.openSession();
			Transaction trans=session.beginTransaction();
			Integer.parseInt(session.save(newNotice).toString());
			trans.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public boolean deleteNotice(int NoticeId) {
		Session session=null;
		try{
			System.out.println("NoticeId = "+NoticeId);
			session=sessionFactory.openSession();
			//根据id获取要删除的用户
			Notice classes=(Notice)session.get(Notice.class, NoticeId);
			//删除plane数据
			Transaction trans=session.beginTransaction();
			session.delete(classes);//删除数据
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{//关闭session
			session.close();//关闭Session
		}
	}

	public boolean updateNoticeByID(int id, Notice newNotice) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Notice oldNotice=(Notice)session.get(Notice.class, id);
			
			oldNotice.setTitle(newNotice.getTitle());;
			oldNotice.setTime(newNotice.getTime());
			oldNotice.setSource(newNotice.getSource());
			oldNotice.setAuthor(newNotice.getAuthor());

			Transaction trans=session.beginTransaction();
			session.update(oldNotice);
			trans.commit();
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}	
	}
	
	
}
