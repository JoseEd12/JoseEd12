package com.depaseo.entity;



import java.util.Date;

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
public class ReservaPuydefu {

	@Id
	private String ReservationId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String fecha;
	private long epoch;
	private String estado;
	private String TransactionId; 
	private Date TransactionDateTime;
	private String SalesDocumentUrl;
}
