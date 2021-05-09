package com.depaseo.model;


import java.util.ArrayList;
import java.util.Map;

import com.depaseo.call.model.transaction.ResponseTransactionEntity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTransactionTicket {
	private int resquestId;	
	private ResponseTransactionEntity responseTransactionEntity;
	private ArrayList<ResponseBus> productosBus;
	private Map<String, String> estado;
	private boolean success;
}
