package com.depaseo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.depaseo.entity.Tarifa;



public interface TarifaRepository extends JpaRepository<Tarifa, Integer>{
	
	@Query(value = "SELECT precio from tarifa where codigo_ruta = :codigo_ruta and origen = :origen", nativeQuery = true)
	int findByPrice(@Param("codigo_ruta") String codigo_ruta,@Param("origen") String origen);
}
