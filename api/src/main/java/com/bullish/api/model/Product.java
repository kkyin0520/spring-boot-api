package com.bullish.api.model;

// Listed product
public class Product {
	private String id;
	private String name;
	private Boolean isAvailable;
	private double price;
	private String dealDescription;
	private long discountLot;
	private double discountRate;
	
	public Product (String id, String name, Boolean isAvailable, double price) {
		this.id = id;
		this.name =  name;
		this.isAvailable = isAvailable;
		this.price = price;
		this.dealDescription = "";
		this.discountLot = 1;
		this.discountRate = 1;
	}
	
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }    
    public Boolean getIsAvailable() {
    	return isAvailable;
    }
    public void setIsAvailable(boolean isAvailable) {
    	this.isAvailable = isAvailable;
    }
    public double getPrice() {
    	return price;
    }
    public void setPrice(double price) {
    	this.price = price;
    }
    public String getDealDescription() {
    	return dealDescription;
    }
    public void setDealDescription(String deal) {
    	this.dealDescription = deal;
    }
    public long getDiscountLot() {
    	return discountLot;
    }
    public void setDiscountLot(long discountLot) {
    	this.discountLot = discountLot;
    }
    public double getDiscountRate() {
    	return discountRate;
    }
    public void setDiscountRate(double discountRate) {
    	this.discountRate = discountRate;
    }    
}
