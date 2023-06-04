package com.bullish.api.model;

// Purchased item
public class Purchase {
	private String id;
	private Product product;
	private long amount;
	
	public Purchase (String id, Product product, long amount) {
		this.id = id;
		this.product = product;
		this.amount = amount;
	}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }    
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
}

