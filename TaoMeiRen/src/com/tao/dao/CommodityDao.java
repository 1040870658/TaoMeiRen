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
		System.out.println(sql);
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
}
