package com.tao.service;

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
}
