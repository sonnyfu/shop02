<%@page import="cn.info.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	User u=(User)session.getAttribute("loginUser");//隐式转换
	if(u==null){
		response.sendRedirect(request.getContextPath()+"/logininput.jsp");
	return;
	}
%>
<div style="text-align: right;border-bottom:1px solid #000">
	欢迎<%=u.getNickname() %>!&nbsp;
	<a href></a>
	<a href="<%=request.getContextPath()+"/admin/user/list.jsp" %> ">用户管理</a>
	<a href="<%=request.getContextPath()+"/logininput.jsp" %>">退出系统</a>
</div>