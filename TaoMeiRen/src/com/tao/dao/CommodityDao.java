package com.tao.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.utils.DataProcess;

public class CommodityDao extends Dao {
	public CommodityDao(DataProcess dataProcess) {
		super(dataProcess);
	}

	public boolean add(User user, Commodity commodity) {
		final int cSize = 9;
		String sql = "insert into commodity values(?";
		for (int i = 1; i != cSize; i++) {
			sql += ",?";
		}
		sql += ")";
		String ImageUrl = "";
		for (String element : commodity.getImageUrl()) {
			ImageUrl += element;
			ImageUrl += ",";
		}
		return dataProcess.execute(sql,
				commodity.getId(),
				ImageUrl,
				commodity.getMailOfseller(),
				commodity.getPrice(),
				commodity.getDescription(),
				commodity.getName(),
				commodity.getStock(), 
				commodity.getType(),
				commodity.getCommodityCredit());
	}
	public ResultSet query(User user){
		String sql = "select * from commodity where mailOfseller = ?";
		String selection = user.getEmail();
		ResultSet resultSet = dataProcess.executeQuery(sql, selection);
		return resultSet;
	}
	public ResultSet queryAll(){
		String sql = "select * from commodity";
		ResultSet resultSet = dataProcess.executeQuery(sql);
		return resultSet;
	}
	public void remove(Commodity commodity){
		String sql = "update commodity set stock = ? where commodityID = ?";
		dataProcess.execute(sql,commodity.getStock(),commodity.getId());
	
	}
	public void delete(Commodity commodity){
		if(commodity.getStock() <= 0){
			String sql = "delete from collection where commodityID = ?";
			dataProcess.execute(sql, commodity.getId());
			sql = "delete from auction where commodityID = ?";
			dataProcess.execute(sql,commodity.getId());
			sql = "delete from commodity where commodityID = ?";
			dataProcess.execute(sql,commodity.getId());
		}
	}
	public void drop(Commodity commodity){
		String sql = "delete from collection where commodityID = ?";
		dataProcess.execute(sql, commodity.getId());
		sql = "delete from auction where commodityID = ?";
		dataProcess.execute(sql,commodity.getId());
		sql = "delete from commodity where commodityID = ?";
		dataProcess.execute(sql,commodity.getId());
	}
	public ResultSet quertyCommodity(int id){
		String sql = "select * from commodity where commodityID = ?";
		ResultSet resultSet = dataProcess.executeQuery(sql, id);
		return resultSet;
	}
	public ResultSet quertyCommodity(String commodityName){
		String sql = "select * from commodity where name = ?";
		ResultSet resultSet = dataProcess.executeQuery(sql, commodityName);
		return resultSet;
	}
	public void updateType(int id,int type){
		String sql = "update commodity set dealType = ? where commodityID = ?";
		dataProcess.execute(sql,type,id);
	}
	public ResultSet quertyCommodity(){
		String sql = "select max(commodityID) from commodity";
		ResultSet resultSet = dataProcess.executeQuery(sql);
		return resultSet;
	}
}
