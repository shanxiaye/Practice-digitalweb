package com.digitalweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digitalweb.impl.OrderDaoImpl;
import com.digitalweb.impl.UserDaoImpl;
import com.digitalweb.model.*;

public class OrderServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		String flag = request.getParameter("flag");
		String nextPage = "product/list_order.jsp";
		HttpSession session = request.getSession();
		OrderDaoImpl odi = new OrderDaoImpl();
		UserDaoImpl udi = new UserDaoImpl();
		boolean result = true;
		if(flag.equals("add")){
			User user = (User)session.getAttribute("user");
			ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
			Order order = new Order();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setOrdertime(sdf.format(date));
			order.setStatus("已确认");
			order.setUserId(user.getId());
			double sum = 0.0;
			for(Cart c : cartList){
				OrderDetail od = new OrderDetail();
				od.setPid(c.getId());
				od.setNum(c.getNum());
				order.getDetailList().add(od);
				sum += c.getPrice() * c.getNum();
			}
			if(odi.add(order)){
				//如果订单添加成功，修改积分
					udi.updateScore(user.getId(), (int)(sum/100));
			}else{
				result = false;
				out.print("<h1>订单添加失败！</h1>");
			}
		}else if(flag.equals("receive")){
			String id = request.getParameter("id");
			result = odi.receive(Integer.parseInt(id));
			if(!result)
				out.print("收货失败！");
		}
		if(result){
			session.removeAttribute("cartList");
			response.sendRedirect(nextPage);
		}
	}

}
