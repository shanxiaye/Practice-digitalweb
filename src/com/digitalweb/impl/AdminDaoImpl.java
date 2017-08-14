package com.digitalweb.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.digitalweb.dao.AdminDao;
import com.digitalweb.util.MyBASE64;

public class AdminDaoImpl extends SuperOpr implements AdminDao {

	public int verify(String userName, String password) {
		int flag = -1;
		sql = "select pwd from admin_info where name=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userName);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				//用户名存在
				if(password.equals(rs.getString("pwd"))){
//				String pwdInDb = MyBASE64.decryptBASE64(rs.getString("password"));
//				if(password.equals(pwdInDb)){
					//密码匹配
					flag = 3;
				}else{
					//密码错误
					flag = 2;
				}
			}else{
				//用户名不存在
				flag = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return flag;
	}

}
