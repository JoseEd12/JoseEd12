package com.depaseo.call.model.reserva;

import java.util.List;

public class RequestReservaEntity {

	private String ApiKey;
	private String IsTest;
	private String AccessDateTime;
	private List<com.depaseo.model.Products> Products;
	//private String ReservationId;
	
	public RequestReservaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestReservaEntity(String apiKey, String isTest, String accessDateTime,
			List<com.depaseo.model.Products> products) {
		super();
		ApiKey = apiKey;
		IsTest = isTest;
		AccessDateTime = accessDateTime;
		Products = products;
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
	public List<com.depaseo.model.Products> getProducts() {
		return Products;
	}
	public void setProducts(List<com.depaseo.model.Products> products) {
		Products = products;
	}
	

	@Override
	public String toString() {
		return "RequestReservaEntity [ApiKey=" + ApiKey + ", IsTest=" + IsTest + ", AccessDateTime=" + AccessDateTime
				+ ", Products=" + Products + "]";
	}
	
	
	
}
