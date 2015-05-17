package com.tao.dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tao.model.User;
import com.tao.utils.*;

/**
 * 数据类
 *
 */
public class UserDao {
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 */
	private DataProcess dataProcess;
	public UserDao(DataProcess dataProcess){
		this.dataProcess = dataProcess;
	}
	public User findByUsername(String username) {
		if(username == null) {
			return null;
		}
		String sql = "select * from user where email = ?";
//		String sql = "select * from user";
		String[] userInfo ={username};
		ResultSet resultSet = dataProcess.executeQuery(sql,userInfo);
		if(resultSet != null){
			try {
				resultSet.next();
				User user = new User(
						resultSet.getString("email"),
						resultSet.getString("passwd"), 
						resultSet.getDouble("account"),
						resultSet.getDouble("buyerCredit"),
						resultSet.getDouble("sellerCredit"));
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user) {
	}
}
