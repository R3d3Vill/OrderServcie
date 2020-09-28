package com.capgemini.go.id;

import java.io.Serializable;

public class CartId implements Serializable {
	
	private String userId;
	
	public String toString() {
		return "CartId  [userId=" + userId + ", productId=" + productId + "]";
	}

	private String productId;
	
	
	
	public CartId() {
	}
	
	public CartId(String userId, String productId) {
		
		this.userId = userId;
		this.productId = productId;
	}
	public String getuserId() {
		return userId;
	}
	public void setuserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
 
}
