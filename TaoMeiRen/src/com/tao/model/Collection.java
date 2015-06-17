package com.tao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.sql.Timestamp;

public class Collection implements Serializable{
	private int collectionID;
	private Commodity commodity;
	private Timestamp deadline;
	private int activeNum;
	private int currentNum;
	private double collectivePrice;
	private ArrayList<String> mailOfparticipants;
	
	
	public Collection(int collectionID, Commodity commodity, Timestamp deadline,
			int activeNum, int currentNum, double collectivePrice,
			ArrayList<String> mailOfparticipants) {
		super();
		this.collectionID = collectionID;
		this.commodity = commodity;
		this.deadline = deadline;
		this.activeNum = activeNum;
		this.currentNum = currentNum;
		this.collectivePrice = collectivePrice;
		this.mailOfparticipants = mailOfparticipants;
	}
	public Collection(){super();}
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
	public Timestamp getDeadline() {
		return deadline;
	}
	public void setDeadline(Timestamp deadline) {
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
