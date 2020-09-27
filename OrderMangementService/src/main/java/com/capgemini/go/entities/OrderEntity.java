package com.capgemini.go.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.capgemini.go.dto.OrderDTO;



@Entity
@Table(name = "order_table")
public class OrderEntity {
	
	@Id
	String orderId;
	String userId;
	String status;
	double amount;
	LocalDate orderDate;
	
	
	
	public OrderEntity() {
		super();
	}
	
	public OrderEntity(OrderDTO orderDto) {
		this.orderId=orderDto.getOrderId();
		this.userId=orderDto.getUserId();
		this.amount=orderDto.getAmount();
		this.status=orderDto.getStatus();
		this.orderDate=orderDto.getOrderDate();
	}
	
	public OrderEntity(String orderId, String userId, String status, double amount,
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

	public OrderEntity(String userId, String status, double amount, LocalDate orderDate) {
		super();
		this.userId = userId;
		this.status = status;
		this.amount = amount;
		this.orderDate = orderDate;
	}

	
	
	
	
}
