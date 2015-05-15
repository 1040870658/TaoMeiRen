package com.tao.utils;

import java.sql.PreparedStatement;
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
	public ResultSet executeQuery(String sql, String str) {
		// TODO Auto-generated method stub
		try {
			preparedStatement = connection.prepareStatement(sql);
			
            preparedStatement.setString(1, str);  
  
			ResultSet resultSet =  preparedStatement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}



	

}
