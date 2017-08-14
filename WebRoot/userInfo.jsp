<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.digitalweb.model.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>四叶草数码商城产品列表</title>
	<link type="text/css" rel="stylesheet" href="<%=path %>/style/main.css" />
    </head>
    <body>
	<div id="header">
		<div id="header_inside">
			<p><img src="<%=path %>/images/header.jpg" alt="四叶草数码商城banner" usemap="#Map" />
<map name="Map" id="Map"><area shape="rect" coords="844,63,869,94" href="<%=path %>/buy_car.html" alt="" />
<area shape="rect" coords="684,93,714,118" href="<%=path %>/index.html" alt="" />
<area shape="rect" coords="769,145,797,168" href="<%=path %>/regist.html" alt="" />
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
	<div id="pro_list1">
	 <jsp:useBean id="user" class="com.digitalweb.model.User" scope="session"></jsp:useBean>
	<%
	if(user==null){
	out.print("<h1>请重新登陆！</h1>");
	}else{%>
			  <form name="registform" id="registform" action="UserServlet" method="post" >
				<table>
				  <tr>
					<th>账号：</th>
					<td><input  type="text" name="userName" value="<%=user.getUserName() %>" size="20" maxlength="20" /></td>
				  </tr>
				  <tr>
					<th>积分：</th>
					<td><input  type="text" name="score" size="20" value="<%=user.getScore() %>" /></td>
				  </tr>
				  <tr>
					<th>真实姓名：</th>
					<td><input  type="text" name="realName" value="<%=user.getRealName() %>" size="20" maxlength="20" /></td>
				  </tr>
				  <tr>
					<th>用户性别：</th>
					<td>
					<%if(user.getSex().equals("男")){ %>
					  <input name="sex" type="radio" value="男" checked />男&nbsp;
					  <input type="radio" name="sex" value="女" />女</td>
					  <%}else{ %>
					   <input name="sex" type="radio" value="男" />男&nbsp;
					  <input type="radio" name="sex" value="女" checked />女</td>
					  <%} %>
				  </tr>
				  <tr>
					<th>配送地址：</th>
					<td><input  type="text" name="address"  size="20" value="<%=user.getAddress() %>" maxlength="20" /></td>
				  </tr>
				  <tr>
			 <%String[] questiones={"您的出生地是？","您父亲的生日是？","您母亲的生日是？","您身份证号码的后6位是？","您手机号码的后6位是？"
			 ,"您手机号码的后6位是？","您父亲的姓名是？","您母亲的姓名是？"}; %>
					<th>密码保护问题：</th>
					<td><select name="question">
						<option value="">--请选择--</option>
						<%for(String q:questiones){
						if(q.equals(user.getQuestion())){
							out.print("<option value='"+q+"' selected>"+q+"</option>");
						}else{
							out.print("<option value='"+q+"'>"+q+"</option>");
						}
						}
						%>
					  </select>
					</td>
				  </tr>

				  <tr>
					<th>答案：</th>
					<td><input  type="text" name="answer" value="<%=user.getAnswer() %>" size="20" maxlength="20" /></td>
				  </tr>
				  <tr>
					<th>邮箱：</th>
					<td><input  type="text" name="email" value="<%=user.getEmail() %>" size="20" maxlength="20" /></td>
				  </tr>
				  <tr>
					<th>请选择关注的商品类别：</th>
					<td>
					<%
					String favorate[] = {"手机","电脑","相机","其他"};
					for(String f : favorate) {
					boolean flag = false;
					if(user.getFavorate()!=null&&user.getFavorate().contains(f))
						flag=true;
				
					if(flag){
					 out.println("<input type='checkbox'  name='favorate' value="+f+" checked />"+f);
					 }else{
					 out.println("<input type='checkbox'  name='favorate' value="+f+" />"+f);
					 }
					}
					%>
					</td>
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
	<%}	%>
	
	</div><!-- end of pro_list1 -->
	<div id="right">
		<div id="login">
		<%
		if(user==null){
		%>
			<form id="loginform" name="loginform" method="post" action="<%=path %>/LoginServlet"" >
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
			<li><a href="<%=path %>/product/list_cart.jsp">我的购物车</a></li>
			<li><a href="<%=path %>/product/list_order.jsp">我的订单</a></li>
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
	
	</div>
	<div id="footer">
		<p>copyright &copy;.All Rights Reserverd. Design form XXX </p>
	</div>
    </body>
</html>
