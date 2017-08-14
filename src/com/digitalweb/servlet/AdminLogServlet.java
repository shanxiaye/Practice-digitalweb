package com.digitalweb.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digitalweb.impl.AdminDaoImpl;

public class AdminLogServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		   HashMap<String,String[]> map = (HashMap<String,String[]>) request.getParameterMap();
		   AdminDaoImpl ai = new AdminDaoImpl();
		   int flag = 0;
			HttpSession session = request.getSession();
			String nextPage = "admin/login.jsp";
			String loginInfo = "";
		   if(map.get("flag")[0].equals("in")){
			 //方式一
			    String name = request.getParameter("userName");
				String pwd = request.getParameter("pwd");
				
				//2.逻辑判断，验证码：ABCD，用户名：tom，密码：123
				//2.1 先判断验证码
				//2.2 判断用户名
				//2.3 判断密码
				flag = ai.verify(name, pwd);//重点
				if(flag == 1){
					//用户名不存在
					loginInfo = "用户名不存在";
				}else if(flag == 2){
					//密码错误
					loginInfo = "密码错误";
				}else if(flag == 3){
					//登陆成功
					session.setAttribute("admin", name);
					session.removeAttribute("loginInfo");
					nextPage = "admin/main.html";
				}
				session.setAttribute("loginInfo", loginInfo);
		   }else if(map.get("flag")[0].equals("out")){
			   session.setMaxInactiveInterval(0);
		   }
			//页面跳转
			response.sendRedirect(nextPage);
	}

}
