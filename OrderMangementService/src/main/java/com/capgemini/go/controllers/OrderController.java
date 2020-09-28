package com.capgemini.go.controllers;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.entities.OrderItemEntity;
import com.capgemini.go.service.OrderService;

import io.swagger.annotations.Api;

@RestController
@Api
public class OrderController {
	@Autowired
	OrderService orderService;
	
	
	@GetMapping(value="/user/order/{orderId}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") String orderId)
	{
		
		return new ResponseEntity<OrderDTO>(orderService.viewOrderDetails(orderId),HttpStatus.OK);
	}
	
	
	@GetMapping(value="/user/order/products/{orderId}")
	public ResponseEntity<List<OrderItemEntity>> getAllProductsInOrder(@PathVariable("orderId")String orderId)
	{
		return new ResponseEntity<List<OrderItemEntity>>(orderService.getAllProductsInOrder(orderId),HttpStatus.OK);
	}
	
	
	@PostMapping(value="/user/order/")
	public ResponseEntity<String> placeOrder(@RequestBody String userId)
	{
		return new ResponseEntity<String>(orderService.placeOrder(userId),HttpStatus.OK);
	}
	
	@GetMapping(value="/user/order/status/{orderID}")
	public ResponseEntity<String> getOrderStatus(@PathVariable("orderID") String orderId)
	{
		return new ResponseEntity<String>(orderService.getOrderStatus(orderId),HttpStatus.OK);
	}
	
	@PutMapping(value="/admin/order/status/")
	public ResponseEntity<String> updateStatus(@RequestBody Map<String, String> orderStatus)
	{
		return new ResponseEntity<String>(orderService.changeStatusOfOrder(orderStatus.get("orderId"),orderStatus.get("status")),HttpStatus.OK);
	}
	
	@PutMapping(value="/user/order/products/")
	public ResponseEntity<String> deleteOrderItem(@RequestBody Map<String, String> orderItem)
	{
		return new ResponseEntity<String>(orderService.deleteOrderItem(orderItem.get("orderId"),orderItem.get("productId")),HttpStatus.OK);
	}
	
	
}
