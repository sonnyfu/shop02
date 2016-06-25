<%@page import="cn.info.utils.ValidateUtil"%>
<%@page import="cn.info.model.ShowException"%>
<%@page import="cn.info.dao.DAOFactory"%>
<%@page import="cn.info.dao.IUserDao"%>
<%@page import="cn.info.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%//
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	String nickname=request.getParameter("nickname");
	boolean validate=ValidateUtil.validateNull(request, new String[]{"username","password","nickname"});
	User user=new User();
	if(!validate){
		//return;跳出本页面代码
	
	%>
	<jsp:forward page="addInput.jsp"></jsp:forward>
	<%}//
	user.setNickname(nickname);
	user.setPassword(password);
	user.setUsername(username);
	user.setType(0);
	user.setStatus(0);
	IUserDao userDao=DAOFactory.getUserDao();
	//sendirect不能？传中文
	try{                                                                                                                           
		userDao.add(user);
		System.out.print(10);
	
%>
添加用户成功！<a href="addInput.jsp">继续添加</a><a href="list.jsp">显示用户列表</a>
<%
	} catch(ShowException e) {
%>
<h2 style="color:red">发生错误:<%=e.getMessage() %></h2>
<%
	}
%>