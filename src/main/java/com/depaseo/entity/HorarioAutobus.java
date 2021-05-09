package com.depaseo.entity;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table()
public class HorarioAutobus {

	@Id
	private Integer id;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private String fecha;
	private String horario;
	private int plazasTotales;
	private int plazasOcupadas;
	private int plazasReservadas;
	
	
	
}
