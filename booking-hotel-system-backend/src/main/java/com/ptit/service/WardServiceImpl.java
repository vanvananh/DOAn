package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.entity.Ward;
import com.ptit.repository.WardRepository;

@Service
public class WardServiceImpl implements WardService {

	@Autowired
	private WardRepository wardRepository;
	@Override
	public List<Ward> getAllWard() {
		// TODO Auto-generated method stub
		return wardRepository.findAll();
	}

	@Override
	public Ward getById(int id) {
		// TODO Auto-generated method stub
		return wardRepository.findOne(id);
	}

}
