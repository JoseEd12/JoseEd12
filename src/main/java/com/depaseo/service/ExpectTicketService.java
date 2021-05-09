package com.depaseo.service;

import java.util.List;

import com.depaseo.call.model.cancel.ResponseCancelEntity;
import com.depaseo.call.model.reserva.ResponseReservaEntity;
import com.depaseo.call.model.transaction.ResponseTransactionEntity;
import com.depaseo.model.Productos;
import com.depaseo.model.Products;

public interface ExpectTicketService {

	


	ResponseTransactionEntity transactionProducto(String fecha, String reservationId);


	ResponseCancelEntity CancelProducto(String fecha, String reservationId);


	ResponseReservaEntity reservaProducto(String fecha, List<Products> productos);


	String catalogo();


	String available(Productos productos,String fecha);

	String sesiones(String fecha);






}
