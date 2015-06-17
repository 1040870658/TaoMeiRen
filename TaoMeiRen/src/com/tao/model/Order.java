package com.tao.model;

import java.io.Serializable;

public class Order implements Serializable{
	public static int BUYER = 0;
	public static int SELLER = 1;
	private int id;
	private int commodityID;
	private int itemNum;
	private String mailOfbuyer;
	private String mailOfseller;
	private double deposit;
	private int dealType;
	
	public Order(){}
	public Order(int id,int commodityID,int itemNum,String mailOfbuyer,String mailOfseller,double deposit,int dealtype){
		this.id = id;
		this.commodityID =  commodityID;
		this.itemNum = itemNum;
		this.mailOfbuyer = mailOfbuyer;
		this.mailOfseller = mailOfseller;
		this.dealType = dealtype;
		this.deposit = deposit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommodityID() {
		return commodityID;
	}
	public void setCommodityID(int commodityID) {
		this.commodityID = commodityID;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getMailOfbuyer() {
		return mailOfbuyer;
	}
	public void setMailOfbuyer(String mailOfbuyer) {
		this.mailOfbuyer = mailOfbuyer;
	}
	public String getMailOfseller() {
		return mailOfseller;
	}
	public void setMailOfseller(String mailOfseller) {
		this.mailOfseller = mailOfseller;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public int getDealType() {
		return dealType;
	}
	public void setDealType(int dealType) {
		this.dealType = dealType;
	}
	
	
}
