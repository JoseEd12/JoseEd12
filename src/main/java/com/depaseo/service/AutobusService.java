package com.depaseo.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.depaseo.model.Productosbus;
import com.depaseo.model.Products;
import com.depaseo.model.ResponseBus;


public interface AutobusService {


	void guardarReservaPlaza(int id_reserva,ResponseBus responseBus);

	 ArrayList<ResponseBus> reservaAsientos(Productosbus productBus, String fecha);

	

	void actualizarReservasBus(String string, Integer idReserva);

	void guardarReservaHorarioAutobus(HashMap<Integer, Integer> reservasFinal, String operador);

	List<Products> plazasLibres(String fecha);

}
