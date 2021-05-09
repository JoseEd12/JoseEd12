package com.depaseo.call.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductsOnlyID {
	@JsonProperty("ProductId")
	private String productId;

	public ProductsOnlyID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductsOnlyID(String productId) {
		super();
		this.productId = productId;

	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ProductsOnlyID [productId=" + productId + "]";
	}

}
