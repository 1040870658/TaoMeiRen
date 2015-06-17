package com.tao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tao.dao.CollectionDao;
import com.tao.dao.CommodityDao;
import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.model.Order;
import com.tao.model.User;
import com.tao.utils.DataProcess;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;
import com.tao.utils.StaticUtil;

public class CommodityService {
	protected CommodityDao commodityDao = new CommodityDao(new MySqlDataProcess(MysqlConnection.getInstance()));
	
	public Boolean registerCommodity(User user,Object... objects){
		Commodity commodity = (Commodity) objects[0];
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
	
	public ArrayList<Commodity> queryCommodity(String commodityName){
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		ResultSet resultSet = commodityDao.quertyCommodity(commodityName);
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
	
	public ArrayList<Commodity> queryAllCommodity(){
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		ResultSet resultSet = commodityDao.queryAll();
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
	
	public void removeCommodity(Commodity commodity,int num){
		commodity.setStock(commodity.getStock() - num);
		commodityDao.remove(commodity);
	}
	public void deleteCommodity(Commodity commodity){
		commodityDao.drop(commodity);
	}
	public void sendedCommodity(Commodity commodity){
		commodityDao.updateType(commodity.getId(), Commodity.SENDED);
	}
	public Commodity queryCommodity(int id){
		ResultSet resultSet = commodityDao.quertyCommodity(id);
		Commodity commodity = new Commodity();
		try {
			while(resultSet.next()){
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
				return commodity;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public ArrayList<Commodity> seperateConceret(int start,int length,ArrayList<Commodity> commodityList){
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		commodities.addAll(commodityList);

		return commodities;
	}
}
