package com.depaseo.call.model.transaction;

import java.util.List;

import com.depaseo.call.model.Tickets;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Products {
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("Tickets")
	private List<Tickets> tickets;
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

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(String productId) {
		super();
		this.productId = productId;

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



	@Override
	public String toString() {
		return "Products [productId=" + productId + ", tickets=" + tickets +", success="
				+ success + ", retailprice=" + retailprice + ", providerId=" + providerId + "]";
	}




	
}
