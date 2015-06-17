package com.tao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlConnection implements DataConnection{
	private static DataConnection mysqlConnection = new MysqlConnection();
	private Connection connection;
	private String url;
	private String userName;
	private String password;
	
	public static DataConnection getInstance(){
		return mysqlConnection;
	}
	private MysqlConnection(String url,String userName,String password){
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
	private MysqlConnection(){
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
