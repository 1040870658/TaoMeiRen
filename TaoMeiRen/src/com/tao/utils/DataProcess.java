package com.tao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataProcess {
	protected Connection connection;
	protected DataConnection dataConnection;
	protected PreparedStatement preparedStatement;
	protected ResultSet resultSet;
	
	public DataProcess(DataConnection dataConnection){
		this.dataConnection = dataConnection;
		this.resultSet = null;
		try {
			this.connection = dataConnection.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract boolean execute(String sql);
	public abstract ResultSet executeQuery(String sql, String[] str);
}
