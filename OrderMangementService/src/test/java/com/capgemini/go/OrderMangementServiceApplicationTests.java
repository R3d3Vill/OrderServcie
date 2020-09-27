package com.capgemini.go;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.go.exceptions.OrderNotFoundException;
import com.capgemini.go.service.OrderService;

@SpringBootTest
class OrderMangementServiceApplicationTests {
	
	
	@Autowired
	OrderService orderService;
	
	@Test
	void contextLoads() {
		assertThat(orderService).isNotNull();
	}

	@Test
	void getStatusPass()
	{
		assertThat(orderService.getOrderStatus("O10000001")).isNotNull();
	}
	
	@Test
	void getOrderByIdPass()
	{
		assertThat(orderService.viewOrderDetails("O10000001")).isNotNull();
	}
	
	@Test
	void getOrderByIdNotFoundException()
	{
		assertThrows(OrderNotFoundException.class,()->{orderService.viewOrderDetails("O10000002");});
		
	}
	
	@Test
	void addProductPass()
	{
		assertThat(orderService.placeOrder("U10002")).isNotNull();
		orderService.deleteOrderByID("O10000002");
	}
	
	
	
}
