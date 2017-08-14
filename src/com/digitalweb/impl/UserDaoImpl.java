package com.digitalweb.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.digitalweb.connection.ConnectionSource;
import com.digitalweb.dao.UserDao;
import com.digitalweb.model.User;
import com.digitalweb.util.MyBASE64;
import com.digitalweb.util.MyMD5Util;

public class UserDaoImpl extends SuperOpr implements UserDao {
	public boolean add(User user) {
		sql = "insert into user_info(userName,password,realName,sex,address,question,answer,email,favorate,regDate)" +
				" values(?,?,?,?,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getRealName());
			psmt.setString(4, user.getSex());
			psmt.setString(5, user.getAddress());
			psmt.setString(6, user.getQuestion());
			psmt.setString(7, user.getAnswer());
			psmt.setString(8, user.getEmail());
			psmt.setString(9, user.getFavorate());
			psmt.setString(10, user.getRegDate());
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}
	public int verify(String userName, String password) {
		int flag = -1;
		sql = "select password from user_info where userName=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userName);
			ResultSet rs = psmt.executeQuery();
			String pwdBASE64 = MyBASE64.encryptBASE64(password);
			if(rs.next()){
				//用户名存在
				if(pwdBASE64.equals(rs.getString("password"))){
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
	public User getUserByName(String userName){
		sql = "select * from user_info where userName=?";
		User user = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userName);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(userName);
				user.setPassword(rs.getString("password"));
				user.setRealName(rs.getString("realName"));
				user.setSex(rs.getString("sex"));
				user.setAddress(rs.getString("address"));
				user.setQuestion(rs.getString("question"));
				user.setAnswer(rs.getString("answer"));
				user.setEmail(rs.getString("email"));
//				user.setFavorate(rs.getString("favorate"));
				user.setRegDate(rs.getString("regDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUserById(int id){
		sql = "select * from user_info where id="+id;
		User user = null;
		try {
			psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(id);
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setRealName(rs.getString("realName"));
				user.setSex(rs.getString("sex"));
				user.setAddress(rs.getString("address"));
				user.setQuestion(rs.getString("question"));
				user.setAnswer(rs.getString("answer"));
				user.setEmail(rs.getString("email"));
				user.setQuestion(rs.getString("favorate"));
				user.setRegDate(rs.getString("regDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public boolean updateScore(int id, int score) {
		sql = "update user_info set score = (score+?) where id = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, score);
			psmt.setInt(2, id);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<User> list() {
		sql = "select * from user_info";
		ArrayList<User> userList = new ArrayList<User>();
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setScore(rs.getInt("score"));
				user.setSex(rs.getString("sex"));
				user.setAddress(rs.getString("address"));
				user.setRegDate(rs.getString("regDate"));
				user.setStatus(rs.getInt("status"));
				userList.add(user);
			}
				} catch (SQLException e) {	
					e.printStackTrace();	
				}
		return userList;
	}
	public boolean cancel(int id) {
		sql = "update user_info set status =0 where id="+id;
		try {
			psmt= con.prepareStatement(sql);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}
	public boolean active(int id){
		sql = "update user_info set status =1 where id="+id;
		try {
			psmt= con.prepareStatement(sql);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row>0?true:false;
	}
	public boolean reSetPwd(int id,String pwd) {
		sql = "update user_info set password ='"+pwd+"' where id="+id;
		try {
			psmt= con.prepareStatement(sql);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}
}
