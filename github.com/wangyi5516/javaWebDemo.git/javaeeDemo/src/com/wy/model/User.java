package com.wy.model;

import java.sql.SQLException;

public class User {
	private String userId;
	private String password;
	
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	
	public Boolean isExist() throws SQLException{
		//User user = new User(userId, password);
		DBHelper db = new DBHelper();
		return db.Query(this);		
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
