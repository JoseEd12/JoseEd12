package com.depaseo.model;


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
public class ResponseCancelTicket {
	private int requestId;
	//private RequestTransactionTicket requestTransactionTicket;
	private ResponseTransactionEntity responseCancelEntity;
	private Map<String, String> estado;
	private boolean success;
}
