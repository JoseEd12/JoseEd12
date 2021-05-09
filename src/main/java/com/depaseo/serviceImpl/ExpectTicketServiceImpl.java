package com.depaseo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaseo.MyProperties;
import com.depaseo.call.ExperTicket;
import com.depaseo.call.model.cancel.RequestCancelEntity;
import com.depaseo.call.model.cancel.ResponseCancelEntity;
import com.depaseo.call.model.reserva.RequestReservaEntity;
import com.depaseo.call.model.reserva.ResponseReservaEntity;
import com.depaseo.call.model.transaction.ProductsOnlyID;
import com.depaseo.call.model.transaction.RequestTransactionEntity;
import com.depaseo.call.model.transaction.ResponseTransactionEntity;
import com.depaseo.model.Productos;
import com.depaseo.model.Products;
import com.depaseo.repository.ProductosReservationidRepository;
import com.depaseo.service.ExpectTicketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExpectTicketServiceImpl implements ExpectTicketService {

	@Autowired
	ProductosReservationidRepository productosReservationidRepository;
	@Autowired
	MyProperties properties;

	@Override
	public ResponseReservaEntity reservaProducto(String fecha, List<Products> productos) {
		String modo = "false"; // si es falso lanza la peticion en real a true es para verificar si hay
								// respuesta(modo prueba)
		RequestReservaEntity requestbody = new RequestReservaEntity();

		requestbody.setApiKey(properties.api);
		requestbody.setIsTest(modo);
		requestbody.setAccessDateTime(fecha);
		requestbody.setProducts(productos);
		log.info("ExpectTicketServiceImpl | reservaProducto -> request: " + requestbody.toString());
		return ExperTicket.reserva(requestbody,properties.url);

	}

	@Override
	public ResponseTransactionEntity transactionProducto(String fecha, String reservationId) {
		String modo = "false"; // si es falso lanza la peticion en real a true es para verificar si hay
								// respuesta(modo prueba)
		RequestTransactionEntity requestbody = new RequestTransactionEntity();

		requestbody.setApiKey(properties.api);
		requestbody.setIsTest(modo);
		requestbody.setAccessDateTime(fecha);
		requestbody.setProducts(listaProductos(reservationId));
		requestbody.setReservationId(reservationId);
		log.info("ExpectTicketServiceImpl | transactionProducto - requestbody: " + requestbody.toString());
		return ExperTicket.transaccion(requestbody,properties.url);

	}

	@Override
	public String catalogo() {

		return ExperTicket.catalogo(properties.url);
	}
	




	@Override
	public String sesiones(String fecha) {

		return ExperTicket.session(fecha,properties.url);
	}
	
	@Override
	public String available( Productos productos,String fecha) {
		String path ="";
		for (Products p: productos.getProducts()) {
			path = path+"&ProductIds="+p.getProductId();
		}
		
		path = path+"&Dates=" + fecha;
		log.info("ExpectTicketServiceImpl | available - path: " + path );
		return ExperTicket.available(properties.url,path,fecha);
		 
	}

	@Override
	public ResponseCancelEntity CancelProducto(String fecha, String reservationId) {
		String modo = "false"; // si es falso lanza la peticion en real a true es para verificar si hay
		// respuesta(modo prueba)
		RequestCancelEntity requestbody = new RequestCancelEntity();
		requestbody.setIsTest(modo);
		requestbody.setApiKey(properties.api);
		requestbody.setReservationId(reservationId);
		log.info("ExpectTicketServiceImpl | transactionProducto - requestbody: " + requestbody.toString());
		return ExperTicket.cancel(requestbody,properties.url);

	}

	private ArrayList<ProductsOnlyID> listaProductos(String reservationId) {
		ArrayList<ProductsOnlyID> productList = new ArrayList<ProductsOnlyID>();
		productosReservationidRepository.findProducByReservationID(reservationId).stream().forEach(p -> {
			productList.add(new ProductsOnlyID(p.getProductId()));
		});
		return productList;
	}

	@SuppressWarnings("unused")
	private ArrayList<Products> listaProductos(int producto, int adulto, int niño) {
		ArrayList<Products> productList = new ArrayList<Products>();
		if (adulto > 0) {

			switch (producto) {

			case 30:
				productList.add(new Products(ExperTicket.ENTRADA_DIURNA_ADULTO, adulto));
				break;
			case 20:
				productList.add(new Products(ExperTicket.ENTRADA_NOCTURNA_ADULTO_CLASICA, adulto));
				break;
			case 10:
				// todavia por pensar no hay producto concreto hay que seleccionar los dos
				// anteriores juntos
				break;
			}
		}
		if (niño > 0) {

			switch (producto) {

			case 30:
				productList.add(new Products(ExperTicket.ENTRADA_DIURNA_NIÑO, niño));
				break;
			case 20:
				productList.add(new Products(ExperTicket.ENTRADA_NOCTURNA_NIÑO_CLASICA, niño));
				break;
			case 10:
				// todavia por pensar no hay producto concreto hay que seleccionar los dos
				// anteriores juntos
				break;
			}
		}
		return productList;
	}

}
