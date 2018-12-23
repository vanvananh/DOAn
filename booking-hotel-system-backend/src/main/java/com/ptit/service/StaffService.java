package com.ptit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ptit.dto.StaffDTO;
import com.ptit.entity.Staff;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterStaff;

public interface StaffService {
	public boolean isExist(int id);
	
	public StaffDTO getById(int id);
	
	public Staff createStaff(Staff staff);
	
	public Page<Staff> getAllStaff(Paging paging, Sorting sorting, String keywordSearch, FilterStaff filter);
	
	public void deleteStaff(int id);
	
	public Staff updateStaff(Staff staff);
	
	public boolean isRoleExist(int id);
	
	public void deleteListStaff(List<Integer> listId);
	
	public boolean isListRoleExist(List<Integer> listId);
	
	public Staff getStaffByUsername(String username);

}
