package com.digitalweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;

import com.digitalweb.model.Order;
import com.digitalweb.model.Rank;

public interface OrderDao {
	//添加订单
	public boolean add(Order o);
	//根据用户查看订单
	public ArrayList<Order> getOrderByUser(int uid);
	//订单全部列表
	public ArrayList<Order> list();
	//订单查询
	public ArrayList<Order> search(String field,String key);
	//订单明细
	public Order getOrderById(int id);
	//发货
	public boolean send(int id);
	//收货
	public  boolean receive(int id);
	//统计
	public JFreeChart statOrder(int top,String graphType,String field);
	//排行
	public List<Rank> rankcall(int count);
}
