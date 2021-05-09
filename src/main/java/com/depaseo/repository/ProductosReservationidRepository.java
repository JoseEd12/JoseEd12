package com.depaseo.repository;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depaseo.entity.ProductosReservationid;


@Repository
public interface ProductosReservationidRepository extends JpaRepository<ProductosReservationid, String>{
	
	@Modifying
	@Transactional
	@Query(value ="insert into productos_reservationid (reservation_id,product_id) values (:reservation_id,:product_id)", nativeQuery = true)
	int guardarProductosReservationid(@Param("reservation_id") String reservation_id,@Param("product_id") String product_id);


	@Query(value = "SELECT * from productos_reservationid where reservation_id = :reservation_id", nativeQuery = true)
	List<ProductosReservationid> findProducByReservationID(@Param("reservation_id") String reservation_id);

}
