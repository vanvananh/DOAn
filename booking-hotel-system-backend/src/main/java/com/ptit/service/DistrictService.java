package com.ptit.service;

import java.util.List;

import com.ptit.entity.District;

public interface DistrictService {

	public List<District> getAllDistrict();
	
	public District getById(int id);
	
}
