package com.tao.model;

import java.io.Serializable;

public class Order implements Serializable
{
	public int orderID;
	public int commodityID;
	public int itemNum;
	public String mailOfbuyer;
	public String mailOfseller;
	public double deposit;
	public int dealType;
}
