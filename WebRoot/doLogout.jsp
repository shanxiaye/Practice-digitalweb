<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.digitalweb.model.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'doLogout.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%
    User user = (User)session.getAttribute("user");
	HashMap<String,User> userMap = (HashMap<String,User>) application.getAttribute("userMap");
	if(userMap!=null&&userMap.size()!=0)
		userMap.remove(String.valueOf(user.getId()));
	session.setMaxInactiveInterval(0);
	response.sendRedirect("index.jsp");
    %>
  </body>
</html>
