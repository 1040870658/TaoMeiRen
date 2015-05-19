package com.tao.service;

import com.tao.dao.UserDao;
import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.utils.*;


public class UserService {
	private UserDao userDao = new UserDao(new MySqlDataProcess(new MysqlConnection()));
	
	
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
	
	public void sell(Commodity commodity){
		
	}
}
