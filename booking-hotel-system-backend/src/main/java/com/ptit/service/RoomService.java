package com.ptit.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ptit.dto.RoomDTO;
import com.ptit.entity.Hotel;
import com.ptit.entity.Room;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterHotel;
import com.ptit.util.filter.FilterRoom;

public interface RoomService {

	public RoomDTO getRoomById(int id);
	
	public boolean isExist(int id);
	
	public List<RoomDTO> getRoomByHotel(int hotelId, int roomTypeId, Date startDate, Date endDate);
	
	public List<Room> checkEmpty(int id, Date startDate, Date endDate);
	
	public Page<Room>  getListRoom(Paging paging, Sorting sorting, Integer hotelId, String keywordSearch, FilterRoom filter);
	
}
