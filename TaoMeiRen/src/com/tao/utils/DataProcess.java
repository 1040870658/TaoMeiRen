package com.tao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataProcess {
	private Connection connection;
	private DataConnection dataConnection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public DataProcess(DataConnection dataConnection){
		this.dataConnection = dataConnection;
		this.resultSet = null;
		try {
			connection = dataConnection.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract boolean execute(String sql);
	
}
