package com.tao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlDataProcess extends DataProcess{

	public MySqlDataProcess(DataConnection dataConnection) {
		super(dataConnection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(String sql) {
		// TODO Auto-generated method stub
		try {
			preparedStatement = connection.prepareStatement(sql);
			return preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public ResultSet executeQuery(String sql, Object... str) {
		// TODO Auto-generated method stub
		try {
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0;i != str.length;i ++){
				preparedStatement.setObject(i+1, str[i]);  
			}
			ResultSet resultSet =  preparedStatement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean execute(String sql, Object...str ) {
		// TODO Auto-generated method stub
		try {
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0;i != str.length;i ++){
				preparedStatement.setObject(i+1, str[i]);  
			}
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("false");
			return false;
		}
	}



	

}
