package com.depaseo.call.model.transaction;

import java.util.List;

public class RequestTransactionEntity {

	private String ApiKey;
	private String IsTest;
	private String AccessDateTime;
	private List<ProductsOnlyID> Products;
	private String ReservationId;
	
	public RequestTransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestTransactionEntity(String apiKey, String isTest, String accessDateTime,
			List<com.depaseo.call.model.transaction.ProductsOnlyID> products) {
		super();
		ApiKey = apiKey;
		IsTest = isTest;
		AccessDateTime = accessDateTime;
		Products = products;
	}
	
	
	public RequestTransactionEntity(String apiKey, String isTest, String accessDateTime,
			List<com.depaseo.call.model.transaction.ProductsOnlyID> products, String reservationId) {
		super();
		ApiKey = apiKey;
		IsTest = isTest;
		AccessDateTime = accessDateTime;
		Products = products;
		ReservationId = reservationId;
	}
	public String getApiKey() {
		return ApiKey;
	}
	public void setApiKey(String apiKey) {
		ApiKey = apiKey;
	}
	public String getIsTest() {
		return IsTest;
	}
	public void setIsTest(String isTest) {
		IsTest = isTest;
	}
	public String getAccessDateTime() {
		return AccessDateTime;
	}
	public void setAccessDateTime(String accessDateTime) {
		AccessDateTime = accessDateTime;
	}

	
	
	public List<ProductsOnlyID> getProducts() {
		return Products;
	}
	public void setProducts(List<ProductsOnlyID> products) {
		Products = products;
	}
	public String getReservationId() {
		return ReservationId;
	}
	public void setReservationId(String reservationId) {
		ReservationId = reservationId;
	}
	@Override
	public String toString() {
		return "RequestTransactionEntity [ApiKey=" + ApiKey + ", IsTest=" + IsTest + ", AccessDateTime="
				+ AccessDateTime + ", Products=" + Products + ", ReservationId=" + ReservationId + "]";
	}
	
	
	
	
	
}
