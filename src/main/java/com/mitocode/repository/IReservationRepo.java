package com.mitocode.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Reservation;
import com.mitocode.model.Room;

public interface IReservationRepo extends IGenericRepo<Reservation, Integer> {

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " + "FROM Reservation r WHERE r.room = :room "
			+ "AND (r.checkInDate < :checkOutDate AND r.checkOutDate > :checkInDate)")
	boolean existsByRoomAndDateRange(@Param("room") Room room, @Param("checkInDate") LocalDateTime checkInDate,
			@Param("checkOutDate") LocalDateTime checkOutDate);

}
