package com.capgemini.go.service;

import java.util.List;

import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.entities.OrderItemEntity;

public interface OrderService {
	public OrderDTO viewOrderDetails(String orderId);
	public List<OrderItemEntity> getAllProductsInOrder(String orderId);
	public String placeOrder(String userId);
	public String changeStatusOfOrder(String orderId,String status);
	public String getOrderStatus(String orderId);
	public String deleteOrderItem(String orderId,String productId);
	public void deleteOrderByID(String orderId);
}
