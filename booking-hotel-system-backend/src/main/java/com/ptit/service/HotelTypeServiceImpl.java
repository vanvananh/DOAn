package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.entity.HotelType;
import com.ptit.repository.HotelTypeRepository;

@Service
public class HotelTypeServiceImpl implements HotelTypeService{
	
	@Autowired
	private HotelTypeRepository hotelTypeService;

	@Override
	public List<HotelType> getAllHotelType() {
		// TODO Auto-generated method stub
		return hotelTypeService.findAll();
	}

}
