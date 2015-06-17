package com.tao.service;

import java.util.ArrayList;

import com.tao.model.Commodity;
import com.tao.model.User;

public class FixService extends CommodityService{

	public ArrayList<Commodity> queryAllCommodity(){
		ArrayList<Commodity>total = super.queryAllCommodity();
		ArrayList<Commodity>fix = new ArrayList<Commodity>();
		for(Commodity element:total){
			if(Commodity.FIX == element.getType()){
				fix.add(element);
			}
		}
		return fix;
	}
	public ArrayList<Commodity>seperateConceret(int start,int length,ArrayList<Commodity> commodityList){
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		int num = 0;
		
		for(int i = start;num < length && i < commodityList.size();i ++){
			if(Commodity.FIX == commodityList.get(i).getType()){
				commodities.add( commodityList.get(i));
				num++;
			}
		}
		return commodities;
	}
	@Override
	public ArrayList<Commodity> queryCommodity(String commodityName) {
		// TODO Auto-generated method stub
		ArrayList<Commodity>total = super.queryCommodity(commodityName);
		ArrayList<Commodity>fix = new ArrayList<Commodity>();
		for(Commodity element:total){
			if(Commodity.FIX == element.getType()){
				fix.add(element);
			}
		}
		return fix;
	}	
}
