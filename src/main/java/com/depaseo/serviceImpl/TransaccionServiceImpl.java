package com.depaseo.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaseo.MyProperties;
import com.depaseo.entity.Reserva;
import com.depaseo.entity.ReservaBus;
import com.depaseo.model.RequestTransactionTicket;
import com.depaseo.model.ResponseBus;
import com.depaseo.model.ResponseTransactionTicket;
import com.depaseo.repository.ReservaPuidefuRepository;
import com.depaseo.repository.ReservaRepository;
import com.depaseo.repository.ReservabusRepository;
import com.depaseo.service.AutobusService;
import com.depaseo.service.ExpectTicketService;
import com.depaseo.service.TransaccionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	ExpectTicketService expectTicketService;
	@Autowired
	ReservaPuidefuRepository reservaPuidefuRepository;
	@Autowired
	ReservaRepository ReservaRepository;
	@Autowired
	MyProperties properties;
	@Autowired
	AutobusService autobusService;
	@Autowired
	ReservabusRepository reservabusRepository;

	@Override
	public ResponseTransactionTicket gestionTransaction(RequestTransactionTicket requestTransactionTicket, String partnet) {
		ResponseTransactionTicket respuesta = new ResponseTransactionTicket();
		Map<String, String> estado = new HashMap<String, String>();
		boolean success=false;
		boolean successtransaccion=true;
		String fechaFormateada = new SimpleDateFormat("yyyy-MM-dd").format(requestTransactionTicket.getFecha());
		
		respuesta.setResquestId(requestTransactionTicket.getRequestId());
		

		try {
			Reserva reserva = ReservaRepository.findId(requestTransactionTicket.getRequestId());
			if (reserva.getReservationId()!= null) {
			respuesta.setResponseTransactionEntity(expectTicketService.transactionProducto(fechaFormateada,reserva.getReservationId()));
				if (!respuesta.getResponseTransactionEntity().getSuccess().equals("true")) {
					successtransaccion=false;
				}
			}
			List<ReservaBus> productosBus = reservabusRepository.findParaConfirmar(reserva.getIdReserva());
			ArrayList<ResponseBus> respuestabus = new ArrayList<ResponseBus>();
			productosBus.stream().forEach(pb -> {
				ResponseBus responsebus = new ResponseBus();
				responsebus.setCodeProductRoute(pb.getCodigoProductoRuta());
				responsebus.setSuccess(pb.getEstado());
				responsebus.setOrigen(pb.getOrigen());
				responsebus.setScheduleIda(pb.getIdHorarioIda());
				responsebus.setScheduleVuelta(pb.getIdHorarioVuelta());
				responsebus.setQuantity(pb.getPlazas());
				respuestabus.add(responsebus);
			});
			respuesta.setProductosBus(respuestabus);

			if (reserva.getEstado().equalsIgnoreCase("RESERVADO")) {
					if (successtransaccion){
					
						if (partnet.equals(reserva.getPartner())) {
							log.info("reserva es: " + reserva.toString());
							guardarTransaccion(respuesta,reserva);
							estado.put("Transaccion", "Compra realizada con Exito");
							
							if (reserva.getMail() != null && reserva.getReservationId()!= null) {
							
								 Process process = null;
								 String urldoc = respuesta.getResponseTransactionEntity().getDocuments().get(0).getSalesDocumentUrl();
						         try
						         { 
						           process = Runtime.getRuntime().exec(properties.scriptmail+ " " + urldoc + " " + reserva.getMail()); // for Linux
						         process.waitFor();
						         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
						         String line;
						            while ((line=reader.readLine())!=null)
						            {
						             log.info(line);   
						             }
						          }       
						             catch(Exception e)
						          { 
						              log.error(e.toString()); 
						          }
						          finally
						          {
						           // process.destroy();
						          } 
							}else {
								log.info("No tenemos el mail del cliente");
							}
							//log.info("Envio de mail al cliente: " + expectTicketService.sendMail(respuesta.getResponseTransactionEntity().getTransactionId(), reserva.getMail()));
							
							success=true;
						}else {
							estado.put("error", "No te pertece esa transaccion");
							success=false;
						}
					}else {
						estado.put("error", respuesta.getResponseTransactionEntity().getErrorMessage());
						estado.put("error", "En la API de terceros");
						success=false;
					}
			}else {
				estado.put("error", "Ha expirado el tiempo de reserva (Max 10m)");
				success=false;
			}
		}catch(NullPointerException nulo) {
			estado.put("error", "El numero introducido no es correcto");
			log.info("El error es;"+nulo.toString());
		}
		respuesta.setEstado(estado);
		respuesta.setSuccess(success);
		log.info("TransaccionServiceImpl | gestionTransaction - respuesta" + respuesta.toString());
		return respuesta;
	}

	private void guardarTransaccion(ResponseTransactionTicket respuestaPuydefu, Reserva reserva) {
		if(respuestaPuydefu.getResponseTransactionEntity() != null ) {
		reservaPuidefuRepository.actualizarReserva("CONFIRMADO",
				respuestaPuydefu.getResponseTransactionEntity().getTransactionId(),
				respuestaPuydefu.getResponseTransactionEntity().getTransactionDateTime(),
				respuestaPuydefu.getResponseTransactionEntity().getDocuments().get(0).getSalesDocumentUrl(),
				reserva.getReservationId());
		}
		
		ReservaRepository.actualizarReserva("CONFIRMADO", respuestaPuydefu.getResquestId());
		if (respuestaPuydefu.getProductosBus() != null) {
			autobusService.actualizarReservasBus("CONFIRMADO",reserva.getIdReserva());
			HashMap<Integer, Integer> reservasFinal = new HashMap<Integer, Integer>();
			
			for(ResponseBus pb :respuestaPuydefu.getProductosBus()) {
				
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
	
			autobusService.guardarReservaHorarioAutobus(reservasFinal,"RESTARESERVA");
			autobusService.guardarReservaHorarioAutobus(reservasFinal,"SUMARCONFIMADA");
		}
	}
}
