<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="cn.info.utils.ValidateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户添加</title>
</head>
<body>

<jsp:include page="inc.jsp"/><!-- 包含另一个人页面,但属于动态导入（已经编译好，不能跳转）（不同的布局） -->
<br>
<form action="add.jsp" method="post">
	<table align="center" width="500" border="1">
		<tr>
			<td>用户名称：</td>
			<td><input type="text" name="username"/>
			<%=ValidateUtil.showError(request, "username")
			%>
			 </td>
			<!-- 若出错，从map获取值 -->
			
		</tr>
		<tr>
			<td>用户密码：</td><td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td>用户昵称：</td><td><input type="text" name="nickname"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="添加用户" /></td>
		</tr>
	</table>
</form>
</body>
</html>