package com.ptit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ptit.entity.RoomType;

@Service
public interface RoomTypeService {
	public List<RoomType> getAllRoomType();
	
	public boolean isExists(int id);
}
