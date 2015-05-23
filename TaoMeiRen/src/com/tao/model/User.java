package com.tao.model;

import java.io.Serializable;

/**
 * 　实体类
 *
 */
public class User implements Serializable{
	private String email;
	private String password;
	private double account;
	private double buyercredit;
	private double sellercredit;

	
	public User() {
	}
	public User(String email,String password,double account,double buyercredit,double sellercredit){
		this.email = email;
		this.password = password;
		this.account = account;
		this.buyercredit = buyercredit;
		this.setSellercredit(sellercredit);
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public double getBuyerCredit() {
		return buyercredit;
	}

	public void setBuyerCredit(double buyercredit) {
		this.buyercredit = buyercredit;
	}

	@Override
	public String toString() {
		return "User [username=" + email + ", password=" + password
				 + "]";
	}
	public void setSellercredit(double sellercredit) {
		this.sellercredit = sellercredit;
	}
	public double getSellercredit() {
		return sellercredit;
	}
}
