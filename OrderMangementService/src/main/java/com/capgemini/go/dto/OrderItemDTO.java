package com.capgemini.go.dto;



import com.capgemini.go.id.Order;

public class OrderItemDTO {
	
	private Order order;
	private String retailerId;
	private int quantity;

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderItemDTO(Order orderProductId, int quantity,String retailerId) {
		super();
		this.order = orderProductId;
		this.quantity = quantity;
		this.retailerId=retailerId;
	}

	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public OrderItemDTO() {
		super();
	}
	
	
	
	

}
