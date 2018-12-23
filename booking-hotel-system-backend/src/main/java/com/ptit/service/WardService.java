package com.ptit.service;

import java.util.List;

import com.ptit.entity.Ward;

public interface WardService {
	
	public List<Ward> getAllWard();
	
	public Ward getById(int id);

}
