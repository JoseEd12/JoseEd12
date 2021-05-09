package com.depaseo.call.model.reserva;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseReservaEntity {

	@JsonProperty("ReservationId")
	private String reservationId;
	@JsonProperty("MinutesToExpiry")
	private int minutesToExpiry;
	@JsonProperty("AccessDateTime")
	private String accessDateTime;
	@JsonProperty("TotalRetailPrice")
	private int totalRetailPrice;
	@JsonProperty("Products")
	private List<Products> products;
	@JsonProperty("Success")
	private String success;
	@JsonProperty("ErrorMessage")
	private String errorMessage;
	
	
	
	public ResponseReservaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getReservationId() {
		return reservationId;
	}



	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}



	public int getMinutesToExpiry() {
		return minutesToExpiry;
	}



	public void setMinutesToExpiry(int minutesToExpiry) {
		this.minutesToExpiry = minutesToExpiry;
	}



	public String getAccessDateTime() {
		return accessDateTime;
	}



	public void setAccessDateTime(String accessDateTime) {
		this.accessDateTime = accessDateTime;
	}



	public int getTotalRetailPrice() {
		return totalRetailPrice;
	}



	public void setTotalRetailPrice(int totalRetailPrice) {
		this.totalRetailPrice = totalRetailPrice;
	}



	public List<Products> getProducts() {
		return products;
	}



	public void setProducts(List<Products> products) {
		this.products = products;
	}



	public String getSuccess() {
		return success;
	}



	public void setSuccess(String success) {
		this.success = success;
	}



	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	@Override
	public String toString() {
		return "ResponseReservaEntity [reservationId=" + reservationId + ", minutesToExpiry=" + minutesToExpiry
				+ ", accessDateTime=" + accessDateTime + ", totalRetailPrice=" + totalRetailPrice + ", products="
				+ products + ", success=" + success + ", errorMessage=" + errorMessage + "]";
	}

	


	
	
}
