<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.digitalweb.model.*,com.digitalweb.impl.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>四叶草数码商城订单列表</title>
	<link type="text/css" rel="stylesheet" href="../style/main.css" />
    </head>
    <body>
	<div id="header">
		<div id="header_inside">
			<p><img src="../images/header.jpg" alt="四叶草数码商城banner" usemap="#Map" />
	<map name="Map" id="Map"><area shape="rect" coords="844,63,869,94" href="buy_car.html" alt="" />
	<area shape="rect" coords="684,93,714,118" href="../index.html" alt="" />
	<area shape="rect" coords="769,145,797,168" href="../regist.html" alt="" />
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
	<div id="sidebar">
		<h2>商品分类</h2>
		<ul>
			<li class="list1"><a href="#">手机专区</a></li>
			<li><a href="#">存话费送手机</a></li>
			<li class="list1"><a href="#">笔记本电脑</a></li>
			<li><a href="#">数码相机</a></li>
			<li class="list1"><a href="#">单反相机</a></li>
			<li><a href="#">DV摄像机</a></li>
			<li class="list1"><a href="#">平板电脑</a></li>
			<li><a href="#">摄影配件</a></li>
			<li class="list1"><a href="#">其他商品</a></li>
		</ul>
	</div>
        <div id="right">
         <div id="login">
		<%User user = (User)session.getAttribute("user"); 
		if(user==null){
		%>
			<form id="loginform" name="loginform" method="post" action="<%=path %>/LoginServlet" >
				<div><strong>登录名：</strong><input name="txtUser" id="txtUser" size="15" /></div>
				<div><strong>密　码：</strong><input name="txtPassword" type="password" id="txtPassword" size="15" /></div>
				<div>
					<strong>验证码：</strong><input name="verifyCode" id="verifyCode" size="4" />
					<img src="<%=path %>/VerifyCodeServlet"  onclick="this.src='<%=path %>/VerifyCodeServlet'" />
				</div>
				<div><input type="submit" value="登录" name="submit" class="picbut" />　
				<input name="reg" type="button" value="注册用户" class="picbut" onclick="javascript:location.href=('regist.jsp');" />
				</div>
			 </form>
		<%}else{ %>
		<ul>
			<li>欢迎回来，<%=user.getUserName() %></li>
			<li><a href="list_cart.jsp">我的购物车</a></li>
			<li><a href="list_order.jsp">我的订单</a></li>
			<li><a href="<%=path %>/userInfo.jsp">个人信息</a></li>
			<li><a href="../LogoutServlet">退出</a></li>
		</ul>
		<%} %>
		</div>
            <div class="news">
                <p><img src="../images/title3.gif" alt="" width="100" height="30" /></p>
                <ol>
                    <li>24小时送达迟一天退10元</li>
                    <li>支付宝金账户购物全场98折</li>
                    <li>用建行卡购物全场98折</li>
                    <li>工行分期付款0.3%/月手续费</li>
                    <li>7天内无条件退货</li>
                    <li>运输造成的损害我们承担损失 </li>
                </ol>
            </div>
        </div>
	<div id="buycar">
	<%
 OrderDaoImpl odi = new OrderDaoImpl();
 if(user == null){
 	out.print("<h1>请重新登陆！</h1>");
 }else{
 ArrayList<Order> orderList = odi.getOrderByUser(user.getId());
 %>
    <div align="center" style="width: 535px">
      <div align="center" style="width: 535px;margin-left: 30px">
	    	<!--  <img src="images/order.jpg" width="48" height="48" align="left"/>-->
	    	<font size="6" style="margin-left: 100px">我的订单</font>
	    </div>
		<div style="width: 530px">
		<%for(Order order:orderList){ %>
	 <table>
	   <tr>
			<th scope="col" colspan="4">
			订单编号：<span style="font-size: 12px;color: blue;"><%=order.getId() %></span>&nbsp;
			状态：<span style="font-size: 12px;color: blue;"><%=order.getStatus() %></span>&nbsp;
			订单时间：<span style="font-size: 12px;color: blue;"><%=order.getOrdertime() %></span>&nbsp;
			收货人：<span style="font-size: 12px;color: blue;"><%=order.getUserName() %></span>&nbsp;
			<%if(order.getStatus().equals("已发货")){ %>
				<span style="font-size: 12px;color: red;"><a href="../OrderServlet?flag=receive&id=<%=order.getId() %>">确认收货</a></span>
			<%} %>
			</th>
	   </tr>
	<%for(OrderDetail od : order.getDetailList()){ 
	%>
	 <tr>
		<td class="row" width="40%"><%=od.getpName() %></td>
		<td class="row" width="40%"><img src="<%=od.getPic() %>" width="75" height="50" /></td>
		<td class="row" width="10%"><%=od.getPrice() %></td>
		<td class="row" width="10%"><%=od.getNum() %></td>
	</tr>
	<%} %>
	</table>
	<%} %>
		</div>
	</div>
<%} %>
	</div>
	</div>
	<div id="footer">
		<p>copyright &copy;.All Rights Reserverd. Design form XXX </p>
	</div>
    </body>
</html>
