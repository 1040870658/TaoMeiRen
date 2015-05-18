package com.tao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ye
 *
 */
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
	
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public PreparedStatement getPreparedStatement(String sql) throws SQLException{
		preparedStatement = connection.prepareStatement(sql);
		return preparedStatement;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public abstract boolean execute(String sql,Object...str);
	public abstract boolean execute(String sql);
	public abstract ResultSet executeQuery(String sql, Object...str);
}
