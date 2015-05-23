package com.tao.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.tao.dao.CommodityDao;
import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.model.User;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;

public class CommodityService {
	private CommodityDao commodityDao = new CommodityDao(new MySqlDataProcess(new MysqlConnection()));
	private User user;
	private Order order;
	public Boolean registerCommodity(User user,Commodity commodity){
		commodity.getImageUrl().add(user.getEmail());
		commodity.setMailOfseller(user.getEmail());
		boolean result = commodityDao.add(user,commodity);
		return result;
	}
	public ArrayList<Commodity> queryCommodity(User user){
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		ResultSet resultSet = commodityDao.query(user);
		try {
			while(resultSet.next()){
				Commodity commodity = new Commodity();
				commodity.setId(resultSet.getInt("commodityID"));
				String[] imageUrls = resultSet.getString("imageURL").split(",");
				ArrayList<String> tmp = new ArrayList<String>();
				for(int i = 0;i != imageUrls.length;i ++){
					tmp.add(imageUrls[i]);
				}
				commodity.setImageUrl(tmp);
				commodity.setMailOfseller(resultSet.getString("mailOfseller"));
				commodity.setPrice(resultSet.getDouble("price"));
				commodity.setDescription(resultSet.getString("description"));
				commodity.setName(resultSet.getString("name"));
				commodity.setStock(resultSet.getInt("stock"));
				commodity.setType(resultSet.getInt("dealType"));
				commodity.setCommodityCredit(resultSet.getDouble("commodityCredit"));
				
				commodities.add(commodity);
			}
			return commodities;
		}
		catch (Exception e) {
			return  new ArrayList<Commodity>();
		}
	}
	public void seperateCommodity(ArrayList<Commodity> total,
									ArrayList<Commodity> fix,
									ArrayList<Commodity> collection,
									ArrayList<Commodity> auction){
		for(Commodity element:total){
			if(Commodity.FIX == element.getType()){
				fix.add(element);
			}
			else if(Commodity.COLLECTIVE == element.getType()){
				collection.add(element);
			}
			else if(Commodity.AUCTION == element.getType()){
				auction.add(element);
			}
		}
		
	}
}
