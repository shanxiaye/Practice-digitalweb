package com.digitalweb.util;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.digitalweb.connection.ConnectionManager;
import java.sql.Connection;

public class DBMetaDataTest {
	private static Connection con = null;  
	 public static void main(String[] args) {
		 ConnectionManager cm = new ConnectionManager();
		 con = cm.getConnection();
		 try{
		 DatabaseMetaData dbmd = con.getMetaData();  
		 System.out.println(dbmd.getURL());  
		 System.out.println(dbmd.getDriverName());  
		 System.out.println(dbmd.getDriverVersion());  
		 System.out.println(dbmd.getDatabaseProductName());  
		 System.out.println(dbmd.getDatabaseProductVersion());  
		 }catch(SQLException e) {  
			 e.printStackTrace();  
		 }  
	 }
}
