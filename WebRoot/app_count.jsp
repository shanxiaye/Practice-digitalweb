<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网页计数</title>
  </head>
  
  <body>
   <%
   Integer counter = (Integer)application.getAttribute("counter");
   if(counter == null){
   		counter = 0;
   }
   	application.setAttribute("counter",++counter);
	
    %>
    <font color=blue size=6>访问次数为：<%=counter %></font>
    <%="2+5" %>
  </body>
</html>
