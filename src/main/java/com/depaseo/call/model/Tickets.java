package com.depaseo.call.model;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Tickets {
	
	@JsonProperty("TicketId")
	private String ticketId;
	
	@JsonProperty("AccessCode")
	private String accessCode;
	
	
	@JsonProperty("SessionId")
	private String sessionId;
	
	@JsonProperty("BillingCode")
	private String billingCode;
	
	@JsonProperty("AccessDateTime")
	private String accessDateTime;
	
	@JsonProperty("AccessEndDateTime")
	private String accessEndDateTime;
	
	@JsonProperty("SuggestedAccessDateMessage")
	private String SuggestedAccessDateMessage;
}
