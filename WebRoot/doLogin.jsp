<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="com.digitalweb.model.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'doLogin.jsp' starting page</title>

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
  <jsp:useBean id="user" scope="session" class="com.digitalweb.model.User" />
  <jsp:setProperty name="user" property="userName" param="txtUser" />
  <jsp:setProperty name="user" property="password" param="txtPassword" />
  <%
   	String loginInfo = "";
  	switch(user.verify()){
  	case 1:loginInfo = "用户名不存在";break;
  	case 2:loginInfo = "密码错误";break;
  	case 3:
  		HashMap<String,User> userMap = (HashMap<String,User>) application.getAttribute("userMap");
		if(userMap==null){
			userMap = new HashMap<String, User>();
		}
		userMap.put(user.getUserName(), user);
		application.setAttribute("userMap", userMap);
		response.addCookie(new Cookie("userName",user.getUserName()));
		response.addCookie(new Cookie("password",user.getPassword()));
		break;
  	}
  	session.setAttribute("loginInfo",loginInfo);
  	response.sendRedirect("index.jsp");
   %>
  
  
  
    <%--
   request.setCharacterEncoding("utf-8");
   String name = request.getParameter("txtUser");// request.getParameter("txtUser");
   String pwd = request.getParameter("txtPassword");
   String loginInfo = "";
   if(!name.equals("tom")&&!name.equals("wen")){
   	//用户名不存在
		loginInfo = "用户名不存在";
   }else if(pwd.equals("123")){
   		loginInfo = "密码错误";
   }else{
   		User user = new User();
   		user.setUserName(name);
   		user.setPassword(pwd);
   		session.setAttribute("user",user);
		HashMap<String,User> userMap = (HashMap<String,User>) application.getAttribute("userMap");
		if(userMap==null){
			userMap = new HashMap<String, User>();
		}
		userMap.put(name, user);
		application.setAttribute("userMap", userMap);
		response.addCookie(new Cookie("userName",name));
		response.addCookie(new Cookie("password",pwd));
	}
	response.sendRedirect("index.jsp");
     --%>
  </body>
</html>
