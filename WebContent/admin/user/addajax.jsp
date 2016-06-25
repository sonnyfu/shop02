<%@page import="cn.info.model.User"%>
<%@page import="cn.info.dao.DAOFactory"%>
<%@page import="cn.info.dao.IUserDao"%>
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
		IUserDao userDao=DAOFactory.getUserDao();
		//userDao.add(request.getParameter("username"));
	%>
</body>
</html>