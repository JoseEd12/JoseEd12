package com.depaseo.entity;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Reserva {

	@Id
	private Integer idReserva;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String fecha;
	private String nombre;
	private String apellidos;
	private String mail;
	private String fechaOperacion;
	private String documentoIdentidad;
	private String partner;
	private long epoch;
	private String estado;
	private String ReservationId;
	private double totalPrice;
	
	
	
}
