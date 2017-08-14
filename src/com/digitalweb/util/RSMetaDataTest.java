package com.digitalweb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.digitalweb.connection.ConnectionManager;

public class RSMetaDataTest {
	private static Connection con = null;  
	private static PreparedStatement ps = null;  
	
	public static void main(String[] args) {
		try {  
				ConnectionManager cm = new ConnectionManager();
				con = cm.getConnection();
				String sql = "select * from user_info where id = ?";  
				ps = con.prepareStatement(sql);  
				ResultSetMetaData rs = ps.getMetaData();  
				int count = rs.getColumnCount();  
				for(int i=1;i<count;i++){  
					System.out.println(rs.getColumnClassName(i));
					System.out.println(rs.getColumnName(i));  
					System.out.println(rs.getColumnType(i));  
					System.out.println(rs.getColumnTypeName(i)); 
					System.out.println("----------------------");
				}  
			} catch (SQLException e) {  
			e.printStackTrace();  
			}  
	}
}
