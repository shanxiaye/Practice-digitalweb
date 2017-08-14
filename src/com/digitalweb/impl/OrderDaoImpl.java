package com.digitalweb.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.digitalweb.dao.OrderDao;
import com.digitalweb.model.Order;
import com.digitalweb.model.OrderDetail;
import com.digitalweb.model.Rank;

public class OrderDaoImpl extends SuperOpr implements OrderDao {

	public boolean add(Order o) {
		sql = "insert into order_info(userId,status,ordertime) values(?,?,?)";
		try {
			con.setAutoCommit(false);
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, o.getUserId());
			psmt.setString(2, o.getStatus());
			psmt.setString(3, o.getOrdertime());
			row = psmt.executeUpdate();
			//2.获取订单ID
			if(row>0){
				sql = "select id from order_info order by id desc limit 1";//mysql
//sqlserver		sql = "select top 1 id from order_info order by id desc";
				PreparedStatement psmt1 = con.prepareStatement(sql);
				ResultSet rs = psmt1.executeQuery();
				if(rs.next()){
					o.setId(rs.getInt("id"));
				}
				//3.添加OrderDetail表	
				sql = "insert into order_detail(o_id,p_id,num) values(?,?,?)";
				PreparedStatement psmt2 = con.prepareStatement(sql);
				psmt2.setInt(1, o.getId());
				for(OrderDetail od : o.getDetailList()){
					psmt2.setInt(2, od.getPid());
					psmt2.setInt(3, od.getNum());
					row = psmt2.executeUpdate();
					if(row<=0){//如果失败
						con.rollback();
						break;
					}
				}
			}
			if(row>0)con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return row>0?true:false;
	}

