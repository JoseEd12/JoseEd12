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
public class ReservaBus {

	@Id
	private Integer id;
	private Integer idReserva;
	private String codigoProductoRuta;
	private Integer plazas;
	private Integer idHorarioIda;
	private Integer idHorarioVuelta;
	private String origen;
	private String estado;
	private double precio;
	

	

	
	
	
	
}
