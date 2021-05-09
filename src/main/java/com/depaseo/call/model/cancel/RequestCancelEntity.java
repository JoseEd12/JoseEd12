package com.depaseo.call.model.cancel;

public class RequestCancelEntity {

	private String ApiKey;
	private String ReservationId;
	private String IsTest;

	public RequestCancelEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public RequestCancelEntity(String apiKey, String reservationId, String isTest) {
		super();
		ApiKey = apiKey;
		ReservationId = reservationId;
		IsTest = isTest;
	}



	public String getIsTest() {
		return IsTest;
	}

	public void setIsTest(String isTest) {
		IsTest = isTest;
	}

	public String getApiKey() {
		return ApiKey;
	}

	public void setApiKey(String apiKey) {
		ApiKey = apiKey;
	}

	public String getReservationId() {
		return ReservationId;
	}

	public void setReservationId(String reservationId) {
		ReservationId = reservationId;
	}

	@Override
	public String toString() {
		return "RequestCancelEntity [ApiKey=" + ApiKey + ", ReservationId=" + ReservationId + ", IsTest=" + IsTest
				+ "]";
	}

	
}
