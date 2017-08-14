package com.digitalweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.digitalweb.connection.ConnectionManager;
import com.digitalweb.connection.ConnectionSource;

public class SuperOpr {
	protected Connection con;
	protected PreparedStatement psmt;
	protected String sql;
	protected int row;
	protected ResultSet rs;
	public SuperOpr() {
		ConnectionManager cm = new ConnectionManager();
		con = cm.getConnection();
//		try {
//			con = ConnectionSource.getConnection();
//		} catch (SQLException e) {
//			System.out.println("从连接池中获取链接异常！");
//			e.printStackTrace();
//		}
	}
}
