package com.depaseo.model;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class RequestTransactionTicket {

	@JsonFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	@NotNull(message = "fecha no puede estar vacio")
	private Date accessDate;

	private int requestId;

	public RequestTransactionTicket() {

	}

	public Date getFecha() {
		return accessDate;
	}

	public void setFecha(Date accessDate) {
		this.accessDate = accessDate;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "RequestTransactionTicket [accessDate=" + accessDate + ", requestId=" + requestId + "]";
	}

}
