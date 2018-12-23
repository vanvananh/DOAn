package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.entity.District;
import com.ptit.repository.DistrictRepository;

@Service
public class DistrictServiceImpl implements DistrictService{

	@Autowired
	private DistrictRepository districtRe;
	
	
	@Override
	public List<District> getAllDistrict() {
		// TODO Auto-generated method stub
		return districtRe.findAll();
	}

	@Override
	public District getById(int id) {
		// TODO Auto-generated method stub
		return districtRe.findOne(id);
	}

}
