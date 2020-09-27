package com.capgemini.go.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.capgemini.go.dto.OrderItemDTO;
import com.capgemini.go.id.Order;

@Entity
@Table(name="order_product_mapping")
public class OrderItemEntity implements Serializable {
	
	@EmbeddedId
	private Order order;
	@Column(name = "retailer_id")
	private String retailerId;
	private int quantity;

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderItemEntity(Order orderProductId, int quantity,String retailerId) {
		super();
		this.order = orderProductId;
		this.quantity = quantity;
		this.retailerId=retailerId;
	}
	
	public OrderItemEntity(OrderItemDTO orderItem ) {
		this.order=orderItem.getOrder();
		this.quantity=orderItem.getQuantity();
		this.retailerId=orderItem.getRetailerId();
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

	public OrderItemEntity() {
		super();
	}
	
	
	
	

}
