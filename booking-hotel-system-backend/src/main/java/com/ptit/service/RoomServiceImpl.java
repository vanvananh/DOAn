package com.ptit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ptit.dto.RoomDTO;
import com.ptit.entity.Hotel;
import com.ptit.entity.Room;
import com.ptit.repository.RoomRepository;
import com.ptit.util.Constants;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.Constants.Operation;
import com.ptit.util.filter.FilterHotel;
import com.ptit.util.filter.FilterRoom;
import com.ptit.util.specification.CriteriaCustom;
import com.ptit.util.specification.SpecificationBuilder;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public RoomDTO getRoomById(int id) {
		// TODO Auto-generated method stub
		return new RoomDTO(roomRepository.findOne(id));
	}

	@Override
	public boolean isExist(int id) {
		// TODO Auto-generated method stub
		return roomRepository.exists(id);
	}

	@Override
	public List<RoomDTO> getRoomByHotel(int hotelId, int roomTypeId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Room> lstRoom = roomRepository.getListRoomOfHotel(hotelId,roomTypeId);
		List<RoomDTO> lstRoomDTO = new ArrayList<RoomDTO>();
		for(Room r: lstRoom) {
			List<Room> check = checkEmpty(r.getRoomId(), startDate, endDate);
			if(check.size()<=0 ) {
				RoomDTO roomDTO = new RoomDTO(r);
				lstRoomDTO.add(roomDTO);
			}
			
		}
		return lstRoomDTO;
	}

	@Override
	public List<Room> checkEmpty(int id, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return roomRepository.checkEmpty(id, startDate, endDate);
	}

	@Override
	public  Page<Room>  getListRoom(Paging paging, Sorting sorting, Integer hotelId, String keywordSearch,
			FilterRoom filter) {
		SpecificationBuilder<Room> specification = new SpecificationBuilder<Room>();
	    // search
	    // search
	    if (!MethodUtil.isNull(keywordSearch)) {
	      specification.addCriteriaCustom(new CriteriaCustom("roomName",
	          Constants.Operation.LIKE, keywordSearch));
	    }
		
	    if (!MethodUtil.isNull(hotelId)) {
	      specification.addCriteriaCustom(new CriteriaCustom("hotelId.hotelId",
	          Constants.Operation.EQUAL, hotelId));
	    }
	    
	    
	    
//	    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
	      // roleId
	      if (!MethodUtil.isNull(filter.getRoomTypeId())) {
	        specification
	            .addCriteriaCustom(new CriteriaCustom("roomTypeId",
	                Operation.EQUAL, filter.getRoomTypeId()));
	      }
	    sorting.and(Direction.ASC, "roomId");
	    
	    // query with paging
	    if (!MethodUtil.isNull(paging)) {
	      return roomRepository.findAll(specification.build(), MethodUtil.Pagination(paging, sorting));
	    }
	    // else query not paging
	    return new PageImpl<Room>(
	    		roomRepository.findAll(specification.build(), MethodUtil.convertSort(sorting)));
	}



}
