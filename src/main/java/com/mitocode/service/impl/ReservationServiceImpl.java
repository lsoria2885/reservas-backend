package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.Reservation;
import com.mitocode.model.Room;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IReservationRepo;
import com.mitocode.repository.IRoomRepo;
import com.mitocode.service.IReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl extends CRUDImpl<Reservation, Integer> implements IReservationService {

	// @Autowired
	private final IReservationRepo repo;
	private final IRoomRepo roomRepo;

	@Override
	protected IGenericRepo<Reservation, Integer> getRepo() {
		return repo;
	}

	/**
	 * Metodo para validar creacion de reservas
	 */
	public Reservation createReservation(Reservation dto) {
		if (dto.getCheckOutDate().isBefore(dto.getCheckInDate())) {
			throw new IllegalArgumentException("Check-out date cannot be before check-in date.");
		}
		Room room = roomRepo.findById(dto.getRoom().getIdRoom())
				.orElseThrow(() -> new IllegalArgumentException("Room not found."));

		if (!room.isAvailable()) {
			throw new IllegalArgumentException("Room is not available.");
		}
		boolean hasConflict = repo.existsByRoomAndDateRange(room, dto.getCheckInDate(), dto.getCheckOutDate());

		if (hasConflict) {
			throw new IllegalArgumentException("Room is already booked for the selected dates.");
		}

		Reservation reservation = new Reservation();
		reservation.setCustomerName(dto.getCustomerName());
		reservation.setCheckInDate(dto.getCheckInDate());
		reservation.setCheckOutDate(dto.getCheckOutDate());
		reservation.setRoom(room);
		Reservation savedReservation = repo.save(reservation);
		room.setAvailable(false);
		roomRepo.save(room);
		return savedReservation;
	}
}