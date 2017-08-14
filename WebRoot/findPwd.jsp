<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>四叶草数码商城</title>
		<link type="text/css" rel="stylesheet" href="style/main.css" />
    </head>
    <body>
	<div id="header">
		<div id="header_inside">
			<p><img src="images/header.jpg" alt="四叶草数码商城banner" usemap="#Map" />
<map name="Map" id="Map"><area shape="rect" coords="844,63,869,94" href="product/buy_car.html" alt="" />
<area shape="rect" coords="684,93,714,118" href="index.html" alt="" />
<area shape="rect" coords="769,145,797,168" href="regist.html" alt="" />
</map></p>		
			<div id="menu">
				<ul>
					<li><a href="<%=path %>/index.jsp" class="btn_active">网站首页</a></li>
					<li><a href="<%=path %>/product/list_product.jsp">商品列表</a></li>
					<li><a href="<%=path %>/product/products_hot.html">热卖产品</a></li>
					<li><a href="#">最新活动</a></li>
					<li><a href="<%=path %>/aboutus.html">关于我们</a></li>
					<li><a href="#">联系我们</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="allcontent">
	<div id="regist">
		<div id="registtop">
			<h2>找回密码</h2>
		</div>
		<div id="registmessage">
			  <form name="registform" id="registform" action="UserServlet?flag=findPwd" method="post" >
				<table>
				  <tr>
					<th>账号：</th>
					<td><input  type="text" name="userName"  size="20" maxlength="20" /></td>
				  </tr>
				  
				   <tr>
					<td colspan="2" align="center">
					<input type="submit" name="submit" value=" 提交" class="picbut" />
					  &nbsp;&nbsp;
					<input name="reset" type="reset" value="清空" class="picbut" />
					
					  </td>
				  </tr>
				</table>
			  </form>
			  </div>
	</div>
	</div>
		<div id="footer">
			<p>copyright &copy;.All Rights Reserverd. Design form XXX </p>
		</div>
    </body>
</html>
