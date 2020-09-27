package com.capgemini.go.dto;

import com.capgemini.go.id.CartId;

public class CartDTO {

	private CartId cartId;
	private int quantity;
	
	public CartDTO(CartId cartId, int quantity) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
	}

	public CartId getCartId() {
		return cartId;
	}

	public void setCartId(CartId cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}