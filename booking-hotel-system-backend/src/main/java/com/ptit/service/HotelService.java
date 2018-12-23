package com.ptit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ptit.dto.HotelDTO;
import com.ptit.dto.HotelSearchDTO;

import com.ptit.entity.Hotel;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterHotel;


public interface HotelService {
	public boolean isExists(int id);
	
	public HotelDTO getHotelById(int id);
	
	public List<Hotel> getHotelBySearch(HotelSearchDTO hotelSearchDto);
	
	public Page<Hotel> getHotelByStaff(Paging paging, Sorting sorting, Integer staffId, String keywordSearch, FilterHotel filter);

}
