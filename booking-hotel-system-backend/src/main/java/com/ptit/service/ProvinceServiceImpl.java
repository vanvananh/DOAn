package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.entity.Province;
import com.ptit.repository.ProvinceRepository;

@Service
public class ProvinceServiceImpl implements ProvinceService{

	@Autowired
	private ProvinceRepository provinceRe;
	
	@Override
	public List<Province> getAllProvice() {
		// TODO Auto-generated method stub
		return provinceRe.findAll();
	}

	@Override
	public Province getById(int id) {
		// TODO Auto-generated method stub
		return provinceRe.findOne(id);
	}

}
