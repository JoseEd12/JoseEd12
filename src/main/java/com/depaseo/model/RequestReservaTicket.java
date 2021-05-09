package com.depaseo.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class RequestReservaTicket {



	@JsonFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	@NotNull(message = "fecha no puede estar vacio")
	private Date accessDate;

	private String mail;

	//@NotNull(message = "Productos: no puede estar vacio")
	@JsonProperty("Productspuydufou")
	private List<com.depaseo.model.Products> productspuydufou;

	@JsonProperty("ProductsBus")
	private Productosbus productBus;
	
	public RequestReservaTicket() {

	}


	

	public Productosbus getProductBus() {
		return productBus;
	}

	public void setProductBus(Productosbus productBus) {
		this.productBus = productBus;
	}

	public Date getaccessDate() {
		return accessDate;
	}

	public void setFecha(Date accessDate) {
		this.accessDate = accessDate;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}




	public List<com.depaseo.model.Products> getProductspuydufou() {
		return productspuydufou;
	}




	public void setProductspuydufou(List<com.depaseo.model.Products> productspuydufou) {
		this.productspuydufou = productspuydufou;
	}




	@Override
	public String toString() {
		return "RequestReservaTicket [accessDate=" + accessDate + ", mail=" + mail + ", productspuydufou="
				+ productspuydufou + ", productBus=" + productBus + "]";
	}

	
	

}
