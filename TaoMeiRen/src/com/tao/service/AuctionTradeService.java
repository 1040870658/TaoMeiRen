package com.tao.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.tao.dao.AuctionDao;
import com.tao.dao.OrderDao;
import com.tao.model.Auction;
import com.tao.model.Commodity;
import com.tao.model.User;

public class AuctionTradeService extends TradeService{
	
	private AuctionDao auctionDao;
	
	public AuctionTradeService(){
		super();
		auctionDao = new AuctionDao(dataProcess);
	}
	public boolean trade(Commodity commodity,Auction auction,int num, User user) {
		int id = commodity.getId();
		boolean success = super.trade(commodity, num, user);
		returnOrder(id,commodity.getPrice(),auction);
		auctionDao.removeAuction(id);
		return success;
	}
	
	public boolean askPrice(Commodity commodity,Auction auction,User user,double price){
		UserService userService = new UserService();
		if(price <= auction.getCurrentPrice())
			return false;
		if(!userService.cost(user, price))
			return false;
		if(price >= commodity.getPrice())
			price = commodity.getPrice();
		
		AuctionService auctionService = new AuctionService();
		String userEmail = user.getEmail();
		ArrayList<String> mailOfparticipantList = auctionService.queryParticipants(commodity);
		if(mailOfparticipantList == null){
			mailOfparticipantList = new ArrayList<String>();
		}
		
		if(!mailOfparticipantList.contains(user.getEmail())){
			mailOfparticipantList.add(userEmail);
			addAskOrder(commodity, price, user);
		}
		else{
			updateAskOrder(commodity,price,user.getEmail());
		}
		auction.setCurrentMail(userEmail);
		auction.setCurrentPrice(price);
		auction.setMailOfparticipants(mailOfparticipantList);
		auctionService.updateAuction(auction);
		if(price >= commodity.getPrice())
			deleteAskOrder(commodity.getId());
		return true;
	}
	public void addAskOrder(Commodity commodity,double price, User user){
		AuctionDao auctionDao = new AuctionDao(dataProcess);
		auctionDao.addOrder(commodity,price,user.getEmail());
	}
	public void updateAskOrder(Commodity commodity,double price,String email){
		auctionDao.updateAskOrder(commodity,price,email);
	}
	public void deleteAskOrder(int commodityID){
		auctionDao.deleteAskOrder(commodityID);
	}
	private void returnOrder(int id,double price,Auction auction){
		ArrayList<String>mailOfparticipants = auction.getMailOfparticipants();
		if(mailOfparticipants == null || mailOfparticipants.size() == 0)
			return;
		String winner = auction.getCurrentMail();
		UserService userService = new UserService();
		for(String element:mailOfparticipants){
			if(!element.equals(winner)){
				userService.returnDeposit(price, userService.queryUser(element));
			}
		}
	}
}
