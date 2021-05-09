package com.depaseo.repository;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depaseo.entity.ReservaBus;
import com.depaseo.model.ResponseBus;


@Repository
public interface ReservabusRepository extends JpaRepository<ReservaBus, Integer>{
	
	@Modifying
	@Transactional
	@Query(value ="insert into reserva_bus (id_reserva,estado,codigo_producto_ruta,origen,id_horario_ida,id_horario_vuelta,plazas,precio) values (:id_reserva,:estado,:codigo_producto_ruta,:origen,:id_horario_ida,:id_horario_vuelta,:plazas,:precio)", nativeQuery = true)
	int guardarReservabus(@Param("id_reserva") int id_reserva,@Param("estado") String estado,@Param("codigo_producto_ruta") String codigo_producto_ruta,@Param("origen") String origen,@Param("id_horario_ida") int id_horario_ida,@Param("id_horario_vuelta") int id_horario_vuelta,@Param("plazas") int plazas,@Param("precio") double precio);
	
	@Query(value ="SELECT DISTINCT id_reserva FROM reserva_bus where estado like 'RESERVADO'", nativeQuery = true)
	List<Integer> findReservadosParaCancelar();
	
	@Query(value ="SELECT * FROM reserva_bus where id_reserva like :id_reserva", nativeQuery = true)
	List<ReservaBus> findParaCancelar(@Param("id_reserva") int id_reserva);
	
	@Query(value ="SELECT * FROM reserva_bus where id_reserva like :id_reserva", nativeQuery = true)
	List<ReservaBus> findParaConfirmar(@Param("id_reserva") int id_reserva);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE reserva_bus SET estado =:estado WHERE id_reserva like :id_reserva", nativeQuery = true)
	int actualizarReservaBus(@Param("estado") String  estado,@Param("id_reserva") int  id_reserva);
}
