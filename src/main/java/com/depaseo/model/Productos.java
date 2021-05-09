package com.depaseo.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Productos {

	@JsonProperty("products")
	private List<Products> products;

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public Productos() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Productos [products=" + products + "]";
	}

}
