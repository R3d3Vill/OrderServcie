package com.capgemini.go.service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.dao.OrderDAO;
import com.capgemini.go.dao.OrderItemDAO;
import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.entities.OrderEntity;
import com.capgemini.go.entities.OrderItemEntity;
import com.capgemini.go.exceptions.IllegalOrderActionException;
import com.capgemini.go.exceptions.OrderNotFoundException;
import com.capgemini.go.id.CartId;
import com.capgemini.go.id.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderServiceImpl implements OrderService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderDAO orderDao;
	@Autowired
	OrderItemDAO orderProduct;

	@Override
	public OrderDTO viewOrderDetails(String orderId) {

		if (orderDao.existsById(orderId)) {
			return new OrderDTO(orderDao.findById(orderId).get());
		} else {
			throw new OrderNotFoundException("Order with Id: " + orderId + " not found.");
		}
	}

	@Override
	public List<OrderItemEntity> getAllProductsInOrder(String orderId) {
		if (orderDao.existsById(orderId)) {
			return orderProduct.findAllByOrderOrderId(orderId);
		} else
			throw new OrderNotFoundException("Order Not Found");

	}

	@Override
	public String placeOrder(String userId) {

		int tempNumber = orderDao.getAllOrderIds().size();
		String temp = orderDao.getAllOrderIds().get(tempNumber - 1);
		tempNumber = Integer.parseInt(temp.substring(1)) + 1;
		temp = "O" + tempNumber;

		// Cart Service method required to fetch all products in cart for a particular
		// user
		List<CartDTO> cartItems = new LinkedList<CartDTO>();
		cartItems.add(new CartDTO(new CartId("U10001", "P10001"), 3));
		cartItems.add(new CartDTO(new CartId("U10001", "P10002"), 1));

		// Received from Cart Service
		double amount = 1700;
		// Todays Date
		LocalDate dateOfOrder = LocalDate.now();

		// saving to Order Table
		orderDao.save(new OrderEntity(temp, userId, "Accepted", amount, dateOfOrder));

		for (CartDTO product : cartItems) {
			// saving to order Product Mapping
			orderProduct.save(new OrderItemEntity(new Order(temp, product.getCartId().getProductId()),
					product.getQuantity(), fetchRetailer(product.getCartId().getProductId(), product.getQuantity())));
		}

		return temp;

	}

	public String fetchRetailer(String productId, int quantity) {
		// List of Retailers for Product Id and stocks greater than quantity from
		// Inventory Service
		List<String> retailerIds = new LinkedList<String>();
		retailerIds.add("R10002");
		retailerIds.add("R10003");
		return retailerIds.get(new Random().nextInt(retailerIds.size()));

	}

	@Override
	public String changeStatusOfOrder(String orderId, String status) {
		if (orderDao.existsById(orderId)) {
			OrderEntity order ;
			if(orderDao.findById(orderId).isPresent()) {
			order= orderDao.findById(orderId).get();
			order.setStatus(status);
			orderDao.save(order);
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
		} else
			throw new OrderNotFoundException("Order with Id" + orderId + " not found.");

	}

}
