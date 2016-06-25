<%@page import="cn.info.model.ShowException"%>
<%@page import="cn.info.model.User"%>
<%@page import="cn.info.dao.IUserDao"%>
<%@page import="cn.info.dao.DAOFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int id=Integer.parseInt(request.getParameter("id"));
	IUserDao userDao=DAOFactory.getUserDao();
	User u=userDao.load(id);
	u.setNickname(request.getParameter("nickname"));
	u.setUsername(request.getParameter("username"));
	try{
	userDao.update(u);
	response.sendRedirect("list.jsp");
	%>
	<%
	}catch(ShowException e){
		e.printStackTrace();
	}
	%>
	<
</body>
</html>