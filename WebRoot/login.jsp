<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    //1.接收表单参数:用户名、密码、验证码
	//方式一
	String name = request.getParameter("txtUser");// request.getParameter("txtUser");
   //name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
	String pwd = request.getParameter("txtPassword");
	String code = request.getParameter("verifyCode");
	//2.逻辑判断，验证码：ABCD，用户名：tom，密码：123
	//2.1 先判断验证码
	//2.2 判断用户名
	//2.3 判断密码
	String loginInfo = "";
	if(!code.equalsIgnoreCase("ABCD")){
		loginInfo = "验证码错误";
	}else if(!name.equals("tom")){
		loginInfo = "用户名不存在";
	}else if(!pwd.equals("123")){
		loginInfo.equals("密码错误");
	}else{
		loginInfo = "登陆成功！";
	}
	session.setAttribute("loginInfo",loginInfo);
	response.sendRedirect("index.jsp");
	
	out.print(loginInfo);
   %>
   
  </body>
</html>
