package com.depaseo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depaseo.entity.HorarioAutobus;
import com.depaseo.model.CommonPlazasdisponiblesBus;



@Repository
public interface HorarioAutobusRepository extends JpaRepository<HorarioAutobus, Integer> {

	@Query(value = "SELECT * from horario_autobus where fecha = :fecha and horario = :horario", nativeQuery = true)
	HorarioAutobus findByFechaAndHorario(@Param("fecha") String fecha,@Param("horario") String horario);
	
	@Query(value = "SELECT * from horario_autobus WHERE id = :id", nativeQuery = true)
	HorarioAutobus findporID(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE horario_autobus SET plazas_reservadas = plazas_reservadas + :plazas_reservadas WHERE id = :id", nativeQuery = true)
	int actualizarPlazasReservadasSuma(@Param("id") int id,@Param("plazas_reservadas") int plazas_reservadas);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE horario_autobus SET plazas_reservadas = (plazas_reservadas - :plazas_reservadas) WHERE id = :id", nativeQuery = true)
	int actualizarPlazasReservadasResta(@Param("id") int id,@Param("plazas_reservadas") int plazas_reservadas);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE horario_autobus SET plazas_ocupadas = plazas_ocupadas + :plazas_ocupadas WHERE id = :id", nativeQuery = true)
	int actualizarPlazasConfirmadasSuma(@Param("id") int id,@Param("plazas_ocupadas") int plazas_ocupadas);
	
	@Query(value = "SELECT fecha,horario, plazas_totales - (plazas_ocupadas + plazas_reservadas) as plazas_libres from horario_autobus where fecha = :fecha ", nativeQuery = true)
	List<Object[]> findPlazasDisponiblesByFecha(@Param("fecha") String fecha);
}
