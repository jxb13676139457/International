package com.international.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import jxl.*;
import org.apache.struts2.ServletActionContext;

import com.international.dao.ExchangeStuDao;
import com.international.dao.OverseasStuDao;
import com.international.dao.StuActivityDao;
import com.international.dao.StudentDao;
import com.international.model.ExchangeStudent;
import com.international.model.InternationalClass;
import com.international.model.InternationalStudent;
import com.international.model.OverseasStudent;
import com.international.model.StudentActivity;
import com.opensymphony.xwork2.ActionSupport;

import jxl.read.biff.BiffException;
import jxl.write.DateTime;

public class ImportExcelAction extends ActionSupport{
	
	private String uploadFileName;
	private File upload;
	private String savePath;
	private List<ExchangeStudent> exStudentList = new ArrayList<ExchangeStudent>();
	private List<InternationalStudent> interStudentList = new ArrayList<InternationalStudent>();
	private List<OverseasStudent> overseasStudentList = new ArrayList<OverseasStudent>();
	private List<StudentActivity> studentActivityList = new ArrayList<StudentActivity>();
	
	private ExchangeStuDao esd;
	private StudentDao sd;
	private OverseasStuDao osd;
	private StuActivityDao sad;
	
	public List<StudentActivity> getStudentActivityList() {
		return studentActivityList;
	}
	public void setStudentActivityList(List<StudentActivity> studentActivityList) {
		this.studentActivityList = studentActivityList;
	}
	public StuActivityDao getSad() {
		return sad;
	}
	public void setSad(StuActivityDao sad) {
		this.sad = sad;
	}
	public List<OverseasStudent> getOverseasStudentList() {
		return overseasStudentList;
	}
	public void setOverseasStudentList(List<OverseasStudent> overseasStudentList) {
		this.overseasStudentList = overseasStudentList;
	}
	public OverseasStuDao getOsd() {
		return osd;
	}
	public void setOsd(OverseasStuDao osd) {
		this.osd = osd;
	}
	public List<InternationalStudent> getInterStudentList() {
		return interStudentList;
	}
	public void setInterStudentList(List<InternationalStudent> interStudentList) {
		this.interStudentList = interStudentList;
	}
	public StudentDao getSd() {
		return sd;
	}
	public void setSd(StudentDao sd) {
		this.sd = sd;
	}
	public ExchangeStuDao getEsd() {
		return esd;
	}
	public void setEsd(ExchangeStuDao esd) {
		this.esd = esd;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public List<ExchangeStudent> getExStudentList() {
		return exStudentList;
	}
	public void setExStudentList(List<ExchangeStudent> exStudentList) {
		this.exStudentList = exStudentList;
	}
	
	//导入excel数据到交换生表  
    @SuppressWarnings("static-access")
	public void importExStudentExcel() throws IOException{  
		System.out.println("excel的值为："+ uploadFileName);
		String message="";
		String path=null;
		Workbook book = null;   //jxl工作簿
		InputStream fileIn = null;
	    if(uploadFileName!=null && !uploadFileName.equals("")){
		    //拦截仅允许上传文件类型
		    if(uploadFileName.substring(uploadFileName.lastIndexOf(".")).equals(".xls")) {
		    	//获取需要上传文件的文件路径  
				path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
				System.out.println("上传Excel的路径："+path);
				 //判断文件是否上传，如果上传的话将会创建该目录 
				File target= new File(path); // 定义目标文件对象
				try {
		            FileUtils.copyFile(upload, target);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }				
			    // 删除临时文件
			    upload.delete();
			    int sum=0;//计算导入成功的条数
			    try {
			    	System.out.println("上传路径："+path);
				    fileIn = new FileInputStream(path);
				    //根据指定的文件输入流导入Excel从而产生Workbook对象 
				    System.out.println("输入流："+fileIn);
				    //获取Excel对象
				    try {
						book = book.getWorkbook(new File(path));
					} catch (BiffException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				    //获取Excel的第一个sheet表
				    Sheet sheet = (Sheet) book.getSheet(0);
	    			System.out.println("获取到的Excel数据："+ sheet);
	    			System.out.println("行数："+sheet.getRows());
	    			System.out.println("列数："+sheet.getColumns());
	    			//对Sheet中的每一行进行迭代  
	    			for (int i = 1; i < sheet.getRows(); i++) {
	    				//创建实体类  
	    				ExchangeStudent exStudent = new ExchangeStudent();
	    				// 获取第一列第二行单元格对象
	                    exStudent.setStudentNo(sheet.getCell(0, i).getContents());
	                    exStudent.setStudentName(sheet.getCell(1, i).getContents());
	                    exStudent.setSex(sheet.getCell(2, i).getContents());
	                    exStudent.setClassName(sheet.getCell(3, i).getContents());
	                    exStudent.setMajor(sheet.getCell(4, i).getContents());
	                    Date sTime = null;
	                    //日期格式处理方式：
	                    if(sheet.getCell(5, i).getType() == CellType.DATE){
	                         DateCell dc = (DateCell)sheet.getCell(5, i);
	                         sTime = dc.getDate();    //获取单元格的date类型
	                    }
	                    exStudent.setStartTime(sTime);
	                    Date eTime = null;
	                    //日期格式处理方式：
	                    if(sheet.getCell(6, i).getType() == CellType.DATE){
	                         DateCell dc = (DateCell)sheet.getCell(6, i);
	                         eTime = dc.getDate();    //获取单元格的date类型
	                    }
	                    exStudent.setEndTime(eTime);
	                    exStudent.setExchangeCollege(sheet.getCell(7, i).getContents());
	                    //不是空对象才加入集合中
	                    if(exStudent!=null) {
	                    	exStudentList.add(exStudent);
	                    }
	    	        }  
	    			System.out.println(exStudentList);
	    	        for(int i=0; i<exStudentList.size(); i++){
	    	        	if(!esd.queryByStudentNo(exStudentList.get(i).getStudentNo())) {
	    	        		if(esd.addExStudent(exStudentList.get(i))){
		    	        		sum++;
		    	        	}
	    	        	}else {
	    	        		message = "此条学生记录已存在";
	    	        		System.out.println("此条学生记录已存在");
	    	        		continue;
	    	        	}
	    	        	
	    	        }
	    	        System.out.println("导入的学生人数："+sum);
	    	        if(sum>0){
	    	        	System.out.println("导入成功");
	    		   		message="导入成功";
	    	        }else{
	    	        	System.out.println("导入失败");
	    	        	message="导入失败";
	    	        }
	    	        fileIn.close();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }else {
			    System.out.println("上传的只能是后缀为.xls的Excel文件");
			    message = "上传的只能是后缀为.xls的Excel文件";
		    }
	    }else{
		    message="请先上传交换生信息的Excel文件";
		    System.out.println("请先上传交换生信息的Excel文件");
	    }
	    ajaxAction.toJson(ServletActionContext.getResponse(),message);
	}
    
    //导入excel数据到国际班学生表  
    @SuppressWarnings("static-access")
	public void importInterStudentExcel() throws IOException {
    	System.out.println("excel的值为："+ uploadFileName);
		String message="";
		String path=null;
		Workbook book = null;   //jxl工作簿
		InputStream fileIn = null;
	    if(uploadFileName!=null && !uploadFileName.equals("")){
		    //拦截仅允许上传文件类型
		    if(uploadFileName.substring(uploadFileName.lastIndexOf(".")).equals(".xls")) {
		    	//获取需要上传文件的文件路径  
				path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
				System.out.println("上传Excel的路径："+path);
				 //判断文件是否上传，如果上传的话将会创建该目录 
				File target= new File(path); // 定义目标文件对象
				try {
		            FileUtils.copyFile(upload, target);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }				
			    // 删除临时文件
			    upload.delete();
			    int sum=0;//计算导入成功的条数
			    try {
			    	System.out.println("上传路径："+path);
				    fileIn = new FileInputStream(path);
				    //根据指定的文件输入流导入Excel从而产生Workbook对象 
				    System.out.println("输入流："+fileIn);
				    //获取Excel对象
				    try {
						book = book.getWorkbook(new File(path));
					} catch (BiffException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				    //获取Excel的第一个sheet表
				    Sheet sheet = (Sheet) book.getSheet(0);
	    			System.out.println("获取到的Excel数据："+ sheet);
	    			System.out.println("行数："+sheet.getRows());
	    			System.out.println("列数："+sheet.getColumns());
	    			//对Sheet中的每一行进行迭代  
	    			for (int i = 1; i < sheet.getRows(); i++) {
	    				//创建实体类  
	    				InternationalStudent interStudent = new InternationalStudent();
	    				// 获取第一列第二行单元格对象
	    				interStudent.setStudentId(sheet.getCell(0, i).getContents());
	    				interStudent.setStudentName(sheet.getCell(1, i).getContents());
	    				interStudent.setSex(sheet.getCell(2, i).getContents());
	    				interStudent.setPassword(sheet.getCell(3, i).getContents());
	    				String className = sheet.getCell(4,i).getContents();
	    				//先去确认有无这个班级
	    				if(sd.queryByClassName(className)!=null) {
	    					interStudent.setClasses(sd.queryByClassName(className));
	    				}else {
	    					System.out.println("数据库无这个学生所属的班级");
	    					continue;
	    				}
	    				interStudent.setStatus(sheet.getCell(7, i).getContents());
	                    //不是空对象才加入集合中
	                    if(interStudent!=null) {
	                    	interStudentList.add(interStudent);
	                    }
	    	        }  
	    			System.out.println(interStudentList);
	    	        for(int i=0; i<interStudentList.size(); i++){
	    	        	//如果数据库不存在此学生对象才给导入
	    	        	if(sd.queryById(interStudentList.get(i).getStudentId())==null) {
	    	        		if(sd.addStudent(interStudentList.get(i))){
		    	        		sum++;
		    	        	}
	    	        	}else {
	    	        		//message = "此条学生记录已存在";
	    	        		System.out.println("此条学生记录已存在");
	    	        		continue;
	    	        	}
	    	        	
	    	        }
	    	        System.out.println("导入的学生人数："+sum);
	    	        if(sum>0){
	    	        	System.out.println("导入成功");
	    		   		message="导入成功";
	    	        }else{
	    	        	System.out.println("导入失败");
	    	        	message="导入失败";
	    	        }
	    	        fileIn.close();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }else {
			    System.out.println("上传的只能是后缀为.xls的Excel文件");
			    message = "上传的只能是后缀为.xls的Excel文件";
		    }
	    }else{
		    message="请先上传交换生信息的Excel文件";
		    System.out.println("请先上传交换生信息的Excel文件");
	    }
	    ajaxAction.toJson(ServletActionContext.getResponse(),message);
    }
    
    //导入excel数据到出国学生表  
    @SuppressWarnings("static-access")
	public void importOverseasStudentExcel() throws IOException {
    	System.out.println("excel的值为："+ uploadFileName);
		String message="";
		String path=null;
		Workbook book = null;   //jxl工作簿
		InputStream fileIn = null;
	    if(uploadFileName!=null && !uploadFileName.equals("")){
		    //拦截仅允许上传文件类型
		    if(uploadFileName.substring(uploadFileName.lastIndexOf(".")).equals(".xls")) {
		    	//获取需要上传文件的文件路径  
				path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
				System.out.println("上传Excel的路径："+path);
				 //判断文件是否上传，如果上传的话将会创建该目录 
				File target= new File(path); // 定义目标文件对象
				try {
		            FileUtils.copyFile(upload, target);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }				
			    // 删除临时文件
			    upload.delete();
			    int sum=0;//计算导入成功的条数
			    try {
			    	System.out.println("上传路径："+path);
				    fileIn = new FileInputStream(path);
				    //根据指定的文件输入流导入Excel从而产生Workbook对象 
				    System.out.println("输入流："+fileIn);
				    //获取Excel对象
				    try {
						book = book.getWorkbook(new File(path));
					} catch (BiffException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				    //获取Excel的第一个sheet表
				    Sheet sheet = (Sheet) book.getSheet(0);
	    			System.out.println("获取到的Excel数据："+ sheet);
	    			System.out.println("行数："+sheet.getRows());
	    			System.out.println("列数："+sheet.getColumns());
	    			//对Sheet中的每一行进行迭代  
	    			for (int i = 1; i < sheet.getRows(); i++) {
	    				//创建实体类  
	    				OverseasStudent overseasStudent = new OverseasStudent();
	    				// 获取第一列第二行单元格对象
	    				overseasStudent.setStudentId(sheet.getCell(0, i).getContents());
	    				overseasStudent.setStudentName(sheet.getCell(1, i).getContents());
	    				overseasStudent.setSex(sheet.getCell(2, i).getContents());
	    				String className = sheet.getCell(3,i).getContents();
	    				//先去确认有无这个班级
	    				if(osd.queryByClassName(className)!=null) {
	    					overseasStudent.setClasses(osd.queryByClassName(className));
	    				}else {
	    					System.out.println("数据库无这个班级");
	    					continue;
	    				}
	    				String collegeName = sheet.getCell(5,i).getContents();
	    				//先去确认有无这个国外院校
	    				if(osd.queryByCollegeName(collegeName)!=null) {
	    					overseasStudent.setCollege(osd.queryByCollegeName(collegeName));
	    				}else {
	    					System.out.println("数据库无这个国外院校");
	    					continue;
	    				}
	    				overseasStudent.setOutMajor(sheet.getCell(6, i).getContents());
	    				Date outTime = null;
	                    //日期格式处理方式：
	                    if(sheet.getCell(7, i).getType() == CellType.DATE){
	                         DateCell dc = (DateCell)sheet.getCell(7, i);
	                         outTime = dc.getDate();    //获取单元格的date类型
	                    }
	                    overseasStudent.setOutTime(outTime);
	                    overseasStudent.setInDegree(sheet.getCell(8, i).getContents());
	                    overseasStudent.setOutDegree(sheet.getCell(9, i).getContents());
	                    overseasStudent.setInSchoolarship(sheet.getCell(10, i).getContents());
	                    overseasStudent.setInAmount(Integer.parseInt(sheet.getCell(11, i).getContents()));
	                    overseasStudent.setOutSchoolarship(sheet.getCell(12, i).getContents());
	                    overseasStudent.setOutAmount(Integer.parseInt(sheet.getCell(13, i).getContents()));
	                    overseasStudent.setCurrency(sheet.getCell(14, i).getContents());
	                    overseasStudent.setSubsidy(Integer.parseInt(sheet.getCell(16, i).getContents()));
	                    //不是空对象才加入集合中
	                    if(overseasStudent!=null) {
	                    	overseasStudentList.add(overseasStudent);
	                    }
	    	        }  
	    			System.out.println(overseasStudentList);
	    	        for(int i=0; i<overseasStudentList.size(); i++){
	    	        	//如果数据库不存在此学生对象才给导入
	    	        	if(osd.queryById(overseasStudentList.get(i).getStudentId())==null) {
	    	        		if(osd.addStudent(overseasStudentList.get(i))){
		    	        		sum++;
		    	        	}
	    	        	}else {
	    	        		//message = "此条学生记录已存在";
	    	        		System.out.println("此条学生记录已存在");
	    	        		continue;
	    	        	}
	    	        }
	    	        System.out.println("导入的学生人数："+sum);
	    	        if(sum>0){
	    	        	System.out.println("导入成功");
	    		   		message="导入成功";
	    	        }else{
	    	        	System.out.println("导入失败");
	    	        	message="导入失败";
	    	        }
	    	        fileIn.close();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }else {
			    System.out.println("上传的只能是后缀为.xls的Excel文件");
			    message = "上传的只能是后缀为.xls的Excel文件";
		    }
	    }else{
		    message="请先上传交换生信息的Excel文件";
		    System.out.println("请先上传交换生信息的Excel文件");
	    }
	    ajaxAction.toJson(ServletActionContext.getResponse(),message);
    }
    
    //导入excel数据到学生活动表  
    @SuppressWarnings("static-access")
	public void importStudentActivityExcel() throws IOException {
    	System.out.println("excel的值为："+ uploadFileName);
		String message="";
		String path=null;
		Workbook book = null;   //jxl工作簿
		InputStream fileIn = null;
	    if(uploadFileName!=null && !uploadFileName.equals("")){
		    //拦截仅允许上传文件类型
		    if(uploadFileName.substring(uploadFileName.lastIndexOf(".")).equals(".xls")) {
		    	//获取需要上传文件的文件路径  
				path=ServletActionContext.getServletContext().getRealPath(this.getSavePath()+ "\\" +this.uploadFileName);
				System.out.println("上传Excel的路径："+path);
				 //判断文件是否上传，如果上传的话将会创建该目录 
				File target= new File(path); // 定义目标文件对象
				try {
		            FileUtils.copyFile(upload, target);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }				
			    // 删除临时文件
			    upload.delete();
			    int sum=0;//计算导入成功的条数
			    try {
			    	System.out.println("上传路径："+path);
				    fileIn = new FileInputStream(path);
				    //根据指定的文件输入流导入Excel从而产生Workbook对象 
				    System.out.println("输入流："+fileIn);
				    //获取Excel对象
				    try {
						book = book.getWorkbook(new File(path));
					} catch (BiffException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				    //获取Excel的第一个sheet表
				    Sheet sheet = (Sheet) book.getSheet(0);
	    			System.out.println("获取到的Excel数据："+ sheet);
	    			System.out.println("行数："+sheet.getRows());
	    			System.out.println("列数："+sheet.getColumns());
	    			//对Sheet中的每一行进行迭代  
	    			for (int i = 1; i < sheet.getRows(); i++) {
	    				//创建实体类  
	    				StudentActivity studentActivity = new StudentActivity();
	    				// 获取第一列第二行单元格对象
	    				studentActivity.setActivityName(sheet.getCell(0, i).getContents());
	    				studentActivity.setStudentId(sheet.getCell(1, i).getContents());
	    				studentActivity.setStudentName(sheet.getCell(2, i).getContents());
	    				String collegeName = sheet.getCell(3,i).getContents();
	    				//需求是学生不一定参加数据库有的国外院校故不做判断
	    				studentActivity.setCollege(osd.queryByCollegeName(collegeName));
	    				studentActivity.setActivityContent(sheet.getCell(4, i).getContents());
	    				Date startTime = null;
	                    //日期格式处理方式：
	                    if(sheet.getCell(5, i).getType() == CellType.DATE){
	                         DateCell dc = (DateCell)sheet.getCell(5, i);
	                         startTime = dc.getDate();    //获取单元格的date类型
	                    }
	                    studentActivity.setStartTime(startTime);
	                    Date endTime = null;
	                    //日期格式处理方式：
	                    if(sheet.getCell(6, i).getType() == CellType.DATE){
	                         DateCell dc = (DateCell)sheet.getCell(6, i);
	                         endTime = dc.getDate();    //获取单元格的date类型
	                    }
	                    studentActivity.setEndTime(endTime);
	                    studentActivity.setFee(Integer.parseInt(sheet.getCell(7, i).getContents()));
	                    studentActivity.setCurrency(sheet.getCell(8, i).getContents());
	                    //不是空对象才加入集合中
	                    if(studentActivity!=null) {
	                    	studentActivityList.add(studentActivity);
	                    }
	    	        }  
	    			System.out.println(studentActivityList);
	    	        for(int i=0; i<studentActivityList.size(); i++){
	    	        	//如果数据库不存在此学生活动对象才给导入，不过是不可能出现的，因为数据库是自增ID，我当初设计的哈哈哈
	    	        	if(sad.queryById(studentActivityList.get(i).getActivityId())==null) {
	    	        		if(sad.addStuActivity(studentActivityList.get(i))){
		    	        		sum++;
		    	        	}
	    	        	}else {
	    	        		//message = "此条学生记录已存在";
	    	        		System.out.println("此条学生记录已存在");
	    	        		continue;
	    	        	}
	    	        	
	    	        }
	    	        System.out.println("导入的学生人数："+sum);
	    	        if(sum>0){
	    	        	System.out.println("导入成功");
	    		   		message="导入成功";
	    	        }else{
	    	        	System.out.println("导入失败");
	    	        	message="导入失败";
	    	        }
	    	        fileIn.close();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }else {
			    System.out.println("上传的只能是后缀为.xls的Excel文件");
			    message = "上传的只能是后缀为.xls的Excel文件";
		    }
	    }else{
		    message="请先上传交换生信息的Excel文件";
		    System.out.println("请先上传交换生信息的Excel文件");
	    }
	    ajaxAction.toJson(ServletActionContext.getResponse(),message);
    }
}
