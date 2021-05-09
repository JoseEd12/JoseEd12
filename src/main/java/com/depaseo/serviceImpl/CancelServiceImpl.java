package com.depaseo.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaseo.call.model.cancel.ResponseCancelEntity;
import com.depaseo.entity.Reserva;
import com.depaseo.model.RequestTransactionTicket;
import com.depaseo.model.ResponseCancelTicket;
import com.depaseo.repository.ReservaRepository;
import com.depaseo.service.CancelService;
import com.depaseo.service.ExpectTicketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CancelServiceImpl implements CancelService {

	@Autowired
	ExpectTicketService expectTicketService;
	@Autowired
	ReservaRepository reservaRepository;

	@Override
	public ResponseCancelTicket cancelarReserva(RequestTransactionTicket requestcancelTicket, String partner) {

		log.info("CancelServiceImpl | cancelarReserva -> resquest " + requestcancelTicket.toString());
		ResponseCancelTicket respuesta = new ResponseCancelTicket();
		boolean success = false;
		Map<String, String> estado = new HashMap<String, String>();
		String fechaFormateada = new SimpleDateFormat("yyyy-MM-dd").format(requestcancelTicket.getFecha());

		Reserva reserva = reservaRepository.findId(requestcancelTicket.getRequestId());
		ResponseCancelEntity cancelEntity =expectTicketService.CancelProducto(fechaFormateada, reserva.getReservationId());
		
		if (cancelEntity.getSuccess().equalsIgnoreCase("true")) {
			success = true;
			estado.put("Cancelacion","Se ha cancelado de manera exitosa");
			respuesta.setEstado(estado);
			reservaRepository.actualizarReserva("CANCELADO", requestcancelTicket.getRequestId());
		}else {
			estado.put("error",cancelEntity.getErrorMessage());
			respuesta.setEstado(estado);
			success = false;
		}
		
		respuesta.setRequestId(requestcancelTicket.getRequestId());
		respuesta.setSuccess(success);
		log.info("CancelServiceImpl | cancelarReserva -> response " + respuesta.toString());

		return respuesta;
	}
}
