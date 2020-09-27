package com.capgemini.go.id;

public class InventoryId {

	private String retailerId;
	private String productId;
	
	
	public InventoryId(String retailerId, String productId) {
		super();
		this.retailerId = retailerId;
		this.productId = productId;
	}
	
	public String getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	
	
}
