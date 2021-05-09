package com.depaseo.call.model.transaction;



import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesDocumentSettings {
	
	@JsonProperty("ShowPrice")
	private String showPrice;
	
}
