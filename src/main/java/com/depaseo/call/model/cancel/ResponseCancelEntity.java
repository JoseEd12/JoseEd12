package com.depaseo.call.model.cancel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCancelEntity {

	@JsonProperty("Success")
	private String success;
	@JsonProperty("ErrorMessage")
	private String errorMessage;
	@JsonProperty("Timestamp")
	private String timestamp;
	public ResponseCancelEntity() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ResponseCancelEntity [success=" + success + ", errorMessage=" + errorMessage + ", timestamp="
				+ timestamp + "]";
	}

	public ResponseCancelEntity(String success, String errorMessage, String timestamp) {
		super();
		this.success = success;
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
	}





}
