package com.tao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection implements DataConnection{
	private Connection connection;
	private String url;
	private String userName;
	private String password;
	
	public MysqlConnection(String url,String userName,String password){
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
	public MysqlConnection(){
		this.url = "jdbc:mysql://localhost:3306/TaoMeiRen";
		this.userName = "root";
		this.password = "root";
	}
	public Connection connect() throws SQLException {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}
		try{
			connection = DriverManager.getConnection(url, userName, password);
		}catch(SQLException e){
			System.out.println("connect failed");
			return null;
		}
		return connection;
	}

}
