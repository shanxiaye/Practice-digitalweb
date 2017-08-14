package com.digitalweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digitalweb.model.Cart;
import com.digitalweb.model.User;

public class CartServlet extends HttpServlet {

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
		String nextPage = "product/list_cart.jsp";
		HttpSession session = request.getSession();
		//定义cookie前缀
		//1.接收购物车参数
		HashMap<String,String[]> map = (HashMap<String,String[]>) request.getParameterMap();

		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		if(cartList == null)
			cartList = new ArrayList<Cart>();
		if(map.get("flag")[0].equals("add")){
			//2.封装Cart对象
			Cart cart = new Cart();
			cart.setId(Integer.parseInt(map.get("id")[0]));
			cart.setName(map.get("name")[0]);
			cart.setSale(Double.parseDouble(map.get("sale")[0]));
			cart.setPrice(Double.parseDouble(map.get("price")[0]));
			cart.setPic(map.get("pic")[0]);
			cart.setNum(Integer.parseInt(map.get("num")[0]));
			//3.先判断cartList中是否有同样的商品
			boolean hasCart = false;
			for(Cart c : cartList){
				if(c.getId() == cart.getId()){
					c.setNum(c.getNum()+cart.getNum());
					hasCart = true;
					break;
				}
			}
			if(!hasCart)
				cartList.add(cart);
			//5.将购物车列表放回session
			session.setAttribute("cartList",cartList);
			
		}else if(map.get("flag")[0].equals("delete")){
			String id = request.getParameter("id");
			for(Cart c:cartList){
				if(c.getId() == Integer.parseInt(id)){
					cartList.remove(c);
					break;
				}
			}
		}else if(map.get("flag")[0].equals("update")){
			String id = request.getParameter("id");
			String num = request.getParameter("num");
			for(Cart c : cartList){
				if(c.getId() == Integer.parseInt(id)){
					c.setNum(Integer.parseInt(num));
					break;
				}
			}
		}
		response.sendRedirect(nextPage);
  }

}