	public ArrayList<Order> getOrderByUser(int uid) {
		ArrayList<Order> orderList = new ArrayList<Order>();
		sql = "select o.id,o.ordertime,o.status,o.userId,u.userName,u.address " +
				"from order_info o,user_Info u " +
				"where o.userId = u.id and o.userId="+uid+" order by o.id desc";
		try {
			psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrdertime(rs.getString("ordertime"));
				order.setUserName(rs.getString("userName"));
				order.setAddress(rs.getString("address"));
				order.setStatus(rs.getString("status"));
				order.setUserId(rs.getInt("userId"));
				sql = "select od.o_id,od.p_id,od.num,p.name as pname,p.price,p.sale,p.pic " +
				  "from order_detail od,product_info p " +
				  "where p.id=od.p_id and o_id="+order.getId();
				PreparedStatement psmt1 = con.prepareStatement(sql);
				ResultSet rs1 = psmt1.executeQuery();
				while(rs1.next()){
					OrderDetail od = new OrderDetail();
					od.setPid(rs1.getInt("p_id"));
					od.setPrice(rs1.getDouble("price"));
					od.setSale(rs1.getDouble("sale"));
					od.setPic(rs1.getString("pic"));
					od.setpName(rs1.getString("pname"));
					od.setNum(rs1.getInt("num"));
					order.getDetailList().add(od);
				}
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public ArrayList<Order> list() {
		ArrayList<Order> orderList = new ArrayList<Order>();
		sql = "select o.id,o.ordertime,o.status,o.userId,u.userName,u.address " +
				"from order_info o,user_Info u " +
				"where o.userId = u.id order by o.id desc";
		try {
			psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrdertime(rs.getString("ordertime"));
				order.setUserName(rs.getString("userName"));
				order.setAddress(rs.getString("address"));
				order.setStatus(rs.getString("status"));
				order.setUserId(rs.getInt("userId"));
				sql = "select od.o_id,od.p_id,od.num,p.name as pname,p.price,p.sale,p.pic " +
				  "from order_detail od,product_info p " +
				  "where o_id="+order.getId();
				PreparedStatement psmt1 = con.prepareStatement(sql);
				ResultSet rs1 = psmt1.executeQuery();
				while(rs1.next()){
					OrderDetail od = new OrderDetail();
					od.setPid(rs1.getInt("p_id"));
					od.setPrice(rs1.getDouble("price"));
					od.setSale(rs1.getDouble("sale"));
					od.setPic(rs1.getString("pic"));
					od.setpName(rs1.getString("pname"));
					od.setNum(rs1.getInt("num"));
					order.getDetailList().add(od);
				}
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public ArrayList<Order> search(String field,String key) {
		ArrayList<Order> orderList = new ArrayList<Order>();
		if(field.equals("name")){
			sql = "select o.*,u.userName,u.address from order_info o,user_info u where o.userId=u.id and u.name='"+key+"'";
		}else if(field.equals("status")){
			sql = "select o.*,u.userName,u.address from order_info o ,user_info u where o.userId=u.id and o.status='"+key+"'";
		}
		try {
			psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrdertime(rs.getString("ordertime"));
				order.setStatus(rs.getString("status"));
				order.setUserId(rs.getInt("userId"));
				order.setAddress(rs.getString("address"));
				order.setUserName(rs.getString("userName"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public Order getOrderById(int id) {
		Order order = new Order();
		ArrayList<Order> orderList = new ArrayList<Order>();
		sql = "select o.id,o.ordertime,o.status,o.userId,u.userName,u.address " +
				"from order_info o,user_Info u " +
				"where o.userId = u.id and o.id="+id;
		try {
			psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				order.setId(rs.getInt("id"));
				order.setOrdertime(rs.getString("ordertime"));
				order.setUserName(rs.getString("userName"));
				order.setAddress(rs.getString("address"));
				order.setStatus(rs.getString("status"));
				order.setUserId(rs.getInt("userId"));
				sql = "select od.o_id,od.p_id,od.num,p.name as pname,p.price,p.sale,p.pic " +
				"from order_detail od,product_info p " +
				"where p.id=od.p_id and o_id="+id;
				
				PreparedStatement psmt1 = con.prepareStatement(sql);
				ResultSet rs1 = psmt1.executeQuery();
				while(rs1.next()){
					OrderDetail od = new OrderDetail();
					od.setPid(rs1.getInt("p_id"));
					od.setPrice(rs1.getDouble("price"));
					od.setSale(rs1.getDouble("sale"));
					od.setPic(rs1.getString("pic"));
					od.setpName(rs1.getString("pname"));
					od.setNum(rs1.getInt("num"));
					order.getDetailList().add(od);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	public boolean send(int id) {
		//1.更新订单表
		sql = "update order_info set status = '已发货' where id ="+id;
		try {
			con.setAutoCommit(false);
			psmt = con.prepareStatement(sql);
			row = psmt.executeUpdate();
			if(row>0){
				//2.更新图书表
				sql = "update product_info set num=(num-?) where id=?";
				PreparedStatement psmt1 = con.prepareStatement(sql);
				Order order = getOrderById(id);
				for(OrderDetail od : order.getDetailList()){
					psmt1.setInt(1, od.getNum());
					psmt1.setInt(2, od.getPid());
					row = psmt1.executeUpdate();
					if(row<=0){
						con.rollback();
						break;
					}
				}
			}
			if(row>0){
				con.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return row>0?true:false;
	}

	public boolean receive(int id) {
		sql = "update order_info set status='交易完成' where id="+id;
		try {
			psmt = con.prepareStatement(sql);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}
	public JFreeChart statOrder(int top,String graphType,String field){
		JFreeChart chart = null;
		String title = "";
		String x = "";
		String y = "";
		if(field.equals("book")){
			//统计销售前N名的图书销售情况
//	sqlserver sql = "select top "+top+" p.name as name,sum(od.num) as num from order_detail od,product_info b where p.id=od.b_id group by p.id,p.name order by num desc";
			sql = "select p.name as name,sum(od.num) as num from order_detail od,product_info b where p.id=od.b_id group by p.id,p.name order by num desc limit "+top;
			title = "图书销售情况";
			x = "图书名称";
		}else if(field.equals("user")){
			//统计购买能力前N名的用户
//sqlserver sql = "select top "+top+" u.name, sum(od.num) as num from order_detail od,order_info o,user_info u where od.o_id=o.id and u.id=o.userId group by userId,u.name order by num desc";
			sql = "select u.userName, sum(od.num) as num from order_detail od,order_info o,user_info u where od.o_id=o.id and u.id=o.userId group by userId,u.userName order by num desc limit "+top;
			title = "用户购买情况";
			x = "用户名称";
		}
		System.out.println(sql);
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(graphType.equals("pie")){
				DefaultPieDataset data = new DefaultPieDataset();
			while(rs.next())
			    data.setValue(rs.getString(1),rs.getInt(2));
				PiePlot3D plot = new PiePlot3D(data);//生成一个3D饼图             
				chart = new JFreeChart("",JFreeChart.DEFAULT_TITLE_FONT, plot, true); 
				chart.setBackgroundPaint(java.awt.Color.lightGray);//可选，设置图片背景色 
				chart.setTitle(title);//可选，设置图片标题 
				plot.setToolTipGenerator(new StandardPieToolTipGenerator()); //MAP中鼠标移上的显示格式
			}else if(graphType.equals("column")){
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				while(rs.next())
				dataset.addValue(rs.getInt(2),title,rs.getString(1));
				chart = ChartFactory.createBarChart3D(title,x,"数量",dataset,PlotOrientation.VERTICAL,false,false,false);
			}
		} catch (SQLException e) {	e.printStackTrace();		}
		return chart;
	}
	public List<Rank> rankcall(int count){
		List<Rank> rankList = new ArrayList<Rank>();
		try {
			CallableStatement csmt = con.prepareCall("{call sp_sale(?)}");
			csmt.setInt(1, count);
			rs = csmt.executeQuery();
			while(rs.next()){
				Rank rank = new Rank();
				rank.setId(rs.getInt("id"));
				rank.setName(rs.getString("name"));
				rank.setMoney(rs.getDouble("money"));				
				rank.setTotal(rs.getInt("total"));
				rankList.add(rank);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rankList;
	}
}
