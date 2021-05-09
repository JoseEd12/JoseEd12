package com.depaseo.model;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Products {
	@JsonProperty("ProductId")
	private String productId;

	@JsonProperty("Quantity")
	private Integer quantity;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Products(String productId) {		
		this.productId = productId;
	}

	public Products(String productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", quantity=" + quantity + "]";
	}



	
}
