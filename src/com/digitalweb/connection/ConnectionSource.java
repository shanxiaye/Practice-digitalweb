package com.digitalweb.connection;

import org.apache.commons.dbcp.BasicDataSource;  
import org.apache.commons.dbcp.BasicDataSourceFactory;  
  
import java.sql.SQLException;  
import java.sql.Connection;  
import java.util.Properties;  
  
public class ConnectionSource {  
    private static BasicDataSource dataSource = null;  
  
    public ConnectionSource() {  
    }  
  
    public static void init() {  
  
        if (dataSource != null) {  
            try {  
                dataSource.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            dataSource = null;  
        }  
  
        try {  
            Properties p = new Properties();  
            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");  
            p.setProperty("url", "jdbc:mysql://127.0.0.1:3306/digital?useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull&amp;transformedBitIsBoolean=true");  
            p.setProperty("password", "888888");  
            p.setProperty("username", "root"); 
//            p.setProperty("driverClassName", "net.sourceforge.jtds.jdbc.Driver");  
//            p.setProperty("url", "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=digital");  
//            p.setProperty("password", "Abc123@");  
//            p.setProperty("username", "sa"); 
            p.setProperty("maxActive", "30");  
            p.setProperty("maxIdle", "10");  
            p.setProperty("maxWait", "1000");  
            p.setProperty("removeAbandoned", "false");  
            p.setProperty("removeAbandonedTimeout", "120");  
            p.setProperty("testOnBorrow", "true");  
            p.setProperty("logAbandoned", "true");  
  
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);  
  
        } catch (Exception e) {  
            //  
        }  
    }  
  
  
    public static synchronized Connection getConnection() throws  SQLException {  
        if (dataSource == null) {  
            init();  
        }  
        Connection conn = null;  
        if (dataSource != null) {  
            conn = dataSource.getConnection();  
        }  
        return conn;  
    }  
		
}  