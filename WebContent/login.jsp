<%@page import="cn.info.model.User"%>
<%@page import="cn.info.model.ShowException"%>
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
	<%//页面访问控制，为了安全，服务器减轻负担
		try{
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			IUserDao userdao=DAOFactory.getUserDao();
			User u=userdao.login(username, password);
			session.setAttribute("loginUser", u);
		response.sendRedirect(request.getContextPath()+"/admin/user/list.jsp");//根路径，"/"开头是以根目录（防止移动文件（相对路径）发生错误）开始的，
		}catch(ShowException e){//
		%>
		<h2 style="color:red">发生错误：<%=e.getMessage() %></h2>
		<%	
		}
	%>
</body>
</html>