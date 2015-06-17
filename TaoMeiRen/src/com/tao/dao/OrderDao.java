package com.tao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.model.User;
import com.tao.utils.DataProcess;

public class OrderDao extends Dao{
	private User user;
	public OrderDao(DataProcess dataProcess,User user){
		super(dataProcess);
		this.user = user;
	}
	public OrderDao(DataProcess dataProcess){
		super(dataProcess);
	}
	public ResultSet findSellerOrder(){
		String mailOfbuyer = user.getEmail();
		String sql = "select * from order_table where mailOfseller = ?";
		ResultSet resultSet = dataProcess.executeQuery(sql, mailOfbuyer);
		return resultSet;
	}
	public ResultSet findBuyerOrder(){
		String mailOfbuyer = user.getEmail();
		String sql = "select * from order_table where mailOfbuyer = ?";
		ResultSet resultSet = dataProcess.executeQuery(sql, mailOfbuyer);
		return resultSet;
	}
	public boolean addOrder(Commodity commodity,int num,int type){
		int oSize = 6;
		Order order = new Order(
				0,
				commodity.getId(),
				num,
				user.getEmail(),
				commodity.getMailOfseller(),
				commodity.getPrice()*num,
				commodity.getType());
		String sql = "insert into order_table(commodityID,itemNum,mailOfbuyer,mailOfseller,deposit,dealType) values(?";
		switch(type){
		case Commodity.FIX:
			sql = "insert into order_table(" +
				"commodityID,itemNum,mailOfbuyer,mailOfseller,deposit,dealType) values(?";break;
		case Commodity.COLLECTIVE:
			sql = "insert into collection_table(" +
				"commodityID,itemNum,mailOfbuyer,mailOfseller,deposit,dealType) values(?";break;
		case Commodity.AUCTION:
			sql = "insert into auction_table(" +
			"commodityID,itemNum,mailOfbuyer,mailOfseller,deposit,dealType) values(?";break;
		}
		
		for (int i = 1; i != oSize; i++) {
			sql += ",?";
		}
		sql += ")";
		return dataProcess.execute(
				sql,
				order.getCommodityID(),
				order.getItemNum(),
				order.getMailOfbuyer(),
				order.getMailOfseller(),
				order.getDeposit(),
				order.getDealType()
				);
	}
	public void removeOrder(Order order){
		String sql = "delete from order_table where orderID = ?";
		dataProcess.execute(sql, order.getId());
	}
	public ResultSet queryOrder(int id){
		String sql = "select * from order_table where orderID = ?";
		return dataProcess.executeQuery(sql, id);
	}
	public void updateType(int id,int type){
		String sql = "update order_table set dealType = ? where orderID = ?";
		dataProcess.execute(sql,type,id);
	}
	public ResultSet queryAllOrders(){
		String sql = "select * from order_table";
		return dataProcess.executeQuery(sql);
	}
}
