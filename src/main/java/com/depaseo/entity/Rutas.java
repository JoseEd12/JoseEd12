package com.depaseo.entity;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Rutas {

	@Id
	private String codigoRuta;
	private String idaSalida;
	private String idaParada;
	private String idaLlegada;
	private String vueltaSalida;
	private String vueltaParada;
	private String vueltaLlegada;
	private String tipo;
	private String descripcion;
	private String puntoSalidaIda;
	private String puntoLlegadaIda;
	private String puntoParadaIda;
	private String puntoSalidaVuelta;
	private String puntoLlegadaVuelta;
	private String puntoParadaVuelta;
	private String horarioIda;
	private String horarioVuelta;
	private String estado;
	
	

	
	
	
	
}
