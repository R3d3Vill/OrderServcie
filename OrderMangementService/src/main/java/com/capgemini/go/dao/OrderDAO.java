package com.capgemini.go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.go.entities.OrderEntity;

@Repository
public interface OrderDAO extends CrudRepository<OrderEntity, String> {
	
	@Query(value = "SELECT o.orderId FROM OrderEntity o")
	public List<String> getAllOrderIds();
	
	@Query(value = "Select o.status FROM OrderEntity o WHERE o.orderId=:orderID ")
	public String trackOrderStatus(@Param("orderID")String orderId);
}
