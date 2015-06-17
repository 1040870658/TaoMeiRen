package com.tao.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tao.dao.UserDao;
import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.model.User;
import com.tao.utils.*;


public class UserService {
	private UserDao userDao = new UserDao(new MySqlDataProcess(MysqlConnection.getInstance()));
	
	
	public void regist(User user) throws Exception {
		
		User _user = userDao.findByUsername(user.getEmail());
		if(_user != null) throw new Exception("用户名" + user.getEmail() + ", 已被注册过了！");
		userDao.add(user);
	}

	public User login(User form) throws Exception {
		
		User user = userDao.findByUsername(form.getEmail());
		
		if(user == null) throw new Exception("用户名不存在！");
		
		if(!form.getPassword().equals(user.getPassword())) {
			throw new Exception("密码错误！");
		}
		return user;
	}
	
	public void sell(Order order,User user){
		user.setAccount(user.getAccount()+order.getDeposit());
		userDao.earn(user);
	}
	
	public boolean cost(User user,double price){
		if (user.getAccount() < price) {
			return false;
		} else {
			userDao.cost(user, price);
			return true;
		}
	}
	public void returnDeposit(double deposit,User user){
		user.setAccount(user.getAccount()+deposit);
		userDao.earn(user);
	}
	public User queryUser(String email){
		ResultSet resultSet = userDao.queryUser(email);
		try {
			while(resultSet.next()){
				User user = new User();
				user.setEmail(email);
				user.setAccount(resultSet.getDouble("account"));
				user.setBuyerCredit(resultSet.getDouble("buyerCredit"));
				user.setSellercredit(resultSet.getDouble("sellerCredit"));
				user.setPassword(resultSet.getString("passwd"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
