package com.international.actions.college;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
public class DocDownloadAction extends ActionSupport{
	/**
     * 文件下载
     */
    //获取要下载的文件的文件名
    private String fileName;

	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDownFileName() {
		String downFileName=fileName;
		try {
			downFileName=new String(downFileName.getBytes(),"ISO8859-1");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return downFileName;
	}
	//返回文件流的方法
    public InputStream getAttrInputStream(){
    	
        return ServletActionContext.getServletContext().getResourceAsStream("/upload"+"\\"+fileName);
    }
    public String execute() throws Exception {
    	
		return "download";
	}
}
