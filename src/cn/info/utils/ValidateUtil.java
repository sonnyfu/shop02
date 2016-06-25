package cn.info.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
//request在java也可以运用；封装函数根据代码的重复使用才设计出来；
public class ValidateUtil {//空才传送值
	public static boolean validateNull(HttpServletRequest request,String[] fields) {
		boolean validate=true;
		Map<String,String> errorMsg=new HashMap<String,String>();
		for(String fields1:fields){
			String value=request.getParameter(fields1);
			if (value==null||"".equals(value.trim())) {
				validate=false;
				errorMsg.put(fields1, fields1+"不能为空");
			}
		}//getAttribute在前台交互，getParameter服务器
		if(!validate)request.setAttribute("errorMsg", errorMsg);
		return validate;
	}
	
	public static String showError(HttpServletRequest request,String field) {
		Map<String, String> errMeg=(Map<String, String>) request.getAttribute("errorMsg");
		if(errMeg==null) return "";
		String msg=errMeg.get(field);
		if(msg==null) return "";//若是空格，则值为null；
		return msg;
	}
}
