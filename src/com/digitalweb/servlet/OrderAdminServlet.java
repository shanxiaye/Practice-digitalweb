package com.digitalweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;

import com.digitalweb.model.Order;
import com.digitalweb.model.Rank;
import com.digitalweb.util.Page;
import com.digitalweb.impl.OrderDaoImpl;

public class  OrderAdminServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @paramOrderAdminServlet response the response send by the server to the client
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
		String oprType = request.getParameter("flag");
		String nextPage = "";
		boolean flag = true;
		OrderDaoImpl odi = new OrderDaoImpl();
		HttpSession session = request.getSession();
		if(oprType.equals("list")){//列表
			ArrayList<Order> orderList = (ArrayList<Order>)session.getAttribute("orderList");
			int curPage = 1;
			if(orderList==null){
				orderList = odi.list();
				session.setAttribute("orderList", orderList);
			}else{
//				String strCurPage = request.getParameter("page");//list_order1.jsp
				String strCurPage = request.getParameter("pageNo");//list_order2.jsp
				if(strCurPage!=null){
					curPage =Integer.parseInt(strCurPage);
				}
			}
			Page page = new Page(orderList.size(),curPage,3);
			session.setAttribute("page", page);
			nextPage = "admin/list_order2.jsp";
		}else if(oprType.equals("detail")){//明细
			String id = request.getParameter("id");
			session.setAttribute("order", odi.getOrderById(Integer.parseInt(id)));
			nextPage = "admin/order_detail.jsp";
		}else if(oprType.equals("send")){//发货
			String  id = request.getParameter("id");
			flag = odi.send(Integer.parseInt(id));
			if(flag)
				nextPage="OrderAdminServlet?flag=list";
			else
				out.println("发货失败");
		}else if(oprType.equals("search")){
			String field = request.getParameter("searchType");
			String key = request.getParameter("key");
			ArrayList<Order> orderList = odi.search(field,key);
			session.setAttribute("orderList", orderList);
			nextPage = "admin/list_order.jsp";
		}else if(oprType.equals("rank")){
			int count = Integer.parseInt(request.getParameter("count"));
			List<Rank> rankList = odi.rankcall(count);
			session.setAttribute("rankList", rankList);
			nextPage = "admin/list_rank.jsp";
		}else if(oprType.equals("stat")){
			String graphType = request.getParameter("graphType");
			String top = request.getParameter("top");
			String field = request.getParameter("field");
			JFreeChart chart = odi.statOrder(Integer.parseInt(top), graphType,field);
			StandardEntityCollection sec = new StandardEntityCollection(); 
			ChartRenderingInfo info = new ChartRenderingInfo(sec); 
			String filename = ServletUtilities.saveChartAsPNG(chart,500,300,info,request.getSession()); 
			nextPage = "admin/orderstat.jsp?filename="+filename;
		}

		if(flag)
			response.sendRedirect(nextPage);
	}

}
