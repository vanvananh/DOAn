package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.entity.RoomType;
import com.ptit.repository.RoomTypeRepository;

@Service
public class RoomTypeServiceImpl implements RoomTypeService{

	@Autowired
	private RoomTypeRepository roomTypeRepository;
	
	
	@Override
	public List<RoomType> getAllRoomType() {
		
		return roomTypeRepository.findAll();
	}


	@Override
	public boolean isExists(int id) {
		// TODO Auto-generated method stub
		return roomTypeRepository.exists(id);
	}

	
	
}
