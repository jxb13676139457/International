package com.international.dao;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;

import com.international.model.ExchangeStudent;
import com.international.model.InternationalStudent;

public class ImportExcelDao {
	
	InternationalStudent interStudent;
	ExchangeStudent exchangeStudent;
	SessionFactory sessionFactory;
	ExchangeStuDao esd;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public InternationalStudent getInterStudent() {
		return interStudent;
	}
	public void setInterStudent(InternationalStudent interStudent) {
		this.interStudent = interStudent;
	}
	public ExchangeStudent getExchangeStudent() {
		return exchangeStudent;
	}
	public void setExchangeStudent(ExchangeStudent exchangeStudent) {
		this.exchangeStudent = exchangeStudent;
	}
	
	public boolean importExStudentExcel(File excelFile) {
//		//InternationalStudent student = (InternationalStudent) this.getSession().getAttribute("student");
//		
//		//得到要解析的excel文件
//		File file = new File(savePath);
//		try {
//		FileInputStream fs = new FileInputStream(file);
//		// 初始化一个工作簿
//		HSSFWorkbook hwb = new HSSFWorkbook(fs);
//		// 第一张表单
//		HSSFSheet sheet = hwb.getSheetAt(0);
//		HSSFRow row = null;
//		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
//		for (int i = 0; i <hwb.getNumberOfSheets(); i++) {
//			sheet = hwb.getSheetAt(i);
//			// 遍历该工作表所有的行，j表示行数  getPhysicalNumberOfRows()表示得到行的总数
//			for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
//				row = sheet.getRow(j);
//				//String studentId = row.getCell((short)0).getNumericCellValue();
//				String studentId =Integer.toString((int) row.getCell((short) 0).getNumericCellValue());
//				String studentName = Integer.toString((int) row.getCell((short) 1).getNumericCellValue());
//				String stId = interStudent.getStId();
//				String orgId = student.getOrganization().getOrgId();
//				String testName = row.getCell(2).toString();
//				String difficulty = row.getCell(3).toString();
//				Date joinTime =new Date();
//				String testType = row.getCell(4).toString();
//				String principleType = row.getCell(5).toString();
//				String answer = row.getCell(6).toString();
//				String isExec = row.getCell(7).toString();
//				String testTitle = row.getCell(8).toString();
//				Integer score = (int) row.getCell(9).getNumericCellValue();
//				Test test = new Test(testId, subId, stId, orgId, testName,
//				difficulty, joinTime, testType, principleType,
//				answer, isExec, testTitle, score);
//				iTestDao.save(test);
//			}
//		}
//		} catch (Exception e) {
//		e.printStackTrace();
//		}
		
		try {
			FileInputStream fileInputStream = new FileInputStream(excelFile);
			//boolean is03Excel = excelName.matches("^.+\\.(?i)(xls)$");
			//Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream) : new XSSFWorkbook(fileInputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println("excel的数据行数："+sheet.getPhysicalNumberOfRows());
			if(sheet.getPhysicalNumberOfRows()>2){
				//Exchange user = null;
				for(int k=2;k<sheet.getPhysicalNumberOfRows();k++){
					HSSFRow row = sheet.getRow(k);
					//学号
					HSSFCell cell0 = row.getCell((short) 0);
					exchangeStudent.setStudentNo(cell0.getStringCellValue());
					//姓名
					HSSFCell cell1 = row.getCell((short) 1);
					exchangeStudent.setStudentName(cell1.getStringCellValue());
					//性别
					HSSFCell cell2 = row.getCell((short) 2);
					exchangeStudent.setSex(cell2.getStringCellValue());
					//班级
					HSSFCell cell3 = row.getCell((short) 3);
					exchangeStudent.setClassName(cell3.getStringCellValue());
					//专业
					HSSFCell cell4 = row.getCell((short) 4);
					exchangeStudent.setMajor(cell4.getStringCellValue());
					//交换开始时间
					HSSFCell cell5 = row.getCell((short) 5);
					exchangeStudent.setStartTime(cell5.getDateCellValue());
					//交换结束时间
					HSSFCell cell6 = row.getCell((short) 6);
					exchangeStudent.setEndTime(cell6.getDateCellValue());
					//交换院校
					HSSFCell cell7 = row.getCell((short) 7);
					exchangeStudent.setExchangeCollege(cell7.getStringCellValue());
					//5、保存
					esd = new ExchangeStuDao();
					System.out.println("esd:"+esd);
					if(esd.addExStudent(exchangeStudent)) {
						System.out.println("导入数据到出国学生表成功");
					}else {
						System.out.println("导入数据失败");
					}
				}
				fileInputStream.close();
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
