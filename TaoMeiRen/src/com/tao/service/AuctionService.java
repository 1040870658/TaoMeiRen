package com.tao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tao.dao.AuctionDao;
import com.tao.dao.AuctionDao;
import com.tao.model.Auction;
import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;
import com.tao.utils.StaticUtil;

public class AuctionService extends CommodityService{
	private AuctionDao auctionDao = new AuctionDao(new MySqlDataProcess(MysqlConnection.getInstance()));
	public Boolean registerCommodity(User user, Object... objects) {
		// TODO Auto-generated method stub
		Commodity commodity = (Commodity) objects[0];
		Auction auction = (Auction)objects[2];
		boolean success = super.registerCommodity(user,commodity);
		if(!success)
			return success;
		
		ResultSet resultSet = commodityDao.quertyCommodity();
		
		try {
			while(resultSet.next()){
				commodity.setId(resultSet.getInt(1));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		success = auctionDao.add(auction);
		return success;
	}
	public ArrayList<Commodity>seperateConceret(int start,int length,ArrayList<Commodity> commodityList){
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		int num = 0;
		for(int i = start;num < length && i < commodityList.size();i ++){
			if(Commodity.AUCTION == commodityList.get(i).getType()){
				commodities.add( commodityList.get(i));
				num++;
			}
		}
		return commodities;
	}
	public ArrayList<Commodity> queryAllCommodity(){
		ArrayList<Commodity>total = super.queryAllCommodity();
		ArrayList<Commodity>auction = new ArrayList<Commodity>();
		for(Commodity element:total){
			if(Commodity.AUCTION == element.getType()){
				auction.add(element);
			}
		}
		return auction;
	}
	
	public Auction queryAuction(Commodity commodity){
		AuctionDao  auctionDao = new AuctionDao(new MySqlDataProcess(MysqlConnection.getInstance()));
		Auction auction = new Auction();
		ResultSet resultSet = auctionDao.queryAuction(commodity.getId());
		String currentParticipants;
		try {
			while(resultSet.next()){
				
				java.sql.Timestamp deadline = new java.sql.Timestamp(resultSet.getDate("deadLine").getTime());
				currentParticipants = resultSet.getString("mailOfparticipants");
				auction.setAuctionID(resultSet.getInt("ID"));
				auction.setCommodity(commodity);		
				auction.setDeadline(deadline);
				auction.setActivePrice(resultSet.getDouble("activePrice"));
				auction.setCurrentPrice(resultSet.getDouble("currentPrice"));
				auction.setCurrentMail(resultSet.getString("currentMail"));
				auction.setMailOfparticipants(StaticUtil.seperateText(currentParticipants));
				return auction;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public ArrayList<String> queryParticipants(Commodity commodity){
		ResultSet resultSet = auctionDao.queryParticipants(commodity.getId());
		ArrayList<String>mailOfparticipantList = new ArrayList<String>();
		try {
			while(resultSet.next()){
				mailOfparticipantList.add(resultSet.getString("mailOfbuyer"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mailOfparticipantList;
	}
	public void updateAuction(Auction auction){
		auctionDao.updateAuction(auction);
	}
//	@Override
//	public void deleteCommodity(Commodity commodity) {
//		Auction auction = queryAuction(commodity);
//		auctionDao.deleteAuction(commodity.getId());
//		super.deleteCommodity(commodity);
//	}
	@Override
	public ArrayList<Commodity> queryCommodity(String commodityName) {
		// TODO Auto-generated method stub
		ArrayList<Commodity>total = super.queryCommodity(commodityName);
		ArrayList<Commodity>auction = new ArrayList<Commodity>();
		for(Commodity element:total){
			if(Commodity.AUCTION == element.getType()){
				auction.add(element);
			}
		}
		return auction;
	}
	
}
