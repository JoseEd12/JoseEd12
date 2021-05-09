package com.depaseo.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.depaseo.entity.Reserva;
import com.depaseo.entity.ReservaBus;
import com.depaseo.repository.ReservaRepository;
import com.depaseo.repository.ReservabusRepository;
import com.depaseo.service.AutobusService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class TaskComponent {
	@Autowired
	ReservaRepository ReservaRepository;

	@Autowired
	ReservabusRepository reservabusRepository;
	@Autowired
	AutobusService autobusService;
	
	
	@Scheduled(cron ="0 * * * * ?")
	public void taskSaveWallet() {
		
		ArrayList<Integer> lista = (ArrayList<Integer>) reservabusRepository.findReservadosParaCancelar();
		long epoch = System.currentTimeMillis();
		lista.forEach(l ->{
			Reserva reserva = ReservaRepository.findId(l);
			if (epoch - reserva.getEpoch() > 600000) {
				log.info("llevan mas de 10 min reservado " + reserva.getEpoch() + " epoch de ahora " + epoch);
				reserva.setEstado("CANCELADO");
				HashMap<Integer, Integer> reservasFinal = new HashMap<Integer, Integer>();
				List<ReservaBus> reserbaBus = reservabusRepository.findParaCancelar(l);
				reserbaBus.forEach(rb -> {
					rb.setEstado("CANCELADO");
					
					
					
						if (reservasFinal.get(rb.getIdHorarioIda()) == null ) {
						
							reservasFinal.put(rb.getIdHorarioIda(),rb.getPlazas());
						}else {
							reservasFinal.put(rb.getIdHorarioIda(),reservasFinal.get(rb.getIdHorarioIda()) + rb.getPlazas());
						}
						
						if (reservasFinal.get(rb.getIdHorarioVuelta()) == null ) {
							
							reservasFinal.put(rb.getIdHorarioVuelta(),rb.getPlazas());
						}else {
							reservasFinal.put(rb.getIdHorarioVuelta(),reservasFinal.get(rb.getIdHorarioVuelta()) + rb.getPlazas());
						}
			
					
				});
				autobusService.guardarReservaHorarioAutobus(reservasFinal,"RESTARESERVA");
				reservabusRepository.saveAll(reserbaBus);
				ReservaRepository.save(reserva);
			}else 
			{
				log.info("llevan menos de 10 min reservado " + reserva.getEpoch() + " epoch de ahora " + epoch);
			}
		});
	}
	

}
