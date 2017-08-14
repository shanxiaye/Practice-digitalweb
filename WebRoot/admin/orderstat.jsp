<%@ page language="java" import="java.util.*,com.digitalweb.model.*,com.digitalweb.impl.OrderDaoImpl" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12}

-->
</style>

</head>

<body>
  <div align="center" style="margin-top: 50px;">
   <form name="statForm" action="../OrderAdminServlet" method="post">
	  <input type="hidden" name="flag" value="stat" />
	  <table cellspacing="0" cellpadding="0">
	   <tr>
		 <td class="row">查询类型
		  	<input type="radio" name="graphType" value="column" checked="checked" />柱状图
 			<input type="radio" name="graphType" value="pie" />饼图
		</td>
	   </tr>
	     <tr>
			<td>
		统计字段：<select name="field">
				<option value="user">用户购买情况</option>
				<option value="book">图书销量情况</option>
				</select>
			</td>
	  </tr>
	   <tr>
			<td>
			销售前<input type="text" name="top" id="top" size="3" />位 &nbsp;&nbsp; 
			<input type="submit" value="查询" />
			</td>
	  </tr>
	  
	  </table>
	  </form>
    </div>
   	<div align="center"  style="margin-top: 50px;">
	<%	String filename = request.getParameter("filename");
		if(filename!=null&&filename.length()>0){
			String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
	%>
		<img src="<%=graphURL %>" width=500 height=300 border=0 usemap="#<%=filename %>" /> 
	<%} %>
	  </div>

</body>
</html>
