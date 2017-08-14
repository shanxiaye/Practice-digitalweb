<%@ page language="java" import="java.util.*,com.digitalweb.model.*,com.digitalweb.impl.OrderDaoImpl" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../ckfinder/ckfinder.js"></script>
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
<script type="text/javascript">
function BrowseServer( inputId )
{
	var finder = new CKFinder() ;
	finder.BasePath = '../ckfinder/' ;
	finder.selectActionFunction  = SetFileField ;
	finder.selectActionData  = inputId ;
	finder.popup() ;
}
function SetFileField( fileUrl, data )
{
	document.getElementById( data["selectActionData"] ).value = fileUrl ;
}
</script>
</head>

<body>
 <div align="center" style="margin-top: 10px;">
   <form name="importForm" action="<%=path %>/ProductServlet" method="post">
   	<input name="flag" type="hidden" value="import" />
	  <table cellspacing="0" cellpadding="0">
	   <tr>
		 <td class="row" >选择文件
   			 <input name="fileName" id="fileName" type="text" onclick="BrowseServer('fileName');" />
		</td>
	   </tr>
	   <tr>
		<td class="row" >
			<input type="submit" value="导入"/>
			<input type="reset" value="重置"/>
		</td>
	  </tr>
	   <tr>
		 <td class="row" >
   			<a href="<%=path %>/DownloadServlet">模板文件下载</a>
	   </tr>
	  </table>
	  </form>
	  <%String info = (String)session.getAttribute("importInfo"); %>
	  <div><font color=red size=3><%=info==null?"":info %></font></div>
    </div>
</body>
</html>
