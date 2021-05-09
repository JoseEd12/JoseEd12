package com.depaseo.service;

import com.depaseo.model.ResponseReservaTicket;
import com.depaseo.model.RequestReservaTicket;

public interface ReservasService {

	

	ResponseReservaTicket gestionReserva(RequestReservaTicket requestReservaTicket, String partnet);

}
