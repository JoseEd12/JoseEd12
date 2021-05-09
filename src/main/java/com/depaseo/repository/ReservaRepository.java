package com.depaseo.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depaseo.entity.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
	@Modifying
	@Transactional
	@Query(value ="insert into reserva (fecha,nombre,apellidos,documento_identidad,partner,epoch,estado) values (:fecha,:nombre,:apellidos,:documento_identidad,:partner,:epoch,:estado)", nativeQuery = true)
	int guardarReserva(@Param("fecha") String fecha,@Param("nombre") String nombre,@Param("apellidos") String apellidos,@Param("documento_identidad") String documento_identidad,@Param("partner") String partner,@Param("epoch") long epoch,@Param("estado") String estado);

	
	@Modifying
	@Transactional
	@Query(value ="insert into reserva (fecha,reservation_id,partner,epoch,estado,fecha_operacion,mail,total_price) values (:fecha,:reservation_id,:partner,:epoch,:estado,:fecha_operacion,:mail,:total_price)", nativeQuery = true)
	int guardarReserva(@Param("fecha") String fecha,@Param("reservation_id") String reservation_id,@Param("partner") String partner,@Param("epoch") long epoch,@Param("estado") String estado,@Param("fecha_operacion") String fecha_operacion,@Param("mail") String mail,@Param("total_price") double total_price);

	
	@Query(value = "SELECT id_reserva from reserva where epoch = :epoch and partner = :partner", nativeQuery = true)
	Integer findIdReserva(@Param("partner") String partner,@Param("epoch") long epoch);
	
	
	@Query(value = "SELECT * from reserva where id_reserva = :id_reserva", nativeQuery = true)
	Reserva findId(@Param("id_reserva") int id_reserva);
	
	@Query(value = "SELECT * from reserva where reservation_id = :reservation_id", nativeQuery = true)
	Reserva findByReservationId(@Param("reservation_id") String reservation_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE reserva SET estado =:estado WHERE id_reserva like :id_reserva", nativeQuery = true)
	int actualizarReserva(@Param("estado") String  estado,@Param("id_reserva") int  id_reserva);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE reserva SET estado =:estado WHERE reservation_id like :reservation_id", nativeQuery = true)
	int actualizarReservaByReservationId(@Param("estado") String  estado,@Param("reservation_id") String  id_reserva);
}
