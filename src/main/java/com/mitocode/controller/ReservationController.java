package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ReservationDTO;
import com.mitocode.model.Reservation;
import com.mitocode.service.IReservationService;
import com.mitocode.util.MapperUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final IReservationService service;
	@Qualifier("defaultMapper")
	private final MapperUtil modelMapper;

	@GetMapping
	public ResponseEntity<List<ReservationDTO>> findAll() throws Exception {
		List<ReservationDTO> list = service.findAll().stream().map(this::convertToDto).toList();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReservationDTO> findById(@PathVariable("id") Integer id) throws Exception {
		Reservation obj = service.findById(id);
		return ResponseEntity.ok(convertToDto(obj));
	}

	@PostMapping
	public ResponseEntity<ReservationDTO> save(@Valid @RequestBody ReservationDTO dto) throws Exception {
		Reservation obj = service.save(convertToEntity(dto));
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReservationDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ReservationDTO dto)
			throws Exception {
		Reservation obj = service.update(id, convertToEntity(dto));
		return ResponseEntity.ok(convertToDto(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Servicio para crear una nueva reserva con sus validaciones de habitaciones y
	 * fechas disponibles
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO dto) {
		Reservation obj = service.createReservation(convertToEntity(dto));
		return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
	}

	private ReservationDTO convertToDto(Reservation obj) {
		return modelMapper.map(obj, ReservationDTO.class);
	}

	private Reservation convertToEntity(ReservationDTO dto) {
		return modelMapper.map(dto, Reservation.class);
	}

}
