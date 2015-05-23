package com.tao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Collection implements Serializable{
	private int collectionID;
	private Commodity commodity;
	private Date deadline;
	private int activeNum;
	private int currentNum;
	private double collectivePrice;
	private ArrayList<String> mailOfparticipants;
	
	public int getCollectionID() {
		return collectionID;
	}
	public void setCollectionID(int collectionID) {
		this.collectionID = collectionID;
	}
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
	public int getActiveNum() {
		return activeNum;
	}
	public void setActiveNum(int activeNum) {
		this.activeNum = activeNum;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public double getCollectivePrice() {
		return collectivePrice;
	}
	public void setCollectivePrice(double collectivePrice) {
		this.collectivePrice = collectivePrice;
	}
	public ArrayList<String> getMailOfparticipants() {
		return mailOfparticipants;
	}
	public void setMailOfparticipants(ArrayList<String> mailOfparticipants) {
		this.mailOfparticipants = mailOfparticipants;
	}
	
	
}
