package com.ptit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ptit.dto.HotelDTO;
import com.ptit.dto.HotelSearchDTO;

import com.ptit.entity.Hotel;
import com.ptit.entity.Room;
import com.ptit.repository.HotelRepository;
import com.ptit.repository.RoomRepository;
import com.ptit.util.Constants;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.Constants.Operation;
import com.ptit.util.filter.FilterHotel;
import com.ptit.util.specification.CriteriaCustom;
import com.ptit.util.specification.SpecificationBuilder;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	

	@Override
	public boolean isExists(int id) {
		// TODO Auto-generated method stub
		return hotelRepository.exists(id);
	}


	@Override
	public HotelDTO getHotelById(int id) {
		// TODO Auto-generated method stub
		return new HotelDTO(hotelRepository.findOne(id));
	}


	@Override
	public List<Hotel> getHotelBySearch(HotelSearchDTO hotelSearchDto) {
		// TODO Auto-generated method stub
		List<Hotel> lstHotel = hotelRepository.getHotelBySearch(hotelSearchDto);
		List<Hotel> lstHotelResult = new ArrayList<>();
		for(Hotel h: lstHotel) {
			List<Room> r = roomRepository.getListRoomOfHotel(h.getHotelId(), hotelSearchDto.getTypeOfRoomId());
			for(Room room: r) {
				List<Room> lst = roomRepository.checkEmpty(room.getRoomId(), hotelSearchDto.getStartDate(), hotelSearchDto.getEndDate());
				if(lst == null || lst.size()==0) {
					lstHotelResult.add(h);
					break;
				}
			}
			}
		return lstHotelResult;
	}


	@Override
	public Page<Hotel> getHotelByStaff(Paging paging, Sorting sorting, Integer staffId,String keywordSearch, FilterHotel filter) {
		SpecificationBuilder<Hotel> specification = new SpecificationBuilder<Hotel>();
	    if (!MethodUtil.isNull(keywordSearch)) {
	      specification.addCriteriaCustom(new CriteriaCustom("hotelName",
	          Constants.Operation.LIKE, keywordSearch));
	    }
		
	    if (!MethodUtil.isNull(staffId)) {
	      specification.addCriteriaCustom(new CriteriaCustom("createStaffId",
	          Constants.Operation.EQUAL, staffId));
	    }
	    
	    
	    
//	    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
	      // roleId
	      if (!MethodUtil.isNull(filter.getHotelTypeId())) {
	        specification
	            .addCriteriaCustom(new CriteriaCustom("hotelTypeId",
	                Operation.EQUAL, filter.getHotelTypeId()));
	      }
	    sorting.and(Direction.ASC, "hotelId");
	    
	    // query with paging
	    if (!MethodUtil.isNull(paging)) {
	      return hotelRepository.findAll(specification.build(), MethodUtil.Pagination(paging, sorting));
	    }
	    // else query not paging
	    return new PageImpl<Hotel>(
	    		hotelRepository.findAll(specification.build(), MethodUtil.convertSort(sorting)));
	}
	


}
