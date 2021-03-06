package com.tao.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.utils.*;


public class UserDao extends Dao{

	public UserDao(DataProcess dataProcess) {
		super(dataProcess);
	}

	public User findByUsername(String username) {
		if (username == null) {
			return null;
		}
		String sql = "select * from user where email = ?";
		// String sql = "select * from user";
		String[] userInfo = { username };
		ResultSet resultSet = dataProcess.executeQuery(sql, userInfo);
		try {
			while (resultSet.next()) {
				User user = new User(resultSet.getString("email"),
				resultSet.getString("passwd"),
				resultSet.getDouble("account"),
				resultSet.getDouble("buyerCredit"),
				resultSet.getDouble("sellerCredit"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void add(User user) throws SQLException {
		String sql = "insert into user(email,passwd) values(?,?)";
		dataProcess.execute(sql,user.getEmail(),user.getPassword());
	}
	public void cost(User user,double cost){
		String sql = "update user set account = ? where email = ?";
		double price = user.getAccount()-cost;
		dataProcess.execute(sql, price,user.getEmail());
	}
	public void earn(User user){
		System.out.println("earn:"+user.getAccount());
		String sql = "update user set account = ? where email = ?";
		double price = user.getAccount();
		dataProcess.execute(sql, price,user.getEmail());
 	}
	public ResultSet queryUser(String email){
		String sql = "select * from user where email = ?";
		return dataProcess.executeQuery(sql, email);
	}
}
