package com.tao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tao.model.*;
import com.tao.dao.CollectionDao;
import com.tao.dao.CommodityDao;
import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;
import com.tao.utils.StaticUtil;

public class CollectionService extends CommodityService{
	private CollectionDao collectionDao = new CollectionDao(new MySqlDataProcess(MysqlConnection.getInstance()));

	@Override
	public Boolean registerCommodity(User user, Object... objects) {
		// TODO Auto-generated method stub
		Commodity commodity = (Commodity) objects[0];
		Collection collection = (Collection)objects[1];
		boolean success = super.registerCommodity(user,commodity);
		if(!success)
			return success;
		
		ResultSet resultSet = commodityDao.quertyCommodity();
		
		try {
			while(resultSet.next()){
				commodity.setId(resultSet.getInt(1));
				System.out.println("CommodityID:"+commodity.getId());
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		success = collectionDao.add(collection);
		return success;
	}
	public ArrayList<Commodity>seperateConceret(int start,int length,ArrayList<Commodity> commodityList){
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		int num = 0;
		for(int i = start;num < length && i < commodityList.size();i ++){
			if(Commodity.COLLECTIVE == commodityList.get(i).getType()){
				commodities.add( commodityList.get(i));
				num++;
			}
		}
		return commodities;
	}
	public ArrayList<Commodity> queryAllCommodity(){
		ArrayList<Commodity>total = super.queryAllCommodity();
		ArrayList<Commodity>collection = new ArrayList<Commodity>();
		for(Commodity element:total){
			if(Commodity.COLLECTIVE == element.getType()){
				collection.add(element);
			}
		}
		return collection;
	}
	
	public Collection queryCollection(Commodity commodity){
		CollectionDao  collectionDao = new CollectionDao(new MySqlDataProcess(MysqlConnection.getInstance()));
		Collection collection = new Collection();
		ResultSet resultSet = collectionDao.queryCollection(commodity.getId());
		String currentParticipants;
		try {
			while(resultSet.next()){
				
				java.sql.Timestamp deadline = new java.sql.Timestamp(resultSet.getDate("deadLine").getTime());
				currentParticipants = resultSet.getString("mailOfparticipants");
				collection.setCollectionID(resultSet.getInt("ID"));
				collection.setCommodity(commodity);		
				collection.setDeadline(deadline);
				collection.setActiveNum(resultSet.getInt("activeNum"));
				collection.setCurrentNum(resultSet.getInt("currentNum"));
				collection.setCollectivePrice(resultSet.getDouble("collectivePrice"));
				collection.setMailOfparticipants(StaticUtil.seperateText(currentParticipants));
				return collection;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public void updateOrderNum(Collection collection,int num){
		
		int currentNum = collection.getCurrentNum() + num;
		if(currentNum >= collection.getActiveNum())
			currentNum = collection.getActiveNum();
		collection.setCurrentNum(currentNum);
		collectionDao.updateOrderNum(collection);
		if(currentNum >= collection.getActiveNum())
			successBook(collection.getCommodity().getId());
	}
	private void successBook(int commodityID){
		collectionDao.copyToorder(commodityID);
		collectionDao.removeCollection(commodityID);
		
	}
//	@Override
//	public void deleteCommodity(Commodity commodity) {
//		Collection collection = queryCollection(commodity);
//		collectionDao.deleteCollection(commodity.getId());
//		super.deleteCommodity(commodity);
//	}
	@Override
	public ArrayList<Commodity> queryCommodity(String commodityName) {
		// TODO Auto-generated method stub
		ArrayList<Commodity>total = super.queryCommodity(commodityName);
		ArrayList<Commodity>collection = new ArrayList<Commodity>();
		for(Commodity element:total){
			if(Commodity.COLLECTIVE == element.getType()){
				collection.add(element);
			}
		}
		return collection;
	}
	
}
