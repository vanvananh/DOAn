package com.ptit.service;

import java.util.List;

import com.ptit.entity.Province;

public interface ProvinceService {
	public List<Province> getAllProvice();
	
	public Province getById(int id);

}
