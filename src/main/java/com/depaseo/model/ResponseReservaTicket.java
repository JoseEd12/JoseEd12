package com.depaseo.model;


import java.util.ArrayList;
import java.util.Map;

import com.depaseo.call.model.reserva.ResponseReservaEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseReservaTicket {
	private int requestId;
	private ResponseReservaEntity productosPuydufou;
	private ArrayList<ResponseBus> productosBus;
	private double finalPrice;
	private Map<String, String> estado;
	private boolean success;
}
