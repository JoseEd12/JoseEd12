package com.depaseo.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaseo.entity.HorarioAutobus;
import com.depaseo.entity.Rutas;
import com.depaseo.model.CommonPlazasdisponiblesBus;
import com.depaseo.model.Productosbus;
import com.depaseo.model.Products;
import com.depaseo.model.ResponseBus;
import com.depaseo.repository.HorarioAutobusRepository;
import com.depaseo.repository.ReservabusRepository;
import com.depaseo.repository.RutasRepository;
import com.depaseo.repository.TarifaRepository;
import com.depaseo.service.AutobusService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AutobusServiceImpl implements AutobusService {

	@Autowired
	RutasRepository rutasRepository;
	@Autowired
	HorarioAutobusRepository horarioAutobusRepository;
	@Autowired
	ReservabusRepository reservabusRepository;
	@Autowired
	TarifaRepository tarifaRepository;

	@Override
	public ArrayList<ResponseBus> reservaAsientos(Productosbus productBus, String fecha) {
		ArrayList<ResponseBus> reservado = new ArrayList<ResponseBus>();

		productBus.getProducts().stream().forEach(pb -> {
			ResponseBus respuestabus = new ResponseBus();
			Map<String, String> estado = new HashMap<String, String>();
			Rutas ruta = rutasRepository.findById(pb.getProductId()).orElse(null);
			if (ruta != null) {
				HorarioAutobus horarioIda = horarioAutobusRepository.findByFechaAndHorario(fecha, ruta.getHorarioIda());
				HorarioAutobus horarioVuelta = horarioAutobusRepository.findByFechaAndHorario(fecha,
						ruta.getHorarioVuelta());
				if (horarioIda != null && horarioVuelta != null) {
					if (horarioIda.getPlazasTotales() >= horarioIda.getPlazasOcupadas()
							+ horarioIda.getPlazasReservadas() + pb.getQuantity()
							&& horarioVuelta.getPlazasTotales() >= horarioVuelta.getPlazasOcupadas()
									+ horarioVuelta.getPlazasReservadas() + pb.getQuantity()) {
						int precio = tarifaRepository.findByPrice(pb.getProductId(), productBus.getOrigen());
						respuestabus.setRetailPrice(precio);
						respuestabus.setOrigen(productBus.getOrigen());
						respuestabus.setCodeProductRoute(pb.getProductId());
						respuestabus.setQuantity(pb.getQuantity());
						respuestabus.setScheduleIda(horarioIda.getId());
						respuestabus.setScheduleVuelta(horarioVuelta.getId());
						estado.put("success", "Hay plazas disponibles");
						respuestabus.setState(estado);
						respuestabus.setSuccess("true");
						log.info("respuesta bus uno a uno" + respuestabus.toString());
						reservado.add(respuestabus);
					} else {
						respuestabus.setOrigen(productBus.getOrigen());
						respuestabus.setCodeProductRoute(pb.getProductId());
						respuestabus.setQuantity(pb.getQuantity());
						estado.put("error", "No hay plazas disponibles");
						respuestabus.setState(estado);
						respuestabus.setSuccess("false");
						reservado.add(respuestabus);
						log.info("respuesta no hay plazas disponibles" + respuestabus.toString());
					}
				} else {
					respuestabus.setOrigen(productBus.getOrigen());
					respuestabus.setCodeProductRoute(pb.getProductId());
					respuestabus.setQuantity(pb.getQuantity());
					estado.put("error", "No hay ese horario disponible");
					respuestabus.setState(estado);
					respuestabus.setSuccess("false");
					reservado.add(respuestabus);
					log.info("respuesta no hay horario disponibles" + respuestabus.toString());
				}

			} else {
				respuestabus.setOrigen(productBus.getOrigen());
				respuestabus.setCodeProductRoute(pb.getProductId());
				respuestabus.setQuantity(pb.getQuantity());
				estado.put("error", "No hay ruta disponible para ese producto");
				respuestabus.setState(estado);
				respuestabus.setSuccess("false");
				reservado.add(respuestabus);
				log.info("respuesta no hay ruta disponibles" + respuestabus.toString());
			}
			log.info("array de bus dentro  " + reservado.toString());
		});
		log.info("array de bus " + reservado.toString());
		return reservado;
	}

	@Override
	public void guardarReservaPlaza(int id_reserva, ResponseBus responseBus) {

		reservabusRepository.guardarReservabus(id_reserva, "RESERVADO", responseBus.getCodeProductRoute(),
				responseBus.getOrigen(), responseBus.getScheduleIda(), responseBus.getScheduleVuelta(),
				responseBus.getQuantity(), responseBus.getRetailPrice());
	}


	@Override
	public void guardarReservaHorarioAutobus(HashMap<Integer, Integer> reservasFinal,String operador) {

		switch(operador) {
		  case "SUMARESERVA":
			  for (Integer i : reservasFinal.keySet()) {
					
					horarioAutobusRepository.actualizarPlazasReservadasSuma(i, reservasFinal.get(i));
				}
		    break;
		  case "RESTARESERVA":
			  for (Integer i : reservasFinal.keySet()) {
					//log.info("hasmap; " + i + " set ket " + reservasFinal.get(i));
					horarioAutobusRepository.actualizarPlazasReservadasResta(i, reservasFinal.get(i));
				}
		    break;
		  case "SUMARCONFIMADA":
			  for (Integer i : reservasFinal.keySet()) {
					//log.info("hasmap; " + i + " set ket " + reservasFinal.get(i));
					horarioAutobusRepository.actualizarPlazasConfirmadasSuma(i, reservasFinal.get(i));
				}
			    break;
		}
		

	}
	@Override
	public List<Products> plazasLibres(String fecha) {
		List<Products> products = new ArrayList<Products>();
		
		List<Object[]> Listabbdd = horarioAutobusRepository.findPlazasDisponiblesByFecha(fecha);
		List<CommonPlazasdisponiblesBus> ListaDisponible = Listabbdd.stream().map(lb -> new CommonPlazasdisponiblesBus((String) lb[0],(String) lb[1],((BigInteger) lb[2]).intValue())).collect(Collectors.toList());
		HashMap<String, Integer> plazasLibres = new HashMap<String, Integer>();
		 ListaDisponible.stream().forEach(l -> {
			 switch (l.getHorario()) {
			 case "mañana_ida":
				 	if (plazasLibres.get("puycom01") == null ) {						
				 		plazasLibres.put("puycom01",l.getPlazas_libres());
					}else {
						if (plazasLibres.get("puycom01")> l.getPlazas_libres()) {
							plazasLibres.put("puycom01",l.getPlazas_libres());
						}
					}
				 	
				 	if (plazasLibres.get("puydiur01") == null ) {
						
				 		plazasLibres.put("puydiur01",l.getPlazas_libres());
					}else {
						if (plazasLibres.get("puydiur01")> l.getPlazas_libres()) {
							plazasLibres.put("puydiur01",l.getPlazas_libres());
						}
					}
				 	break;
			 case "tarde_ida":
					if (plazasLibres.get("puyesp01") == null ) {						
						plazasLibres.put("puyesp01",l.getPlazas_libres());
					}else {
						if (plazasLibres.get("puyesp01")> l.getPlazas_libres()) {
							plazasLibres.put("puyesp01",l.getPlazas_libres());
						}
					}
				 	
				 	break;
			 case "tarde_vuelta":
				 	if (plazasLibres.get("puydiur01") == null ) {
						
				 		plazasLibres.put("puydiur01",l.getPlazas_libres());
					}else {
						if (plazasLibres.get("puydiur01")> l.getPlazas_libres()) {
							plazasLibres.put("puydiur01",l.getPlazas_libres());
						}
					}
				 
				 	break;
			 case "noche_vuelta":
			 	if (plazasLibres.get("puycom01") == null ) {						
			 		plazasLibres.put("puycom01",l.getPlazas_libres());
				}else {
					if (plazasLibres.get("puycom01")> l.getPlazas_libres()) {
						plazasLibres.put("puycom01",l.getPlazas_libres());
					}
				}
			 	
			 	if (plazasLibres.get("puyesp01") == null ) {
					
			 		plazasLibres.put("puyesp01",l.getPlazas_libres());
				}else {
					if (plazasLibres.get("puyesp01")> l.getPlazas_libres()) {
						plazasLibres.put("puyesp01",l.getPlazas_libres());
					}
				}
			 		break;
			 		
			 }
		 });
		 
			Products puycom01 = new Products("puycom01",plazasLibres.get("puycom01"));
			Products puydiur01 = new Products("puydiur01",plazasLibres.get("puydiur01"));
			Products puyesp01 = new Products("puyesp01",plazasLibres.get("puyesp01"));
			products.add(puycom01);
			products.add(puydiur01);
			products.add(puyesp01);
		return products;
	}
	@Override
	public void actualizarReservasBus(String estado, Integer idReserva) {
		reservabusRepository.actualizarReservaBus(estado, idReserva);
		
	}

}
