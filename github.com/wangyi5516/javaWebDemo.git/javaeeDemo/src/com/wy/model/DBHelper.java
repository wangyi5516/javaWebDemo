package com.wy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wy.entity.UserEntity;

public class DBHelper {
	
	private Connection conn;
	private Statement stmt;
	
	private static final String drivername = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306";
	public DBHelper() {
		try {
			Class.forName(drivername);
			conn = DriverManager.getConnection(url,"root","12345678");
			stmt = conn.createStatement();
			stmt.execute("use usrdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet Query(String cmd) throws SQLException {
		ResultSet rs = null;		
		rs = stmt.executeQuery(cmd);		
		return rs;
	}

	public Boolean Query(User user) throws SQLException {
		ResultSet rs = null;
		String cmd = "select * from userInfo where userId='"+user.getUserId()+"' and password='"+user.getPassword()+"'";
		rs = stmt.executeQuery(cmd);
		
		
		ResultSetMapper<UserEntity> rsm = new ResultSetMapper<UserEntity>();
		UserEntity entity = rsm.mapRersultSetToObject(rs, UserEntity.class).get(0);
		
		System.out.println("entity:"+entity.toString());
		
		boolean ret = rs.next();
		String phone = rs.getString("phone");
		System.out.println("phone123:"+phone);
		
		return ret;
	}

}
