package com.tao.service;

import com.tao.dao.CollectionDao;
import com.tao.dao.OrderDao;
import com.tao.dao.UserDao;
import com.tao.model.Commodity;
import com.tao.model.User;

public class CollectionTradeService extends TradeService{
	public CollectionTradeService(){
		super();
	}
	public boolean book(Commodity commodity,int num,User user){
		UserDao userDao = new UserDao(dataProcess);
		double price = commodity.getPrice() * num;
		if (user.getAccount() < price || 0 == price) {
			return false;
		} else {
			addBookOrder(commodity, num, user);
			userDao.cost(user, price);
			return true;
		}
	}
	public void addBookOrder(Commodity commodity, int num, User user) {
		OrderDao orderDao = new OrderDao(dataProcess, user);
		orderDao.addOrder(commodity, num,Commodity.COLLECTIVE);
	}
}
