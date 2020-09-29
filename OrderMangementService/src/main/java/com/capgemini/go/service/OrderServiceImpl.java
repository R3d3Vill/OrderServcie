package com.capgemini.go.service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.go.dao.OrderDAO;
import com.capgemini.go.dao.OrderItemDAO;
import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.entities.OrderEntity;
import com.capgemini.go.entities.OrderItemEntity;
import com.capgemini.go.exceptions.IllegalOrderActionException;
import com.capgemini.go.exceptions.OrderNotFoundException;
import com.capgemini.go.id.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderServiceImpl implements OrderService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OrderDAO orderDao;
	@Autowired
	OrderItemDAO orderProduct;

	@Override
	public OrderDTO viewOrderDetails(String orderId) {
		Optional<OrderEntity> order = orderDao.findById(orderId);
		if (order.isPresent()) {
			return new OrderDTO(order.get());
		} else {
			throw new OrderNotFoundException("Order with Id: " + orderId + " not found.");
		}
	}

	@Override
	public List<OrderItemEntity> getAllProductsInOrder(String orderId) {
		if (orderDao.existsById(orderId)) {
			return orderProduct.findAllByOrderOrderId(orderId);
		} else {
			throw new OrderNotFoundException("Order Not Found");
		}
	}

	@Override
	public String placeOrder(String userId) {

		int tempNumber = orderDao.getAllOrderIds().size();
		String temp = orderDao.getAllOrderIds().get(tempNumber - 1);
		tempNumber = Integer.parseInt(temp.substring(1)) + 1;
		temp = "O" + tempNumber;

		// Cart Service method required to fetch all products in cart for a particular
		// user
		CartDTO[] cartItems = restTemplate.getForObject("http://cart-service/user/cart/"+userId,CartDTO[].class);
		// Received from Cart Service

		// saving to Order Table
		orderDao.save(new OrderEntity(temp, userId, 
										"Accepted", 
										restTemplate.getForObject("http://cart-service/user/cartValue/"+userId, Double.class), 
										LocalDate.now())
										);

		for (CartDTO product : cartItems) {
			// saving to order Product Mapping
			orderProduct.save(new OrderItemEntity(new Order(temp, product.getCartId().getProductId()),
					product.getQuantity(), fetchRetailer(product.getCartId().getProductId(), product.getQuantity())));
		}

		return temp;

	}

	public String fetchRetailer(String productId, int quantity) {
		Map<String, String> inputs=new HashMap<>();
			inputs.put("productId",productId);
			inputs.put("units",Integer.toString(quantity));
	String[] reatailerIds=	restTemplate.postForObject("http://inventory-service/api/getRetailerIds/",inputs, String[].class);
		if(reatailerIds.length>0) {
				return reatailerIds[(new SecureRandom().nextInt(reatailerIds.length))];
		}
		else {
			throw new IllegalOrderActionException("Not enough Units available to Order");
		}

	}

	@Override
	public String changeStatusOfOrder(String orderId, String status) {
		if (orderDao.existsById(orderId)) {
			Optional<OrderEntity> order=orderDao.findById(orderId) ;
			OrderEntity orderEnt;
			if(order.isPresent()) {
			orderEnt= order.get();
			orderEnt.setStatus(status);
			orderDao.save(orderEnt);
			}
			return status;
		} else {
			throw new OrderNotFoundException("Order with ID: " + orderId + " not found");
		}

	}

	@Override
	public String getOrderStatus(String orderId) {
		if (orderDao.existsById(orderId)) {
			return orderDao.trackOrderStatus(orderId);
		} else {
			throw new OrderNotFoundException("ORder Not Found");
		}
	}

	@Override
	public String deleteOrderItem(String orderId, String productId) {
		if (getOrderStatus(orderId).equals("In Transit") || getOrderStatus(orderId).equals("Delivered")) {
			throw new IllegalOrderActionException("Cant Delete Item now");
		} else {
			orderProduct.deleteById(new Order(orderId, productId));
			return "Product : " + productId + " Deleted Succesfully";
		}

	}

	@Override
	public void deleteOrderByID(String orderId) {
		if (orderDao.existsById(orderId)) {
			orderDao.deleteById(orderId);
		} else {
			throw new OrderNotFoundException("Order with Id" + orderId + " not found.");
		}
	}

}
