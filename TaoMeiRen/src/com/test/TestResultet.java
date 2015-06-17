package com.test;

import org.junit.Test;

import com.tao.dao.UserDao;
import com.tao.model.User;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;


public class TestResultet {

	@Test
	public void testSet() {
		UserDao userDao = new UserDao(new MySqlDataProcess(MysqlConnection.getInstance()));
		User user = userDao.findByUsername("1040870658@qq.com");
//		System.out.println(user == null );
		System.out.println(user.getEmail());
	
	}
}
