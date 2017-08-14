<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'doRegist.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <jsp:useBean id="user" class="com.digitalweb.model.User" scope="session"></jsp:useBean>
  <jsp:setProperty name="user" property="*"/>
  <%
	if(user.getUserName()==null||user.getUserName().equals("")||user.getPassword()==null||user.getPassword().equals("")){
		out.print("<script type='text/javascript'>alert('注册失败！')</script>");
	}else{
		out.print("<script type='text/javascript'>alert('注册成功！')</script>");
	}
	response.setHeader("refresh", "2;URL=index.jsp");
   %>
  </body>
</html>
