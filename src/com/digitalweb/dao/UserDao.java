package com.digitalweb.dao;

import com.digitalweb.model.User;

public interface UserDao {
	public boolean add(User user);
	public int verify(String userName,String password);
	public boolean updateScore(int id,int score);
	
}
