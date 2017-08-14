package com.digitalweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digitalweb.impl.UserDaoImpl;
import com.digitalweb.model.User;
import com.digitalweb.util.MyBASE64;
import com.digitalweb.util.MD5Util;
import com.digitalweb.util.MyMD5Util;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		User user = new User();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**		
	 * 接收方式一
	 * String userName = request.getParameter("userName");
	 * String password = request.getParameter("password");
	 * String realName = request.getParameter("realName");
	 * String sex = request.getParameter("sex");
	 * String address = request.getParameter("address");
	 * String question = request.getParameter("question");
	 * String answer = request.getParameter("answer");
	 * String email = request.getParameter("email");
	 * String fs[] = request.getParameterValues("favorate");
	 * String favorate = "";
	 * for(String f:fs){
	 *     favorate += f+",";
	 *   }
	 * user.setAddress(address);
	 * user.setAnswer(answer);
	 * user.setEmail(email);
	 * user.setFavorate(favorate.substring(0,favorate.length()-1));
	 * user.setPassword(password);
	 * user.setQuestion(question);
	 * user.setRealName(realName);
	 * user.setRegDate(sdf.format(date));
	 * user.setSex(sex);
	 * user.setUserName(userName);
	 * user.setRegDate(sdf.format(date));
	**/
//		接收方式二
		HashMap<String,String[]> map = (HashMap<String,String[]>) request.getParameterMap();
		user.setAddress(map.get("address")[0]);
		user.setAnswer(map.get("answer")[0]);
		user.setEmail(map.get("email")[0]);
		String favorate = "";
		if(map.get("favorate") != null){
			for(String s : map.get("favorate")){
				favorate += s+",";
			}
			user.setFavorate(favorate.substring(0,favorate.length()-1));
		}
		user.setQuestion(map.get("question")[0]);
		user.setRealName(map.get("realName")[0]);
		user.setSex(map.get("sex")[0]);
		user.setUserName(map.get("userName")[0]);
		user.setRegDate(sdf.format(date));
		//MD5加密方式
//		String pwdMD5;
//		try {
//			pwdMD5 = MyMD5Util.getEncryptedPwd(map.get("password")[0]);
//			user.setPassword(pwdMD5);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			String pwdBASE64 = MyBASE64.encryptBASE64(map.get("password")[0]);
			user.setPassword(pwdBASE64);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDaoImpl ui = new UserDaoImpl();
		if(ui.add(user)){
			out.print("<script type='text/javascript'>alert('注册成功！')</script>");
		}else{
			out.print("<script type='text/javascript'>alert('注册失败！')</script>");
		}
		response.setHeader("refresh", "2;URL=index.jsp");
		out.flush();
		out.close();
	}

}
