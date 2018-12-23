package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.entity.Location;
import com.ptit.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService{
	
	@Autowired
	private LocationRepository locationService;

	@Override
	public List<Location> getAllWard() {
		// TODO Auto-generated method stub
		return locationService.findAll();
	}

	@Override
	public Location getById(int id) {
		// TODO Auto-generated method stub
		return locationService.findOne(id);
	}

	@Override
	public List<Location>  getPopu() {
		// TODO Auto-generated method stub
		return locationService.getPopu();
	}

}
