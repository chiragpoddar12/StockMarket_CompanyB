package com.javainuse.model;
import org.springframework.beans.factory.annotation.Autowired;
public class Company {
	 @Autowired
	private String name;
	private int numberOfShares;
	private double priceOfShares;
	private double sharesDoublesWhen;
	public int buy_shares(int n) {
	if(n>numberOfShares) {
	return 0;
	}
	else {
		numberOfShares-=n;
		return n;
	}}
	public double doubletheshareprice(double priceOfShares) {
		return getPriceOfShares()*2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfShares() {
		return numberOfShares;
	}
	public void setNumberOfShares(int numberOfShares) {
		this.numberOfShares = numberOfShares;
	}
	public double getPriceOfShares() {
		return priceOfShares;
	}
	public void setPriceOfShares(double priceOfShares) {
		this.priceOfShares = priceOfShares;
	}
	public double getSharesDoublesWhen() {
		return sharesDoublesWhen;
	}
	public void setSharesDoublesWhen(double priceDoublesWhen) {
		this.sharesDoublesWhen = priceDoublesWhen;
	}

}
