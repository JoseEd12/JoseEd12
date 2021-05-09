package com.depaseo.call.model.reserva;

import java.util.List;

import com.depaseo.call.model.Tickets;
import com.depaseo.call.model.transaction.SalesDocumentSettings;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Products {
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("Tickets")
	private List<Tickets> tickets;
	@JsonProperty("Quantity")
	private Integer quantity;
	@JsonProperty("Success")
	private boolean success;
	@JsonProperty("RetailPrice")
	private double retailprice;
	@JsonProperty("ProviderId")
	private String providerId;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("AccessDateCriteria")
	private String accessDateCriteria;
	@JsonProperty("SalesDocumentSettings")
	private SalesDocumentSettings salesDocumentSettings;
	@JsonProperty("ErrorMessage")
	private String errorMessage;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(String productId) {
		super();
		this.productId = productId;
		this.quantity =  null;
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



	public List<Tickets> getTickets() {
		return tickets;
	}



	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}


	public double getRetailprice() {
		return retailprice;
	}

	public void setRetailprice(double retailprice) {
		this.retailprice = retailprice;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccessDateCriteria() {
		return accessDateCriteria;
	}

	public void setAccessDateCriteria(String accessDateCriteria) {
		this.accessDateCriteria = accessDateCriteria;
	}

	public SalesDocumentSettings getSalesDocumentSettings() {
		return salesDocumentSettings;
	}

	public void setSalesDocumentSettings(SalesDocumentSettings salesDocumentSettings) {
		this.salesDocumentSettings = salesDocumentSettings;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", tickets=" + tickets + ", quantity=" + quantity + ", success="
				+ success + ", retailprice=" + retailprice + ", providerId=" + providerId + "]";
	}




	
}
