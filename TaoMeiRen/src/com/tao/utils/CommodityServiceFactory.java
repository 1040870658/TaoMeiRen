package com.tao.utils;

import com.tao.model.Auction;
import com.tao.model.Commodity;
import com.tao.service.AuctionService;
import com.tao.service.CollectionService;
import com.tao.service.CommodityService;
import com.tao.service.FixService;

public class CommodityServiceFactory {
	public CommodityService createService(int type){
		switch(type){
		case Commodity.FIX:return new FixService();
		case Commodity.COLLECTIVE:return new CollectionService();
		case Commodity.AUCTION:return new AuctionService();
		default: return new CommodityService();
		}
	}
}
