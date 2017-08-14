package com.digitalweb.util;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.digitalweb.connection.ConnectionManager;

public class PMMetaDataTest {

	private static Connection con = null;  
	private static PreparedStatement ps = null;  
	public static void main(String[] args) {  
		try {  
			ConnectionManager cm = new ConnectionManager();
			con = cm.getConnection(); 
			String sql = "select * from user_info where name = ? and address=?";  
			ps = con.prepareStatement(sql);  
			ParameterMetaData pm = ps.getParameterMetaData();  
			System.out.println(pm.getParameterCount());  
		} catch (SQLException e) {  
			e.printStackTrace();  
		}  
   }  
}
