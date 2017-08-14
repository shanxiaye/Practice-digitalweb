<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>四叶草数码商城留言板</title>
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
					<li><a href="index.jsp" class="btn_active">网站首页</a></li>
				<li><a href="product/list_product.jsp">商品列表</a></li>
				<li><a href="product/products_hot.html">热卖产品</a></li>
				<li><a href="#">最新活动</a></li>
				<li><a href="aboutus.html">关于我们</a></li>
				<li><a href="#">联系我们</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="allcontent">
	<div id="message">
		<p><img src="images/message.gif" alt="四叶草数码商城留言板" /></p>
		<div id="messagelist">
			<table>
				<tr>
					<th rowspan="3">第一楼<br /><img src="images/boy.jpg" alt="头像男" width="60" height="60" /><br />XXX</th>
					<td colspan="2">标题：XXX</td>
				</tr>
				<tr>
					<td colspan="2" class="msgcontent">XXXXXXXXX</td>
				</tr>
				<tr>
					<td>联系方式：</td>
					<td>发表时间：</td>
				</tr>
			</table>
			<table>
				<tr>
					<th rowspan="3">第二楼<br /><img src="images/boy.jpg" alt="头像男" width="60" height="60" /><br />XXX</th>
					<td colspan="2">标题：XXX</td>
				</tr>
				<tr>
					<td colspan="2"class="msgcontent">XXXXXXXXX</td>
				</tr>
				<tr>
					<td>联系方式：</td>
					<td>发表时间：</td>
				</tr>
			</table>
		</div>
		<div id="publishmessage">
		<form name="messageform" method="post" action="">
		<table>
			<tr>
				<th>昵称：</th>
				<td><input type="text" name="name" value=" " size="20" /></td>
			</tr>
			<tr>
				<th>主题：</th>
				<td><input type="text" name="title" value=" " size="50" /></td>
			</tr>
			<tr>
				<th>内容：</th>
				<td><textarea cols="50" rows="5" name="message"></textarea></td>
			</tr>
			<tr>
				<th>联系方式：</th>
				<td><input type="text" name="contact" value=" " size="50" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="submit" value="提交" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="reset" value="重置" /></td>
			</tr>
		</table>
		</form>
		</div>
	</div>
	<div id="footer">
		<p>copyright &copy;.All Rights Reserverd. Design form XXX </p>
	</div>
	</div>	
    </body>
</html>