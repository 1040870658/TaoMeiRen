package com.tao.dao;

import java.sql.ResultSet;

import com.tao.model.Auction;
import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.utils.DataProcess;

public class AuctionDao extends Dao {
	public AuctionDao(DataProcess dataProcess){
		super(dataProcess);
	}
	public boolean add(Auction auction) {
		final int cSize = 6;
		String sql = "insert into auction"
				+ "(commodityID,deadLine,activePrice,currentPrice,currentMail,mailOfparticipants)"
				+ " values(?";
		for (int i = 1; i != cSize; i++) {
			sql += ",?";
		}
		sql += ")";

		return dataProcess.execute(sql, 
				auction.getCommodity().getId(),
				auction.getDeadline(), 
				auction.getActivePrice(),
				auction.getCurrentPrice(),
				auction.getCurrentMail(),
				""
				);
	}
	public ResultSet queryAuction(int commodityID){
		String sql = "select * from auction where commodityID = ?";
		return dataProcess.executeQuery(sql, commodityID);
	}
	public void removeAuction(int id){
		String sql = "delete from auction_table where commodityID = ?";
		dataProcess.execute(sql,id);
	}
	public ResultSet queryParticipants(int commodityID){
		String sql = "select mailOfbuyer from auction_table where commodityID = ?";
		return dataProcess.executeQuery(sql, commodityID);
	}
	public void updateAuction(Auction auction){
		String sql = "update auction set currentPrice = ?,currentMail = ? where ID = ?";
		dataProcess.execute(sql,auction.getCurrentPrice(),auction.getCurrentMail(),auction.getAuctionID());
	}
	public void updateAskOrder(Commodity commodity,double price,String email){
		String sql = "update auction_table set deposit = ? where commodityID = ? AND mailOfbuyer = ?";
		dataProcess.execute(sql, price,commodity.getId(),email);
	}
	public void deleteAskOrder(int commodityID){
		String sql = "delete from auction_table where commodityID = ?";
		dataProcess.execute(sql, commodityID);
	}
	public void deleteAuction(int commodityID){
		String sql = "delete from auction where commodityID = ?";
		dataProcess.execute(sql,commodityID);
	}
	public void addOrder(Commodity commodity,double price,String email){
		String sql = "insert into auction_table(" +
		"commodityID,itemNum,mailOfbuyer,mailOfseller,deposit,dealType) values(?";
		int oSize = 6;
		Order order = new Order(
				0,
				commodity.getId(),
				1,
				email,
				commodity.getMailOfseller(),
				commodity.getPrice(),
				commodity.getType());
		
		for (int i = 1; i != oSize; i++) {
			sql += ",?";
		}
		sql += ")";
		System.out.println(price);
	    dataProcess.execute(
				sql,
				order.getCommodityID(),
				order.getItemNum(),
				order.getMailOfbuyer(),
				order.getMailOfseller(),
				price,
				order.getDealType()
				);
	}
}