<%@page import="cn.info.model.Pager"%>
<%@page import="cn.info.model.User"%>
<%@page import="java.util.List"%>
<%@page import="cn.info.dao.DAOFactory"%>
<%@page import="cn.info.dao.IUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body><!-- 权限控制（页面显示功能，）；2、username的判断再显示  -->
<jsp:include page="inc.jsp"/>
	<%//初始化参数
	/*		int pagesize=5;
		int pageindex=1;
		//首次访问时，若索引号为空，则写以下异常，否则正常传递
		try{
			pageindex=Integer.parseInt(request.getParameter("pageindex"));
		}catch(NumberFormatException e)
		{
			
		} */
		
		String con=request.getParameter("con");
		if(con==null) con = "";//好判断
		IUserDao userDao=DAOFactory.getUserDao();
		Pager<User> pager=userDao.list(con);
		List<User> users=pager.getDates();
		User user=(User)session.getAttribute("loginUser");
		
	%><!--表单是用来排列数据（包含表单元素）  。-->
	<table align="center" border="2" width="800">
		<tr>
		<td colspan=7>
		<form action="list.jsp">
			<input type="text" name="con" value="<%=con %>"/>
			<input type="submit" name=submit value="查询" />
		</form>
		</td>
		</tr>
		<tr>
		<td>用户标识</td><td>用户名</td><td>用户密码</td><td>用户昵称</td>
		<td>用户类型</td><td>状态</td><td>操作</td>
		
		</tr> 
		<%
		for(User u:users){
		%>
		<tr>
		<td><%=u.getId() %></td>
		<td><%=u.getUsername() %></td>
		<td><%=u.getPassword() %></td>
		<td><%=u.getNickname() %></td>
		<td><%//显示并赋予管理员设置普通会员的权限
		if(u.getType()==1){//
			  
			 %>
			管理员
			<%
			if(user.getType()==1){
			%>
			
			<a href="setType.jsp?id=<%=u.getId()%>">设置为普通会员</a>
			<%
			}
			%>
			
		<%
		}else{
					
		%>
		普通会员
		<%
			if(user.getType()==1){
		%>
			
			<a href="setType.jsp?id=<%=u.getId()%>">设置为管理员</a>
			<%
			}
			%>		
		<%
		} 
		%>
		</td>
		<td><%
		if(u.getStatus()==1){
			
			 %>
			启用<a href="setStatus.jsp?id=<%=u.getId() %>">停用</a>
		<%
			}else{
				
				
		%>
			停用<a href="setStatus.jsp?id=<%=u.getId() %>">启用</a>	
		<%} 
		%>
		</td>
		<td><a href="delete.jsp?id=<%=u.getId()%>">删除</a>&nbsp;<a href="updateinput.jsp?id=<%=u.getId() %>">更新</a></td>
		</tr>
		<%
		}
		%>
		<tr>
			<td colspan="7">
			<pg:pager maxPageItems="5" items="<%=pager.getTotalRecord() %>" export="curPage=pageNumber">
				当前第<%=curPage %>页&nbsp;每页显示<%=pager.getPageSize() %>条
			&nbsp;总共<%=pager.getTotalPage() %>页&nbsp;总共<%=pager.getTotalRecord() %>记录
				<pg:first>
				<!--pageurl，包含了路径，传递参数pager.offset的pageoffset  -->
					<a href="<%=pageUrl %>">首页</a>
				</pg:first>
				<pg:prev>
					<a href="<%=pageUrl %>">上一页</a>
				</pg:prev>
				<pg:pages>
					<%
					if(curPage==pageNumber){
						%>
						[<%=pageNumber %>]
						<%
					}else{
					%>
					<a href="<%=pageUrl %>"></a>
					<%} %>
				</pg:pages>
				<pg:next>
					<a href="<%=pageUrl%>">下一页</a>
				</pg:next>
				<pg:last>
					<a href="<%=pageUrl %>">尾页</a>
				</pg:last>
				
			</pg:pager>
			</td>
		</tr>
	</table>
	
</body>
</html>