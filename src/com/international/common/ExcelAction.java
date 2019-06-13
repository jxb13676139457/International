package com.international.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.multipart.MultipartFile;

import com.international.dao.ActivityDao;
import com.international.dao.AgencyDao;
import com.international.dao.AttendTrainingDao;
import com.international.dao.ClassesDao;
import com.international.dao.CollegeDao;
import com.international.dao.ExamDao;
import com.international.dao.ExchangeStuDao;
import com.international.dao.ImportExcelDao;
import com.international.dao.OverseasStuDao;
import com.international.dao.ScoreDao;
import com.international.dao.StuActivityDao;
import com.international.dao.StudentDao;
import com.international.dao.TrainingDao;
import com.international.dao.UserDao;
import com.international.model.Admin;
import com.international.model.Agency;
import com.international.model.AttendTraining;
import com.international.model.College;
import com.international.model.CollegeActivity;
import com.international.model.Exam;
import com.international.model.ExchangeStudent;
import com.international.model.InternationalClass;
import com.international.model.InternationalStudent;
import com.international.model.OverseasStudent;
import com.international.model.Score;
import com.international.model.StudentActivity;
import com.international.model.Training;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExcelAction extends ActionSupport {
	
	private static final long serialVersionUID = 1821670445298887284L;
	
	private File excelFile;
	private MultipartFile file;
	private String excelName;
	private String result;
	private String savePath;
	private String allowedTypes;
	private String uploadContentType;
	private List<InternationalStudent> interStudentList = new ArrayList<InternationalStudent>();
	private List<ExchangeStudent> exStudentList = new ArrayList<ExchangeStudent>();
	
	StudentDao sd;
	OverseasStuDao osd;
	ExchangeStuDao esd;
	StuActivityDao sad;
	CollegeDao cd;
	ActivityDao ad;
	ExamDao ed;
	ScoreDao scoredao;
	ClassesDao classDao;
	UserDao ud;
	AttendTrainingDao atd;
	TrainingDao td;
	AgencyDao agencydao;
	ImportExcelDao ied;
	
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getAllowedTypes() {
		return allowedTypes;
	}
	public void setAllowedTypes(String allowedTypes) {
		this.allowedTypes = allowedTypes;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public AgencyDao getAgencydao() {
		return agencydao;
	}
	public void setAgencydao(AgencyDao agencydao) {
		this.agencydao = agencydao;
	}
	public TrainingDao getTd() {
		return td;
	}
	public void setTd(TrainingDao td) {
		this.td = td;
	}
	public AttendTrainingDao getAtd() {
		return atd;
	}
	public void setAtd(AttendTrainingDao atd) {
		this.atd = atd;
	}
	public UserDao getUd() {
		return ud;
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	public ClassesDao getClassDao() {
		return classDao;
	}
	public void setClassDao(ClassesDao classDao) {
		this.classDao = classDao;
	}
	public StuActivityDao getSad() {
		return sad;
	}
	public void setSad(StuActivityDao sad) {
		this.sad = sad;
	}
	public ExchangeStuDao getEsd() {
		return esd;
	}
	public void setEsd(ExchangeStuDao esd) {
		this.esd = esd;
	}
	public StudentDao getSd() {
		return sd;
	}
	public void setSd(StudentDao sd) {
		this.sd = sd;
	}
	public OverseasStuDao getOsd() {
		return osd;
	}
	public void setOsd(OverseasStuDao osd) {
		this.osd = osd;
	}
	public ImportExcelDao getIed() {
		return ied;
	}
	public void setIed(ImportExcelDao ied) {
		this.ied = ied;
	}
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	public List<InternationalStudent> getInterStudentList() {
		return interStudentList;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public void setInterStudentList(List<InternationalStudent> interStudentList) {
		this.interStudentList = interStudentList;
	}
	public List<ExchangeStudent> getExStudentList() {
		return exStudentList;
	}
	public void setExStudentList(List<ExchangeStudent> exStudentList) {
		this.exStudentList = exStudentList;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public CollegeDao getCd() {
		return cd;
	}
	public void setCd(CollegeDao cd) {
		this.cd = cd;
	}
	public ActivityDao getAd() {
		return ad;
	}
	public void setAd(ActivityDao ad) {
		this.ad = ad;
	}
	public ExamDao getEd() {
		return ed;
	}
	public void setEd(ExamDao ed) {
		this.ed = ed;
	}
	public ScoreDao getScoredao() {
		return scoredao;
	}
	public void setScoredao(ScoreDao scoredao) {
		this.scoredao = scoredao;
	}
	public String getSavePath() {
        return savePath = ServletActionContext.getServletContext().getRealPath(
                "/uploadExcel");
    }
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

	private HSSFWorkbook workbook;
	private HSSFCell cell = null;  //Excel的列 
	private HSSFRow row = null;  //Excel的行
	private HSSFCellStyle style,style1;
	
	//导出国际班学生
	public String exportInterStudent() throws Exception{
		//获取国际班学生对象
		List<InternationalStudent> studentList = sd.queryInterStudents("");
		String []tableHeader={"学号","姓名","性别","密码","班级","年级","专业","状态"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(studentList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("国际班学生表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < studentList.size() ;i++){    
					//获取InternationalStudent对象
					InternationalStudent student1 = (InternationalStudent)studentList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(student1.getStudentId() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(student1.getStudentId());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(student1.getStudentName() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(student1.getStudentName());//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(student1.getSex() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(student1.getSex()); 
					    cell.setCellStyle(style); 
				    } 
				    if(student1.getPassword()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(student1.getPassword()); 
					    cell.setCellStyle(style); 
				    } 
				    if(student1.getClasses().getClassName()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(student1.getClasses().getClassName()); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getClasses().getGrade()!= null){ 
					    cell = row.createCell((short) 5); 
					    cell.setCellValue(student1.getClasses().getGrade()); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getClasses().getMajor()!= null){ 
					    cell = row.createCell((short) 6); 
					    cell.setCellValue(student1.getClasses().getMajor()); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getStatus() != null){ 
					    cell = row.createCell((short) 7); 
					    cell.setCellValue(student1.getStatus()); 
					    cell.setCellStyle(style); 
				    } 
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("国际班学生表.xls");
		return null; 
	} 
	
	//导入交换生Excel数据     
	public void importExchangeStudentExcel() throws IOException{
		System.out.println("测试进入此导入action");
		System.out.println("前台传过来的file："+file);
		try {
			List<ExchangeStudent> exStudent = ImportUtil.readExcel(file);
			for(int i=0;i<exStudent.size();i++) {
				if(esd.addExStudent(exStudent.get(i))) {
					System.out.println("插入第"+i+"条交换生数据");
				};
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//上传excel文件
	public String Fileupload(){
        String realPath = ServletActionContext.getServletContext().getRealPath(this.getSavePath()+"\\"+this.excelName);
        System.out.println(realPath);
        System.out.println(allowedTypes);
        System.out.println(uploadContentType);
		//定义目标对象文件，保存文件
        File newExcel = new File(realPath);
        if(newExcel!=null) {
        	try {
                FileUtils.copyFile(excelFile, newExcel);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 删除临时文件
            excelFile.delete();
            return "uploadExcelSuccess";
        }else {
        	System.out.println("没有上传文件");
        	return "error";
        }
        
    
    }
		
	//导入国际班学生数据   (此功能未完成)
	public void importInterStudentExcel() throws Exception{
//		try {
//			Map session = ActionContext.getContext().getSession();
//			System.out.println(this.getSavePath());
//			session.put("savePath", this.getSavePath());
//			FileUtils.copyFile(excelFile, new File(this.getSavePath()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "import";
	}
	
	//导出培训机构
	
//	@SuppressWarnings("deprecation")
//	public String getSavePath() {
//		return ServletActionContext.getRequest().getRealPath(savePath)+"\\"+this.excelFile;
//	}
	
	//导出合作的雅思培训机构
	public String exportAgency() throws Exception{
		//获取雅思培训机构对象
		List<Agency> agencyList = agencydao.queryAllAgency();
		String []tableHeader={"培训机构","合作时间","联系人","职位","联系电话","电子邮箱"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(agencyList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("培训机构表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < agencyList.size() ;i++){    
					//获取InternationalStudent对象
					Agency agency1 = (Agency)agencyList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(agency1.getAgencyName() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(agency1.getAgencyName());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(agency1.getTime() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(agency1.getTime().substring(0,10));//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(agency1.getCotactPerson() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(agency1.getCotactPerson()); 
					    cell.setCellStyle(style); 
				    } 
				    if(agency1.getPosition()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(agency1.getPosition()); 
					    cell.setCellStyle(style); 
				    } 
				    if(agency1.getPhone()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(agency1.getPhone()); 
					    cell.setCellStyle(style); 
				    }
				    if(agency1.getEmail()!= null){ 
					    cell = row.createCell((short) 5); 
					    cell.setCellValue(agency1.getEmail()); 
					    cell.setCellStyle(style); 
				    }
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("合作培训机构表.xls");
		return null; 
	}
	
	//导出国外院校
	public String exportCollege() throws Exception{
		//获取国外院校对象
		List<College> collegeList = cd.queryAllCollege("");
		String []tableHeader={"国外院校","地址","合作开始时间","合作类型","备注状态","国家","联系人","职位","联系电话","电子邮箱"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(collegeList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("国外院校表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < collegeList.size() ;i++){    
					//获取InternationalStudent对象
					College college1 = (College)collegeList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(college1.getCollegeName() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(college1.getCollegeName());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(college1.getAddress() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(college1.getAddress());//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(college1.getStartTime() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(college1.getStartTime().substring(0,10)); 
					    cell.setCellStyle(style); 
				    } 
				    if(college1.getType()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(college1.getType()); 
					    cell.setCellStyle(style); 
				    } 
				    if(college1.getStatus()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(college1.getStatus()); 
					    cell.setCellStyle(style); 
				    }
				    if(college1.getCountry()!= null){ 
					    cell = row.createCell((short) 5); 
					    cell.setCellValue(college1.getCountry()); 
					    cell.setCellStyle(style); 
				    }
				    if(college1.getContactPerson()!= null){ 
					    cell = row.createCell((short) 6); 
					    cell.setCellValue(college1.getContactPerson()); 
					    cell.setCellStyle(style); 
				    }
				    if(college1.getPosition() != null){ 
					    cell = row.createCell((short) 7); 
					    cell.setCellValue(college1.getPosition()); 
					    cell.setCellStyle(style); 
				    } 
				    if(college1.getPhone() != null){ 
					    cell = row.createCell((short) 8); 
					    cell.setCellValue(college1.getPhone()); 
					    cell.setCellStyle(style); 
				    } 
				    if(college1.getEmail() != null){ 
					    cell = row.createCell((short) 9); 
					    cell.setCellValue(college1.getEmail()); 
					    cell.setCellStyle(style); 
				    } 
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("国外院校表.xls");
		return null; 
	}
	
	//导出后台操作员
	public String exportOperator() throws Exception{
		//获取后台管理员对象
		List<Admin> adminList = ud.queryAdmins("");
		String []tableHeader={"操作员ID","名字","性别","密码","是否管理员"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(adminList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("后台操作员表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < adminList.size() ;i++){    
					//获取admin对象
					Admin admin1 = (Admin)adminList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(admin1.getAdminId() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(admin1.getAdminId());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(admin1.getUserName() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(admin1.getUserName());//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(admin1.getSex() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(admin1.getSex()); 
					    cell.setCellStyle(style); 
				    } 
				    if(admin1.getPassword()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(admin1.getPassword()); 
					    cell.setCellStyle(style); 
				    }
				    if(admin1.getType()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(admin1.getType()); 
					    cell.setCellStyle(style); 
				    }
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("后台操作员表.xls");
		return null; 
	}
	
	//导出学生参与培训计划
	public String exportAttendTraining() throws Exception{
		//获取学生参与培训计划对象
		List<AttendTraining> attendList = atd.queryAttends("");
		String []tableHeader={"学号","姓名","班级","专业","培训机构","培训开始时间","培训结束时间","培训课时","培训费用"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(attendList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("学生参与培训计划表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < attendList.size() ;i++){    
					//获取admin对象
					AttendTraining attend1 = (AttendTraining)attendList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(attend1.getInterStudent().getStudentId() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(attend1.getInterStudent().getStudentId());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(attend1.getInterStudent().getStudentName() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(attend1.getInterStudent().getStudentName());//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(attend1.getInterStudent().getClasses().getClassName() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(attend1.getInterStudent().getClasses().getClassName()); 
					    cell.setCellStyle(style); 
				    } 
				    if(attend1.getInterStudent().getClasses().getMajor()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(attend1.getInterStudent().getClasses().getMajor()); 
					    cell.setCellStyle(style); 
				    }
				    if(attend1.getTraining().getAgencies().getAgencyName()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(attend1.getTraining().getAgencies().getAgencyName()); 
					    cell.setCellStyle(style); 
				    }
				    if(attend1.getTraining().getStartTime()!= null){ 
					    cell = row.createCell((short) 5); 
					    cell.setCellValue(attend1.getTraining().getStartTime().substring(0,10)); 
					    cell.setCellStyle(style); 
				    }
				    if(attend1.getTraining().getEndTime()!= null){ 
					    cell = row.createCell((short) 6); 
					    cell.setCellValue(attend1.getTraining().getEndTime().substring(0,10)); 
					    cell.setCellStyle(style); 
				    }
				    
				    cell = row.createCell((short) 7); 
				    cell.setCellValue(attend1.getTraining().getCourseHours()); 
				    cell.setCellStyle(style); 
				   
				    cell = row.createCell((short) 8); 
				    cell.setCellValue(attend1.getTraining().getCourseFee()); 
				    cell.setCellStyle(style); 
			    
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("学生参与培训计划表.xls");
		return null; 
	}
	
	//导出雅思培训计划
	public String exportTraining() throws Exception{
		//获取培训计划对象
		List<Training> trainingList = td.queryAllTraining();
		String []tableHeader={"培训机构","培训开始时间","培训结束时间","培训课时","培训费用"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(trainingList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("培训计划表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < trainingList.size() ;i++){    
					//获取training对象
					Training training1 = (Training)trainingList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(training1.getAgencies().getAgencyName() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(training1.getAgencies().getAgencyName());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(training1.getStartTime() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(training1.getStartTime().substring(0,10));//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(training1.getEndTime() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(training1.getEndTime().substring(0,10)); 
					    cell.setCellStyle(style); 
				    } 
				    
				    cell = row.createCell((short) 3); 
				    cell.setCellValue(training1.getCourseHours()); 
				    cell.setCellStyle(style); 
				   
				    cell = row.createCell((short) 4); 
				    cell.setCellValue(training1.getCourseFee()); 
				    cell.setCellStyle(style); 
			    
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("培训计划表.xls");
		return null; 
	}
	
	//导出国外院校交流活动  
	public String exportCollegeActivity() throws Exception{
		//获取国外院校交流活动对象
		List<CollegeActivity> collegeActivityList = ad.queryAllActivity("","","");
		System.out.println("测试："+collegeActivityList);
		String []tableHeader={"国外院校","交流活动主题","交流活动内容","交流活动时间","交流活动类型"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(collegeActivityList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("国外院校交流活动表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < collegeActivityList.size() ;i++){    
					//获取InternationalStudent对象
					CollegeActivity activity1 = (CollegeActivity)collegeActivityList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    cell = row.createCell((short) 0);//创建第i+1行第0列 
				    cell.setCellValue(activity1.getColl().getCollegeName());//设置第i+1行第0列的值 
				    cell.setCellStyle(style);//设置风格 
				    
				    cell = row.createCell((short) 1);//创建第i+1行第1列 
				    cell.setCellValue(activity1.getTitle());//设置第i+1行第0列的值 
				    cell.setCellStyle(style);//设置风格 
			    
				    cell = row.createCell((short) 2); //创建第i+1行第2列 
				    cell.setCellValue(activity1.getContent());//设置第i+1行第1列的值 
				    cell.setCellStyle(style); //设置风格 
			   
				    cell = row.createCell((short) 3); 
				    cell.setCellValue(activity1.getTime().substring(0,10)); 
				    cell.setCellStyle(style); 
			    
				    cell = row.createCell((short) 4); 
				    cell.setCellValue(activity1.getType()); 
				    cell.setCellStyle(style); 
				    
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("国外院校交流活动表.xls");
		return null; 
	}
	
	//导出考试信息
	public String exportExam() throws Exception{
		//获取考试信息对象
		List<Exam> examList = ed.queryAllExam("");
		String []tableHeader={"雅思机构","考试时间","考试地点","考试类型"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(examList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("雅思考试表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < examList.size() ;i++){    
					//获取InternationalStudent对象
					Exam exam1 = (Exam)examList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(exam1.getAgen().getAgencyName() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(exam1.getAgen().getAgencyName());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(exam1.getTime() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(exam1.getTime().substring(0,10));//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(exam1.getLocation() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(exam1.getLocation()); 
					    cell.setCellStyle(style); 
				    } 
				    if(exam1.getExamType()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(exam1.getExamType()); 
					    cell.setCellStyle(style); 
				    }
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("雅思考试表.xls");
		return null; 
	}
	
	//导出学生参与考试信息(成绩单)   
	public String exportScore() throws Exception{
		//获取考试信息对象
		List<Score> scoreist = scoredao.queryAllScore("");
		String []tableHeader={"雅思考试类型","考试时间","考试地点","学号","姓名","班级","听力分数","口语分数","阅读分数","写作分数","总分数"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(scoreist.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("雅思参与考试分数表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < scoreist.size() ;i++){    
					//获取InternationalStudent对象
					Score score1 = (Score)scoreist.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(score1.getExm().getExamType() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(score1.getExm().getExamType());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(score1.getExm().getTime() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(score1.getExm().getTime().substring(0,10));//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(score1.getExm().getLocation() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(score1.getExm().getLocation()); 
					    cell.setCellStyle(style); 
				    } 
				    if(score1.getInterStu().getStudentId()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(score1.getInterStu().getStudentId()); 
					    cell.setCellStyle(style); 
				    }
				    if(score1.getInterStu().getStudentName()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(score1.getInterStu().getStudentName()); 
					    cell.setCellStyle(style); 
				    }
				    if(score1.getInterStu().getClasses().getClassName()!= null){ 
					    cell = row.createCell((short) 5); 
					    cell.setCellValue(score1.getInterStu().getClasses().getClassName()); 
					    cell.setCellStyle(style); 
				    }
				  
				    cell = row.createCell((short) 6); 
				    cell.setCellValue(score1.getListening()); 
				    cell.setCellStyle(style); 
			    
				    cell = row.createCell((short) 7); 
				    cell.setCellValue(score1.getOral()); 
				    cell.setCellStyle(style); 
			    
				    cell = row.createCell((short) 8); 
				    cell.setCellValue(score1.getReading()); 
				    cell.setCellStyle(style); 
			   
				    cell = row.createCell((short) 9); 
				    cell.setCellValue(score1.getWriting()); 
				    cell.setCellStyle(style); 
				    
				    cell = row.createCell((short) 10); 
				    cell.setCellValue(score1.getScore()); 
				    cell.setCellStyle(style); 
				    
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("参与雅思考试表（成绩单）.xls");
		return null; 
	}
	
	//导出交换生
	public String exportExchangeSudent() throws Exception{
		//获取交换生对象
		List<ExchangeStudent> studentList = esd.queryInterStudents("");
		String []tableHeader={"学号","姓名","性别","班级","专业","交换开始时间","交换结束时间","交换的院校"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(studentList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("交换生表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < studentList.size() ;i++){    
					//获取InternationalStudent对象
					ExchangeStudent student1 = (ExchangeStudent)studentList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(student1.getStudentNo() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(student1.getStudentNo());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(student1.getStudentName() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(student1.getStudentName());//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(student1.getSex() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(student1.getSex()); 
					    cell.setCellStyle(style); 
				    } 
				    if(student1.getClassName()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(student1.getClassName()); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getMajor()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(student1.getMajor()); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getStartTime() != null){ 
					    cell = row.createCell((short) 5); 
					    cell.setCellValue(student1.getStartTime().toString().substring(0,10)); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getEndTime() != null){ 
					    cell = row.createCell((short) 6); 
					    cell.setCellValue(student1.getEndTime().toString().substring(0,10)); 
					    cell.setCellStyle(style); 
				    } 
				    if(student1.getExchangeCollege() != null){ 
					    cell = row.createCell((short) 7); 
					    cell.setCellValue(student1.getExchangeCollege()); 
					    cell.setCellStyle(style); 
				    } 
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("交换生表.xls");
		return null; 
	}
	
	//导出学生活动
	public String exportStudentActivity() throws Exception{
		//获取交换生对象
		List<StudentActivity> stuActivityList = sad.queryStuActivities("");
		String []tableHeader={"活动主题","学号","姓名","合作国外院校","活动内容","活动开始时间","活动结束时间","活动费用","费用币种"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(stuActivityList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("学生参与活动表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < stuActivityList.size() ;i++){    
					//获取InternationalStudent对象
					StudentActivity stuActivity1 = (StudentActivity)stuActivityList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(stuActivity1.getActivityName() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(stuActivity1.getActivityName());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(stuActivity1.getStudentId() != null){ 
					    cell = row.createCell((short) 1);//创建第i+1行第0列 
					    cell.setCellValue(stuActivity1.getStudentId());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    }
				    if(stuActivity1.getStudentName() != null){ 
					    cell = row.createCell((short) 2); //创建第i+1行第1列 
					    cell.setCellValue(stuActivity1.getStudentName());//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(stuActivity1.getCollege() != null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(stuActivity1.getCollege().getCollegeName()); 
					    cell.setCellStyle(style); 
				    } else {
				    	cell = row.createCell((short) 3);
				    	cell.setCellValue("");
				    	cell.setCellStyle(style);
				    }
				    if(stuActivity1.getActivityContent()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(stuActivity1.getActivityContent()); 
					    cell.setCellStyle(style); 
				    }
				    if(stuActivity1.getStartTime() != null){ 
					    cell = row.createCell((short) 5); 
					    cell.setCellValue(stuActivity1.getStartTime().toString().substring(0,10)); 
					    cell.setCellStyle(style); 
				    } 
				    if(stuActivity1.getEndTime()!= null){ 
					    cell = row.createCell((short) 6); 
					    cell.setCellValue(stuActivity1.getEndTime().toString().substring(0,10)); 
					    cell.setCellStyle(style); 
				    }
				    
				    cell = row.createCell((short) 7); 
				    cell.setCellValue(stuActivity1.getFee()); 
				    cell.setCellStyle(style); 
				    
				    if(stuActivity1.getCurrency() != null){ 
					    cell = row.createCell((short) 8); 
					    cell.setCellValue(stuActivity1.getCurrency()); 
					    cell.setCellStyle(style); 
				    }
				    
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("学生参与活动表（夏令营）.xls");
		return null; 
	}
	
	//导出班级
	public String exportInterClass() throws Exception {
		//获取班级对象
		List<InternationalClass> classList = classDao.queryAllClass();
		String []tableHeader={"班级名称","年级","专业"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(classList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("班级表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < classList.size() ;i++){    
					//获取InternationalStudent对象
					InternationalClass class1 = (InternationalClass)classList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(class1.getClassName() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(class1.getClassName());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(class1.getGrade() != null){ 
					    cell = row.createCell((short) 1);//创建第i+1行第0列 
					    cell.setCellValue(class1.getGrade());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    }
				    if(class1.getMajor() != null){ 
					    cell = row.createCell((short) 2); //创建第i+1行第1列 
					    cell.setCellValue(class1.getMajor());//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				   
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("班级表.xls");
		return null; 
	}
	
	//导出出国学生
	public String exportOverseasStudent() throws Exception{
		//获取出国学生对象
		List<OverseasStudent> studentList = osd.queryOverseas("");
		String []tableHeader={"学号","姓名","性别","班级","专业","出国的院校","出国院校就读专业","出国时间","类型","出国津贴"}; 
		short cellNumber=(short)tableHeader.length;//表的列数 
		workbook = new HSSFWorkbook(); //创建一个Excel 
		style = workbook.createCellStyle(); //设置表头的类型 
		style.setAlignment(HorizontalAlignment.CENTER); 
		style1 = workbook.createCellStyle(); //设置数据类型 
		style1.setAlignment(HorizontalAlignment.CENTER); 
		HSSFFont font = workbook.createFont(); //设置字体 
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet 
		HSSFHeader header = sheet.getHeader();//设置sheet的头 
		try {              
			//根据是否取出数据，设置header信息 
			if(studentList.size() < 1 ){ 
				header.setCenter("查无资料"); 
			}else{ 
				header.setCenter("出国学生表"); 
				row = sheet.createRow(0); 
				row.setHeight((short)400);
				//表头
				for(int k = 0;k < cellNumber;k++){
					cell = row.createCell((short) k);//创建第0行第k列 
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
					sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度 
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色. 
					font.setFontHeight((short)350); //设置单元字体高度 
					style1.setFont(font);//设置字体风格 
					cell.setCellStyle(style1); 
				} 
				// 给Excel填充数据                         
				for(int i = 0 ;i < studentList.size() ;i++){    
					//获取InternationalStudent对象
					OverseasStudent student1 = (OverseasStudent)studentList.get(i); 
				    row = sheet.createRow((short) (i + 1));//创建第i+1行 
				    row.setHeight((short)400);//设置行高 
				    
				    if(student1.getStudentId() != null){ 
					    cell = row.createCell((short) 0);//创建第i+1行第0列 
					    cell.setCellValue(student1.getStudentId());//设置第i+1行第0列的值 
					    cell.setCellStyle(style);//设置风格 
				    } 
				    if(student1.getStudentName() != null){ 
					    cell = row.createCell((short) 1); //创建第i+1行第1列 
					    cell.setCellValue(student1.getStudentName());//设置第i+1行第1列的值 
					    cell.setCellStyle(style); //设置风格 
				    } 
				    if(student1.getSex() != null){ 
					    cell = row.createCell((short) 2); 
					    cell.setCellValue(student1.getSex()); 
					    cell.setCellStyle(style); 
				    } 
				    if(student1.getClasses().getClassName()!= null){ 
					    cell = row.createCell((short) 3); 
					    cell.setCellValue(student1.getClasses().getClassName()); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getClasses().getMajor()!= null){ 
					    cell = row.createCell((short) 4); 
					    cell.setCellValue(student1.getClasses().getMajor()); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getCollege().getCollegeName() != null){ 
					    cell = row.createCell((short) 5); 
					    cell.setCellValue(student1.getCollege().getCollegeName()); 
					    cell.setCellStyle(style); 
				    }
				    if(student1.getOutMajor() != null){ 
					    cell = row.createCell((short) 6); 
					    cell.setCellValue(student1.getOutMajor()); 
					    cell.setCellStyle(style); 
				    } 
				    if(student1.getOutTime() != null){ 
					    cell = row.createCell((short) 7); 
					    cell.setCellValue(student1.getOutTime().toString().substring(0,10)); 
					    cell.setCellStyle(style); 
				    } 
				    if(student1.getCollege().getType() != null){ 
					    cell = row.createCell((short) 8); 
					    cell.setCellValue(student1.getCollege().getType()); 
					    cell.setCellStyle(style); 
				    }
				    
				    cell = row.createCell((short) 9); 
				    cell.setCellValue(""+student1.getSubsidy()); 
				    cell.setCellStyle(style); 
				    
				} 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		outputSetting("出国学生表.xls");
		return null; 
	} 
	
	//固定配置
	public void outputSetting(String fileName) {
		HttpServletResponse response = null;//创建一个HttpServletResponse对象 
		OutputStream out = null;//创建一个输出流对象 
		try { 
			response = ServletActionContext.getResponse();//初始化HttpServletResponse对象 
			out = response.getOutputStream();// 得到输出流
	        response.setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes(),"ISO-8859-1"));//filename是下载的xls的名 
	        response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
	        response.setHeader("Pragma","No-cache");//设置头 
	        response.setHeader("Cache-Control","no-cache");//设置头 
	        response.setDateHeader("Expires", 0);//设置日期头 
	        workbook.write(out); 
	        out.flush(); 
	        workbook.write(out); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{ 
			try{ 
				if(out!=null){ 
					out.close(); 
				} 
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		} 
	}
	
}
