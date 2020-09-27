package com.capgemini.go.dto;

import com.capgemini.go.id.InventoryId;

public class InventoryDTO {

	private InventoryId inventoryId;
	private int quantity;
	
	
	public InventoryDTO(InventoryId inventoryId, int quantity) {
		super();
		this.inventoryId = inventoryId;
		this.quantity = quantity;
	}
	
	public InventoryId getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(InventoryId inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
