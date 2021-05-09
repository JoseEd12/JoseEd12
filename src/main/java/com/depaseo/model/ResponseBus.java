package com.depaseo.model;


import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBus {

	private String codeProductRoute;
	private Integer quantity;
	private Integer scheduleIda;
	private Integer scheduleVuelta;
	private String origen;
	private int retailPrice;
	private Map<String, String> state;
	private String success;

}
