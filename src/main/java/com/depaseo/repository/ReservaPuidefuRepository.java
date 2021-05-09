package com.depaseo.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depaseo.entity.ReservaPuydefu;


@Repository
public interface ReservaPuidefuRepository extends JpaRepository<ReservaPuydefu, String>{
	
	@Modifying
	@Transactional
	@Query(value ="insert into reserva_puydefu (reservation_id,epoch,estado,fecha) values (:reservation_id,:epoch,:estado,:fecha)", nativeQuery = true)
	int guardarReserva(@Param("reservation_id") String reservation_id,@Param("epoch") long epoch,@Param("estado") String estado,@Param("fecha") String fecha);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE reserva_puydefu SET estado =:estado, transaction_id = :transaction_id,transaction_date_time =:transaction_date_time,sales_document_url = :sales_document_url WHERE reservation_id like :reservation_id", nativeQuery = true)
	int actualizarReserva(@Param("estado") String  estado,@Param("transaction_id") String  transaction_id, @Param("transaction_date_time") String transaction_date_time,@Param("sales_document_url") String sales_document_url,@Param("reservation_id") String reservation_id);
	
}
