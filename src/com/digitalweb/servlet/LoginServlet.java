package com.digitalweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digitalweb.impl.UserDaoImpl;
import com.digitalweb.model.User;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//1.接收表单参数:用户名、密码、验证码
        HashMap<String,String[]> map = (HashMap<String,String[]>) request.getParameterMap();
		String name = request.getParameter("txtUser");// request.getParameter("txtUser");
		//方式一
//		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		String pwd = request.getParameter("txtPassword");
		String code = request.getParameter("verifyCode");
		
		//2.逻辑判断，验证码：ABCD，用户名：tom，密码：123
		//2.1 先判断验证码
		//2.2 判断用户名
		//2.3 判断密码
		UserDaoImpl ui = new UserDaoImpl();
		int flag = ui.verify(name, pwd);//重点
		HttpSession session = request.getSession();
		String vcode = (String) session.getAttribute("piccode");
		String loginInfo = "";
		if(!code.equalsIgnoreCase(vcode)){
			//验证码错误
			loginInfo = "验证码错误";
		}else if(flag == 1){
			//用户名不存在
			loginInfo = "用户名不存在";
		}else if(flag == 2){
			//密码错误
			loginInfo = "密码错误";
		}else if(flag == 3){
			//登陆成功
			User user = ui.getUserByName(name);
			session.setAttribute("user",user );
			ServletContext  application=(ServletContext)session.getServletContext(); 
			HashMap<String,User> userMap = (HashMap<String,User>) application.getAttribute("userMap");
			if(userMap==null){
				userMap = new HashMap<String, User>();
			}
			userMap.put(String.valueOf(user.getId()), user);
			application.setAttribute("userMap", userMap);
//			List<User> userList = (List<User>) application.getAttribute("userList");
//			if(userList==null){
//				userList = new ArrayList<User>();
//			}
//			userList.add(user);
//			application.setAttribute("userList", userList);
			session.removeAttribute("loginInfo");
			//cookie记录用户名密码
			response.addCookie(new Cookie("userName",name));
			response.addCookie(new Cookie("password",pwd));
		}
		//页面跳转
		session.setAttribute("loginInfo", loginInfo);
		PrintWriter out = response.getWriter();
//		response.setHeader("refresh","10;URL=index.jsp");
		response.sendRedirect("index.jsp");
	}

}
