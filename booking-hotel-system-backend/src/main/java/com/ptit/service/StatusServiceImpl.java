package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.entity.Status;
import com.ptit.repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	private StatusRepository statusRe;
	
	@Override
	public List<Status> getAllStatus() {
		// TODO Auto-generated method stub
		return statusRe.findAll();
	}

}
