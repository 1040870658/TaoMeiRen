package com.tao.model;

import java.io.Serializable;

public class PersonalComment implements Serializable{
	private int commentID;
	private int commodityID;
	double point;
	String comment;
	String email;
	
	public PersonalComment(){
		
	}
	public PersonalComment(int commentID, int commodityID, double point,
			String comment, String email) {
		super();
		this.commentID = commentID;
		this.commodityID = commodityID;
		this.point = point;
		this.comment = comment;
		this.email = email;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public int getCommodityID() {
		return commodityID;
	}
	public void setCommodityID(int commodityID) {
		this.commodityID = commodityID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
