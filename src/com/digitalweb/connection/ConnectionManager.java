package com.digitalweb.connection;

import java.sql.*;

public class ConnectionManager {
	private String driver ;
	private String url;
	private String userName ;
	private String pwd ;
	private Connection con;
	//初始化私有属性，加载驱动
	public ConnectionManager() {
//		driver = "net.sourceforge.jtds.jdbc.Driver";
//		url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=digital";
//		userName = "sa";
//		pwd = "Abc123@";
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://127.0.0.1:3306/digital?useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull&amp;transformedBitIsBoolean=true";
		userName = "root";
		pwd = "mysql";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		try {
			con = DriverManager.getConnection(url,userName,pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;		
	}
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ConnectionManager cm = new ConnectionManager();
		System.out.println(cm.getConnection());
		//此处编写代码
		
		
	}
}
