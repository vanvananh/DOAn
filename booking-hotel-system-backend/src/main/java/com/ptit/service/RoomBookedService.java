package com.ptit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ptit.entity.RoomBooked;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterBooking;

public interface RoomBookedService {
	
	public RoomBooked createBooking(RoomBooked booking);
	
	public RoomBooked getById(int id);
	
	public boolean isExists(int id);
	
	public List<RoomBooked> getBookingByCustomerId(int id);
	
	public Page<RoomBooked> getAllRoomBooked(Paging paging, Sorting sorting, FilterBooking filter);
	
}
