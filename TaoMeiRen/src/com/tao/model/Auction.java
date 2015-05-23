package com.tao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Auction implements Serializable{
	private Commodity commodity;
	private Date deadline;
	private double activePrice;
	private double currentPrice;
	private String currentMail;
	private ArrayList<String> mailOfparticipants;
	private int auctionID;
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public double getActivePrice() {
		return activePrice;
	}
	public void setActivePrice(double activePrice) {
		this.activePrice = activePrice;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getCurrentMail() {
		return currentMail;
	}
	public void setCurrentMail(String currentMail) {
		this.currentMail = currentMail;
	}
	public ArrayList<String> getMailOfparticipants() {
		return mailOfparticipants;
	}
	public void setMailOfparticipants(ArrayList<String> mailOfparticipants) {
		this.mailOfparticipants = mailOfparticipants;
	}
	public int getAuctionID() {
		return auctionID;
	}
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}
	
}
