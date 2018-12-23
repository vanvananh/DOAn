package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ptit.util.Constants.Operation;
import com.ptit.dto.StaffDTO;
import com.ptit.entity.Staff;
import com.ptit.repository.StaffRepository;
import com.ptit.util.Constants;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterStaff;
import com.ptit.util.specification.CriteriaCustom;
import com.ptit.util.specification.SpecificationBuilder;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	StaffRepository staffRepository;
	
	@Override
	public boolean isExist(int id) {
		// TODO Auto-generated method stub
		return staffRepository.exists(id);
	}

	@Override
	public StaffDTO getById(int id) {
		// TODO Auto-generated method stub
		return new StaffDTO(staffRepository.findOne(id));
	}

	@Override
	public Staff createStaff(Staff staff) {
		// TODO Auto-generated method stub
		Staff st = staffRepository.save(staff);
		return staffRepository.findOne(st.getStaffId());
	}

	@Override
	public Page<Staff> getAllStaff(Paging paging, Sorting sorting, String keywordSearch, FilterStaff filter) {
		SpecificationBuilder<Staff> specification = new SpecificationBuilder<Staff>();
	    // search
	    if (!MethodUtil.isNull(keywordSearch)) {
	      specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.FULL_NAME_SEARCH,
	          Constants.Operation.LIKE, keywordSearch));
	    }
	    
	      // roleId
	      if (!MethodUtil.isNull(filter.getStaffRoleId())) {
	        specification
	            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.STAFF_FILTER_ROLE_ID,
	                Operation.EQUAL, filter.getStaffRoleId()));
	      }
	    
	    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
	    
	    sorting.and(Direction.ASC, Constants.NameColume.STAFF_ID);
	    
	    // query with paging
	    if (!MethodUtil.isNull(paging)) {
	      return staffRepository.findAll(specification.build(), MethodUtil.Pagination(paging, sorting));
	    }
	    // else query not paging
	    return new PageImpl<Staff>(
	    		staffRepository.findAll(specification.build(), MethodUtil.convertSort(sorting)));
			
	}

	@Override
	public void deleteStaff(int id) {
		// TODO Auto-generated method stub
		Staff staffDisable = staffRepository.findOne(id);
		staffDisable.setDeleted(true);
		staffRepository.save(staffDisable);
	}

	@Override
	public Staff updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		return staffRepository.save(staff);
	}

	@Override
	public boolean isRoleExist(int id) {
		// TODO Auto-generated method stub
		return staffRepository.exists(id);
	}

	@Override
	public void deleteListStaff(List<Integer> listId) {
		for(Integer id: listId) {
			deleteStaff(id);
		}
	}

	@Override
	public boolean isListRoleExist(List<Integer> listId) {
		SpecificationBuilder<Staff> specification = new SpecificationBuilder<Staff>();
	    specification.addCriteriaCustom(
	        new CriteriaCustom(Constants.NameColume.STAFF_ID, Constants.Operation.IN, listId));
	    return staffRepository.count(specification.build()) == (long) listId.size();
	}

	@Override
	public Staff getStaffByUsername(String username) {
		// TODO Auto-generated method stub
		return staffRepository.getStaffByUsername(username);
	}


}
