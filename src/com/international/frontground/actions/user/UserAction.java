package com.international.frontground.actions.user;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.international.common.ajaxAction;
import com.international.dao.UserDao;
import com.international.model.Admin;
import com.international.model.InternationalStudent;
import com.international.model.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class UserAction extends ActionSupport{
	String name=null;     //登录的用户名
	String password=null;  //旧密码
	String newPassword=null; //新密码
	String reqPassword=null;  //确认密码
	String type=null;      //登录的类型
	UserDao ud;
	
	public UserAction(){
		
	}
	
	public UserDao getUd() {
		return ud;
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReqPassword() {
		return reqPassword;
	}

	public void setReqPassword(String reqPassword) {
		this.reqPassword = reqPassword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public  String execute() {
		Map session=ActionContext.getContext().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		String hql=null;
		String loginResult=null;//返回的actionResult来判断是学生登录还是老师登录
		String hql2=null; 
		System.out.println("名字："+ name +" psw: " + password + " type: " + type);
		
        try{
			//类型转换，防止中文乱码
        	type = new String(type.getBytes("ISO8859-1"),"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// 判断登录的类型
		if (type != null && type != "") {
			if (type.equals("student")) {
				// 如果是学生的话就执行这条语句
				hql = "from InternationalStudent where studentId='"+name+"' and password='"+password+"'";
				loginResult = "student";
			} else if (type.equals("other")) {
				// 如果是教师或者是领导的话就执行这条
				//hql = "from Admin where adminId='"+name+"' and password='"+password+"'";
				hql2="from Teacher where teacherId='"+name+"' and password='"+password+"'";
				loginResult = "other";
			}
			// 验证登录的账号和密码
			if (ud.validLogin(hql)||ud.validLogin(hql2)) {
				if (loginResult.equals("student") && loginResult != null) {
					List<InternationalStudent> list = new ArrayList<InternationalStudent>();
					InternationalStudent loginStudent = new InternationalStudent();
					String str1 = "studentId= '" + name + "'";
					String str2 = "password= '" + password + "'";
					String str = "from InternationalStudent where  " + str1 + " and " + str2;
					list = ud.studentLogin(str);
					if (list != null && list.size() > 0) {
						loginStudent = list.get(0);
						session.put("loginUser", loginStudent);
						if(session.get("loginUser3")!=null){
                			session.put("loginUser3" ,null);
                		}
					}
				}else{
					/*Admin login2=new Admin();
                	List<Admin> list=new ArrayList<Admin>();
                	String str1="adminId= '"+ name+"'";
                 	String str2="password= '"+ password+"'";
                	String str="from Admin where  "+ str1 + " and " + str2;
                	list=ud.otherLogin(str);
                	if(list!=null && list.size()>0){
                		login2=list.get(0);
                		session.put("loginUser2" ,login2);
                		if(session.get("loginUser")!=null||session.get("loginUser3")!=null){
                			session.put("loginUser" ,null);
                			session.put("loginUser3" ,null);
                		}
                	}*/
            		Teacher login3=new Teacher();
                	List<Teacher> list3=new ArrayList<Teacher>();
                	String str3="teacherId= '"+ name+"'";
                 	String str4="password= '"+ password+"'";
                	String strx="from Teacher where  "+ str3 + " and " + str4;
                	list3=ud.teacherLogin(strx);
                	if(list3!=null && list3.size()>0){
                		login3=list3.get(0);
                		session.put("loginUser3" ,login3);
                		if(session.get("loginUser")!=null){
                			session.put("loginUser",null);
                		}
                	}
                }
				//存储登录者的信息进session,显示在界面的右上角
				session.put("loginIn" ,name);
				//存储登录者的类型，为了来判断显示导航栏上的哪些内容
				session.put("type",type);
			}else{
				this.addFieldError("userFault", "用户名或密码错误或登录类型错误");
				loginResult= "fault";
			}
		}else{
			loginResult="fault";
			this.addFieldError("userFault", "用户类型不能为空");
		}
		return loginResult;
	}
	
	//前台退出
	public String exitFront() {
		System.out.println("开始清除session");
		Map session = ActionContext.getContext().getSession();
		session.clear();
		System.out.println("清除session成功");
		return "exitlogin";
	}
	
	//前台修改密码
	public String editPassword(){
		//String message = "";
		Map session = ActionContext.getContext().getSession();
		System.out.println("前台action获得的ID:"+name);
		if(ud.updatePassword(name,password,newPassword,reqPassword)) {
			addFieldError("tip","修改密码成功");
			//message = "修改密码成功";
			return "editSucc";
		}else {
			addFieldError("tip","修改密码失败，检查旧密码是否正确以及新密码和确认密码是否一致");
			return "editFail";
			//message = "修改密码失败";
		}
	}
	
}
