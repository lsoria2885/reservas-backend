package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.Room;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IRoomRepo;
import com.mitocode.service.IRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends CRUDImpl<Room, Integer> implements IRoomService {

	// @Autowired
	private final IRoomRepo repo;

	@Override
	protected IGenericRepo<Room, Integer> getRepo() {
		return repo;
	}
}