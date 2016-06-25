<%@page import="cn.info.model.User"%>
<%@page import="cn.info.dao.DAOFactory"%>
<%@page import="cn.info.dao.IUserDao"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		IUserDao uDao=DAOFactory.getUserDao();
		User u=uDao.load(Integer.parseInt(request.getParameter("id")));
		if(u.getType()==1){
			u.setType(0);
		}else{
			u.setType(1);
		}
		uDao.update(u);	
		response.sendRedirect("list.jsp");
	%>
</body>
</html>