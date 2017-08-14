package com.digitalweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digitalweb.impl.UserDaoImpl;
import com.digitalweb.model.User;
import com.digitalweb.util.mail.MailSenderProperties;
import com.digitalweb.util.mail.SimpleMailSender;


public class UserServlet extends HttpServlet {

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
		String nextPage = "UserServlet?flag=list";
		HashMap<String,String[]> map = (HashMap<String,String[]>) request.getParameterMap();
		HttpSession session = request.getSession();
		boolean flag = true;
		UserDaoImpl udi = new UserDaoImpl();
		if(map.get("flag")[0].equals("list")){
			session.setAttribute("userList", udi.list());
			nextPage = "admin/list_user.jsp";
		}else if(map.get("flag")[0].equals("cancel")){
			String id = map.get("id")[0];
			flag = udi.cancel(Integer.parseInt(id));
		}else if(map.get("flag")[0].equals("active")){
			String id = map.get("id")[0];
			flag = udi.active(Integer.parseInt(id));
		}else if(map.get("flag")[0].equals("findPwd")){
			nextPage = "findPwd.jsp";
			User user = udi.getUserByName(map.get("userName")[0]);
			if(user==null){
				session.setAttribute("info", "很抱歉,账户不存在");
			}else if(user.getEmail()==null||user.getEmail().equals("")){
				session.setAttribute("info", "很抱歉,没有绑定联系邮箱！");
			}else{
				String newPwd = genRandomPwd();
				MailSenderProperties mailInfo = new MailSenderProperties();   
			    mailInfo.setMailServerHost("smtp.163.com");   
			    mailInfo.setMailServerPort("25");   
			    mailInfo.setValidate(true);   
			    mailInfo.setUserName("******@163.com");   
			    mailInfo.setPassword("********");//您的邮箱密码   
			    mailInfo.setFromAddress("*******@163.com");   
			    mailInfo.setToAddress(user.getEmail());   
			    mailInfo.setSubject("找回密码");   
			    mailInfo.setContent(newPwd);   
			     //这个类主要来发送邮件  
			    SimpleMailSender sms = new SimpleMailSender();  
			   if(sms.sendTextMail(mailInfo)){//发送文体格式
					flag = udi.reSetPwd(user.getId(),newPwd);
			   }
			}
			
		}
		response.sendRedirect(nextPage);
	}
	public String genRandomPwd(){
		StringBuffer sb = new StringBuffer();
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		int index, len = ch.length;
		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			index = r.nextInt(len);
			sb.append(ch[index]);
		}
		return sb.toString();
	}
	
}
