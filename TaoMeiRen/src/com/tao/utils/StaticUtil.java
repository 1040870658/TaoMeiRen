package com.tao.utils;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tao.model.Commodity;
import com.tao.service.CommodityService;

public class StaticUtil {
	static public int getArraySize(Commodity[] commodities){
		for(int i = 0;i != commodities.length;i ++){
			if(commodities[i]==null )return i;
		}
		return commodities.length;
	}
	static public void updateContext(HttpServletRequest request, HttpServletResponse response,int type){
			CommodityService commodityService= new CommodityServiceFactory().createService(type);
			ArrayList<Commodity> all_commodities = commodityService.queryAllCommodity();
			ArrayList<Commodity> commodityArray = commodityService.seperateConceret(0, 16, all_commodities);
			ArrayList<Commodity>commodities_detail  = new ArrayList<Commodity>();
			request.getServletContext().setAttribute("all_concrete_commodities", commodityArray);
	}
	static public ArrayList<String> seperateText(String src){
		ArrayList<String> stringlList = new ArrayList<String>();
		String[] strings = src.split(",");
		for(int i = 0;i != strings.length;i ++){
			stringlList.add(strings[i]);
		}
		return stringlList;
	}
}
