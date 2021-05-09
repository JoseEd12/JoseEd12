package com.depaseo.call.model.transaction;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseTransactionEntity {

	@JsonProperty("TransactionId")
	private String transactionId;
	
	@JsonProperty("TransactionDateTime")
	private String transactionDateTime;
	
	@JsonProperty("AccessDateTime")
	private String accessDateTime;
	
	@JsonProperty("TotalRetailPrice")
	private int totalRetailPrice;
	
	@JsonProperty("Products")
	private List<Products> products;
	
	@JsonProperty("Success")
	private String success;
	
	@JsonProperty("Documents")
	private List<Documents> documents;
	
	@JsonProperty("ErrorMessage")
	private String errorMessage;
	
	
	public ResponseTransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}




	public String getErrorMessage() {
		return errorMessage;
	}




	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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




	public String getTransactionId() {
		return transactionId;
	}




	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}




	public String getTransactionDateTime() {
		return transactionDateTime;
	}



	public List<Documents> getDocuments() {
		return documents;
	}




	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}




	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}




	@Override
	public String toString() {
		return "ResponseTransactionEntity [transactionId=" + transactionId + ", transactionDateTime="
				+ transactionDateTime + ", accessDateTime=" + accessDateTime + ", totalRetailPrice=" + totalRetailPrice
				+ ", products=" + products + ", success=" + success + ", documents=" + documents + ", errorMessage="
				+ errorMessage + "]";
	}



	
}
