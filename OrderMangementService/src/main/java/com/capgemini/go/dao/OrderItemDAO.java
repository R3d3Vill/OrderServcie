package com.capgemini.go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.go.entities.OrderItemEntity;
import com.capgemini.go.id.Order;

@Repository
public interface OrderItemDAO extends CrudRepository<OrderItemEntity, Order> {
	
	public List<OrderItemEntity> findAllByOrderOrderId(String orderId);
	
	@Query(value= "SELECT o.order.productId FROM OrderItemEntity o WHERE o.order.orderId=:orderID")
	public List<String> getAllProductsInOrder(@Param("orderID")String orderId);

}
