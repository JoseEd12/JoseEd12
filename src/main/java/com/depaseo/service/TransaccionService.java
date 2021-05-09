package com.depaseo.service;

import com.depaseo.model.RequestTransactionTicket;

import com.depaseo.model.ResponseTransactionTicket;


public interface TransaccionService {

	ResponseTransactionTicket gestionTransaction(RequestTransactionTicket requestTransactionTicket, String partnet);

}
