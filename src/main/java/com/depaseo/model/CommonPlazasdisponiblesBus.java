package com.depaseo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CommonPlazasdisponiblesBus {

	private String fecha;
	private String horario;
	private int plazas_libres;
	
	public CommonPlazasdisponiblesBus(String fecha, String horario, int plazas_libres) {
		this.fecha = fecha;
		this.horario = horario;
		this.plazas_libres = plazas_libres;
	}
	
	
}
