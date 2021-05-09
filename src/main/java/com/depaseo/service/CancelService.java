package com.depaseo.service;

import com.depaseo.model.RequestTransactionTicket;
import com.depaseo.model.ResponseCancelTicket;


public interface CancelService {

	ResponseCancelTicket cancelarReserva(RequestTransactionTicket requestcancelTicket, String partner);

}
