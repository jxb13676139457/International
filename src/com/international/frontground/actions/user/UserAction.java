package com.international.frontground.actions.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.international.dao.UserDao;
import com.international.model.Admin;
import com.international.model.InternationalStudent;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class UserAction extends ActionSupport{
	String    name=null;     //登录的用户名
	String    password=null;  //登录的密码
	String    newPassword=null; //确认密码
	String    type=null;      //登录的类型
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
		String loginResult=null;//返回的actionResult来判断是学生登录还是老师或领导登录
		
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
				hql = "from Admin where adminId='"+name+"' and password='"+password+"'";
				loginResult = "other";
			}
			// 验证登录的账号和密码
			if (ud.validLogin(hql)) {
				
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
						if(session.get("loginUser2")!=null){
                			session.put("loginUser2" ,null);
                		}
						//session.put("loginIn" ,loginStudent);
					}
				}else{
					Admin login2=new Admin();
                	List<Admin> list=new ArrayList<Admin>();
                	String str1="adminId= '"+ name+"'";
                 	String str2="password= '"+ password+"'";
                	String str="from Admin where  "+ str1 + " and " + str2;
                	list=ud.otherLogin(str);
                	if(list!=null && list.size()>0){
                		login2=list.get(0);
                		session.put("loginUser2" ,login2);
                		if(session.get("loginUser")!=null){
                			session.put("loginUser" ,null);
                		}
                		//session.put("loginIn" ,login2);
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
			System.out.println("xxxxxxxx");
			this.addFieldError("userFault", "用户类型不能为空");
		}
		return loginResult;
	}
}
