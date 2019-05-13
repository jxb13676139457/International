package com.international.actions.common;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;


public class ajaxAction {
	public static void toJson(HttpServletResponse response, Object data) throws IOException{
		Gson gson = new Gson();  
        String result = gson.toJson(data);  
        response.setContentType("text/json; charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存  
        PrintWriter out = response.getWriter();  
        out.print(result);  
        out.flush();  
        out.close(); 
	}
}
