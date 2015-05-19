package com.tao.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

public class Commodity {
	private final static int FIX = 1;
	private final static int COLLECTIVE = 2;
	private final static int AUCTION = 3;
	private double commodityCredit;
	private int id;
	private ArrayList<String> imageUrl = new ArrayList<String>();
	private String mailOfseller;
	private double price;
	private String name;
	private String description;
	private int stock;
	private int type;
	public final static Map<String, Integer> TYPE = new HashMap<String, Integer>(){
	};
	static{
		TYPE.put("一口价", FIX);
		TYPE.put("团购",COLLECTIVE);
		TYPE.put("竞拍", AUCTION);
	}
	public double getCommodityCredit() {
		return commodityCredit;
	}
	public void setCommodityCredit(double commodityCredit) {
		this.commodityCredit = commodityCredit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<String> getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(ArrayList<String> imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getMailOfseller() {
		return mailOfseller;
	}
	public void setMailOfseller(String mailOfseller) {
		this.mailOfseller = mailOfseller;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public static int getFix() {
		return FIX;
	}
	public static int getCollective() {
		return COLLECTIVE;
	}
	public static int getAuction() {
		return AUCTION;
	}
	
	
}
