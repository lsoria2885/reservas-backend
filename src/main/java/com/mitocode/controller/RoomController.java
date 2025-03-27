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

import com.mitocode.dto.RoomDTO;
import com.mitocode.model.Room;
import com.mitocode.service.IRoomService;
import com.mitocode.util.MapperUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final IRoomService service;
	@Qualifier("defaultMapper")
	private final MapperUtil modelMapper;

	@GetMapping
	public ResponseEntity<List<RoomDTO>> findAll() throws Exception {
		List<RoomDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, RoomDTO.class)).toList();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoomDTO> findById(@PathVariable("id") Integer id) throws Exception {
		Room obj = service.findById(id);
		return ResponseEntity.ok(modelMapper.map(obj, RoomDTO.class));
	}

	/**
	 * Servicio para obtener solo las habitaciones disponibles para realizar una
	 * reserva
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/available")
	public ResponseEntity<List<RoomDTO>> findAvailableRooms() throws Exception {
		List<RoomDTO> list = service.findAll().stream().filter(room -> Boolean.TRUE.equals(room.isAvailable()))
				.map(e -> modelMapper.map(e, RoomDTO.class)).toList();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<RoomDTO> save(@Valid @RequestBody RoomDTO dto) throws Exception {
		Room obj = service.save(modelMapper.map(dto, Room.class));
		return new ResponseEntity<>(modelMapper.map(obj, RoomDTO.class), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoomDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody RoomDTO dto)
			throws Exception {
		Room obj = service.update(id, modelMapper.map(dto, Room.class));
		return ResponseEntity.ok(modelMapper.map(obj, RoomDTO.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	private RoomDTO convertToDto(Room obj) {
		return modelMapper.map(obj, RoomDTO.class);
	}

	private Room convertToEntity(RoomDTO dto) {
		return modelMapper.map(dto, Room.class);
	}
}
