package com.mitocode.service;

import com.mitocode.dto.ReservationDTO;
import com.mitocode.model.Reservation;

public interface IReservationService extends ICRUD<Reservation, Integer> {

	public Reservation createReservation(Reservation dto);

}
