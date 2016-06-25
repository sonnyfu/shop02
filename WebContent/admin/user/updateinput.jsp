<%@page import="cn.info.model.User"%>
<%@page import="cn.info.dao.DAOFactory"%>
<%@page import="cn.info.dao.IUserDao"%>
<%@page import="cn.info.utils.ValidateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="inc.jsp"/><!-- 包含另一个人页面（不同的布局） -->
<br>
	<%
		IUserDao userDao=DAOFactory.getUserDao();
		User u=(User)session.getAttribute("loginUser");
	%>
<form action="update.jsp" method="post">
	<input type="hidden" name="id" value="<%=u.getId()%>">
	<table align="center" width="500" border="1">
		
		<tr>
			<td>用户名称：
			</td>
			<td><input type="text" name="username" value=<%=u.getUsername() %> />
			 </td>
			 
			<!-- 若出错，从map获取值 -->
			
		</tr>
		<tr>
			<td>用户昵称：</td><td><input type="text" name="nickname" value=<%=u.getNickname() %> /></td>
		</tr>
		<tr>
			<td>用户密码</td><td><input type="password" name="password" value=<%=u.getPassword()%>/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="更新" /></td>
		</tr>
	</table>
</form>
</body>
</html>