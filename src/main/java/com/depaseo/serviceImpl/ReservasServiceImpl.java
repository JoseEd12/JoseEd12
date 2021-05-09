package com.depaseo.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaseo.model.RequestReservaTicket;
import com.depaseo.model.ResponseBus;
import com.depaseo.model.ResponseReservaTicket;
import com.depaseo.repository.ProductosReservationidRepository;
import com.depaseo.repository.ReservaPuidefuRepository;
import com.depaseo.repository.ReservaRepository;
import com.depaseo.service.AutobusService;
import com.depaseo.service.ExpectTicketService;
import com.depaseo.service.ReservasService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservasServiceImpl implements ReservasService {

	@Autowired
	AutobusService autobusService;
	@Autowired
	ReservaRepository ReservaRepository;
	@Autowired
	ExpectTicketService expectTicketService;
	@Autowired
	ProductosReservationidRepository productosReservationidRepository;
	@Autowired
	ReservaPuidefuRepository reservaPuidefuRepository;




	@Override
	public ResponseReservaTicket gestionReserva(RequestReservaTicket requestReservaTicket, String partner) {
		ResponseReservaTicket tratarDatos = new ResponseReservaTicket();
		boolean success = false;
		boolean successParque = true;
		boolean successBus = true;
		long epoch = System.currentTimeMillis();

		Map<String, String> estado = new HashMap<String, String>();

		String fechaFormateada = new SimpleDateFormat("yyyy-MM-dd").format(requestReservaTicket.getaccessDate());
		String fechaoperacion = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		// aqui se reserva el autobus si hay plazas
		if (requestReservaTicket.getProductBus() != null) {
			tratarDatos.setProductosBus(autobusService.reservaAsientos(requestReservaTicket.getProductBus(),fechaFormateada));
			
			//aqui busca si hay alguno con error
			ArrayList<ResponseBus> productosBusError = (ArrayList<ResponseBus>) tratarDatos.getProductosBus().stream().filter(pb -> pb.getSuccess().equals("false")).collect(Collectors.toList());
			
			if (!productosBusError.isEmpty()) {
				successBus = false;
				estado.put("error", "Error al reservar autobus");
			}else {successBus = true;}
		}

		// tiene entrada elegida
		if (requestReservaTicket.getProductspuydufou()!= null) {
			tratarDatos.setProductosPuydufou(expectTicketService.reservaProducto(fechaFormateada, requestReservaTicket.getProductspuydufou()));
			if (tratarDatos.getProductosPuydufou().getSuccess().equals("true")) {
				successParque = true;
			}
		}

		if (successParque && successBus) {
			
			double precioSumaParque= 0;
			double precioSumaAutobus=0;
			if (tratarDatos.getProductosPuydufou() != null) {				
				precioSumaParque= tratarDatos.getProductosPuydufou().getTotalRetailPrice();
			}
			if (tratarDatos.getProductosBus() != null) {				
				precioSumaAutobus= tratarDatos.getProductosBus().stream().mapToDouble(o -> o.getRetailPrice() * o.getQuantity()).sum();
			}
			tratarDatos.setFinalPrice(precioSumaParque + precioSumaAutobus);
			guardarReserva(tratarDatos, partner, epoch, fechaFormateada, "RESERVADO", fechaoperacion,requestReservaTicket.getMail());
			tratarDatos.setRequestId(ReservaRepository.findIdReserva(partner, epoch));
			success = true;
		} else {
			if (!tratarDatos.getProductosPuydufou().getErrorMessage().isBlank()) {
				estado.put("error", tratarDatos.getProductosPuydufou().getErrorMessage());
				estado.put("error", "En la API de terceros");
			}
			
			success = false;
		}


		tratarDatos.setEstado(estado);
		tratarDatos.setSuccess(success);
		log.info(tratarDatos.toString());

		return tratarDatos;
	}

	private void guardarReserva(ResponseReservaTicket responseReservaTicket, String partner, long epoch,
			String formatoFecha, String estado, String fechaoperacion, String mail) {

		log.info("ResponseReservaTicket | guardarReserva -> " + responseReservaTicket.toString());
		int id_reserva = 0;

		if (responseReservaTicket.getProductosPuydufou() != null) {
			ReservaRepository.guardarReserva(formatoFecha, responseReservaTicket.getProductosPuydufou().getReservationId(),
					partner, epoch, estado, fechaoperacion, mail,responseReservaTicket.getFinalPrice());
			id_reserva = ReservaRepository.findIdReserva(partner, epoch);
						
			reservaPuidefuRepository.guardarReserva(responseReservaTicket.getProductosPuydufou().getReservationId(), epoch,estado, formatoFecha);
			
			// añade todos los productos seleccionados del parque
			responseReservaTicket.getProductosPuydufou().getProducts().stream().forEach(p -> {
				for (int i = 0; i < p.getQuantity(); i++) {
					productosReservationidRepository.guardarProductosReservationid(
							responseReservaTicket.getProductosPuydufou().getReservationId(), p.getProductId());
				}
			});
		}else {
			ReservaRepository.guardarReserva(formatoFecha, null,
					partner, epoch, estado, fechaoperacion, mail,responseReservaTicket.getFinalPrice());
			id_reserva = ReservaRepository.findIdReserva(partner, epoch);
		}
		// añade todos los productos seleccionados del autobus
		if (responseReservaTicket.getProductosBus() != null) {
			HashMap<Integer, Integer> reservasFinal = new HashMap<Integer, Integer>();
			for(ResponseBus pb :responseReservaTicket.getProductosBus()) {
				autobusService.guardarReservaPlaza(id_reserva, pb);
				if (reservasFinal.get(pb.getScheduleIda()) == null ) {
				
					reservasFinal.put(pb.getScheduleIda(),pb.getQuantity());
				}else {
					reservasFinal.put(pb.getScheduleIda(),reservasFinal.get(pb.getScheduleIda()) + pb.getQuantity());
				}
				
				if (reservasFinal.get(pb.getScheduleVuelta()) == null ) {
					
					reservasFinal.put(pb.getScheduleVuelta(),pb.getQuantity());
				}else {
					reservasFinal.put(pb.getScheduleVuelta(),reservasFinal.get(pb.getScheduleVuelta()) + pb.getQuantity());
				}
	
			}
	
			autobusService.guardarReservaHorarioAutobus(reservasFinal,"SUMARESERVA");

		}
	}

}
