package com.ptit.service;

import java.util.List;

import com.ptit.entity.Location;



public interface LocationService {

	public List<Location> getAllWard();
	
	public Location getById(int id);
	
	public List<Location> getPopu();
	
}
