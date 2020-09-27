package com.capgemini.go.dto;

import java.time.LocalDate;

import com.capgemini.go.entities.OrderEntity;


public class OrderDTO {

	String orderId;
	String userId;
	String status;
	double amount;
	LocalDate orderDate;
	
	
	
	public OrderDTO() {
		super();
	}
	
	public OrderDTO(String orderId, String userId, String status, double amount,
			LocalDate orderDate) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.status = status;
		this.amount = amount;
		this.orderDate = orderDate;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public OrderDTO(String userId, String status, double amount, LocalDate orderDate) {
		super();
		this.userId = userId;
		this.status = status;
		this.amount = amount;
		this.orderDate = orderDate;
	}

	public OrderDTO(OrderEntity orderEntity) {
		this.orderId=orderEntity.getOrderId();
		this.amount=orderEntity.getAmount();
		this.userId=orderEntity.getUserId();
		this.status=orderEntity.getStatus();
		this.orderDate=orderEntity.getOrderDate();
	}

	
	
	
	
}
