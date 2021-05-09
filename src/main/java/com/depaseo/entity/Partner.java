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
public class Partner {

	@Id
	private Integer idpartner;	
	private String nombre;
	private String username;
	private String password;
	private String authority;
	private boolean enabled;
	
}
