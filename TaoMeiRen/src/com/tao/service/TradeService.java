package com.tao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tao.dao.CommodityDao;
import com.tao.dao.OrderDao;
import com.tao.dao.UserDao;
import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.model.User;
import com.tao.utils.DataProcess;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;

public class TradeService {
	protected DataProcess dataProcess;
	public TradeService() {
		dataProcess = new MySqlDataProcess(MysqlConnection.getInstance());
	}

	public ArrayList<Order> displayOrders(User user, int type) {
		ArrayList<Order> orders = new ArrayList<Order>();
		OrderDao orderDao = new OrderDao(dataProcess, user);
		ResultSet resultSet;
		if (type == Order.BUYER)
			resultSet = orderDao.findBuyerOrder();
		else
			resultSet = orderDao.findSellerOrder();
		try {
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("orderID"));
				order.setCommodityID(resultSet.getInt("commodityID"));
				order.setItemNum(resultSet.getInt("itemNum"));
				order.setMailOfbuyer(resultSet.getString("mailOfbuyer"));
				order.setMailOfseller(resultSet.getString("mailOfseller"));
				order.setDeposit(resultSet.getDouble("deposit"));
				order.setDealType(resultSet.getInt("dealType"));
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return orders;
		}
	}

	public boolean trade(Commodity commodity, int num, User user) {
		UserService userService = new UserService();
		double price = commodity.getPrice() * num;
		if (!userService.cost(user, price) || num == 0) {		
			return false;
		} else {
			user.setAccount(user.getAccount()-price);
			addOrder(commodity, num, user);
			return true;
		}
	}

	public void addOrder(Commodity commodity, int num, User user) {
		OrderDao orderDao = new OrderDao(dataProcess, user);
		orderDao.addOrder(commodity, num,Commodity.FIX);
	}

	public void removeOrder(Order order){
		OrderDao orderDao = new OrderDao(dataProcess);
		orderDao.removeOrder(order);
	}
	public Order queryOrder(int id){
		OrderDao orderDao = new OrderDao(dataProcess);
		ResultSet resultSet = orderDao.queryOrder(id);
		try {
			while(resultSet.next()){
				Order order = new Order();
				order.setCommodityID(resultSet.getInt("commodityID"));
				order.setDealType(resultSet.getInt("dealType"));
				order.setDeposit(resultSet.getDouble("deposit"));
				order.setId(resultSet.getInt("orderID"));
				order.setMailOfbuyer(resultSet.getString("mailOfbuyer"));
				order.setMailOfseller(resultSet.getString("mailOfseller"));
				order.setItemNum(resultSet.getInt("itemNum"));
				return order;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public void seperateOrder(ArrayList<Order> total,
			ArrayList<Order> fix, ArrayList<Order> collection,
			ArrayList<Order> auction) {
		for (Order element : total) {
			if (Commodity.FIX == element.getDealType()) {
				fix.add(element);
			} else if (Commodity.COLLECTIVE == element.getDealType()) {
				collection.add(element);
			} else if (Commodity.AUCTION == element.getDealType()) {
				auction.add(element);
			}
		}

	}
	public void sendedCommodity(Order order){
		OrderDao orderDao = new OrderDao(dataProcess);
		orderDao.updateType(order.getId(), Commodity.SENDED);
	}
	public ArrayList<Order> queryAllOrders(){
		ArrayList<Order>orders = new ArrayList<Order>();
		OrderDao orderDao = new OrderDao(dataProcess);
		ResultSet resultSet = orderDao.queryAllOrders();
		try {
			while(resultSet.next()){
				Order order = new Order();
				order.setCommodityID(resultSet.getInt("commodityID"));
				order.setDealType(resultSet.getInt("dealType"));
				order.setDeposit(resultSet.getDouble("deposit"));
				order.setId(resultSet.getInt("orderID"));
				order.setMailOfbuyer(resultSet.getString("mailOfbuyer"));
				order.setMailOfseller(resultSet.getString("mailOfseller"));
				order.setItemNum(resultSet.getInt("itemNum"));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
}
