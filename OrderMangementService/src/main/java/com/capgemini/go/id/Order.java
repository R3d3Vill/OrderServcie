package com.capgemini.go.id;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class Order implements Serializable {
	
	
	@Column(name="order_id")
	private String orderId;
	
	
	@Override
	public String toString() {
		return "OrderProductId [orderId=" + orderId + ", productId=" + productId + "]";
	}

	@Column(name="product_id")
	private String productId;
	
	
	
	public Order() {
	}
	
	public Order(String orderId, String productId) {
		
		this.orderId = orderId;
		this.productId = productId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}


	@Override
	public boolean equals(Object o) {
		
		if (o == null)
		    return false;

		  if (this.getClass() != o.getClass())
		    return false;

	
        Order that= (Order) o;
        return orderId.equals(that.orderId)&&productId.equals(that.productId);
		
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orderId,productId);
	}
	
 
}
