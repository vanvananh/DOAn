package com.ptit.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.dto.HotelDTO;
import com.ptit.dto.RoomDTO;
import com.ptit.entity.Hotel;
import com.ptit.entity.Room;
import com.ptit.service.ConfigService;
import com.ptit.service.HotelService;
import com.ptit.service.RoomService;
import com.ptit.service.RoomTypeService;
import com.ptit.util.ApiMessage;
import com.ptit.util.Constants;
import com.ptit.util.DataRespone;
import com.ptit.util.MessageUtil;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.RegularExpressions;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterHotel;
import com.ptit.util.filter.FilterRoom;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.ROOM)
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private HotelService hotelService;
	
	
	@Autowired
	ConfigService configService;
	
	  @GetMapping(value = Constants.Url.RoomUrl.GET_LIST_ROOM_OF_HOTEL_NO_PAGING)
	  public ResponseEntity<?> getListRoomOfHotel(
	      @RequestParam(name = Constants.Param.ID, defaultValue = Constants.Common.EMPTY_STRING) int hotelId,
	      @RequestParam(name = "roomTypeId", defaultValue = Constants.Common.EMPTY_STRING) int roomTypeId,
	      @RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) {
		  Date start = null;
		  Date end = null;
		  try {
				start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
	
				end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  
	    // validate param not exists
	    if (!hotelService.isExists(hotelId)) {
	      // return message error param id not valid
	      return new ResponseEntity<ApiMessage>(
	          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Hotel.HOTEL_ID_NOT_EXISTS),
	          HttpStatus.UNPROCESSABLE_ENTITY);
	    }
	    
	    // validate param not exists
	    if (!roomTypeService.isExists(roomTypeId)) {
	      // return message error param id not valid
	      return new ResponseEntity<ApiMessage>(
	          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Room.ROOM_TYPE_ID_NOT_EXISTS),
	          HttpStatus.UNPROCESSABLE_ENTITY);
	    }

	    // validate param success then return list account of group
	    return new ResponseEntity<List<RoomDTO>>(roomService.getRoomByHotel(hotelId,roomTypeId,start,end),
	        HttpStatus.OK);
	  }
	  
	  @GetMapping("/getRooms")
		public ResponseEntity<?> getListRooms(
				@RequestParam(name = Constants.Param.PAGE_NUMBER, required = false) Integer pageNumber,
				@RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
				@RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = "hotelId") String sortField,
				@RequestParam(name ="hotelId", required = false) Integer hotelId,
				@RequestParam(name ="keywordSearch", required = false) String keywordSearch,
				FilterRoom filter) {

			// validate param pageNumber <= 0
			
			
			if (!MethodUtil.isNull(pageNumber) && pageNumber <= Constants.Common.ZERO_INT) {
				// return message error param pageNumber not valid
				return new ResponseEntity<ApiMessage>(
						new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_PAGENUMBER_NOT_VALID),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

			// validate param sortType
			if (!MethodUtil.isNull(sortType)
					&& !MethodUtil.checkRegularExpression(sortType.toLowerCase(), RegularExpressions.SORT_TYPE_PATTERN)) {
				// return message error param sortType not valid
				return new ResponseEntity<ApiMessage>(
						new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_SORTTYPE_NOT_VALID),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
			Page<Room> page = roomService.getListRoom(
					!MethodUtil.isNull(pageNumber) ? new Paging(pageNumber, configService.getNumberRecordPerPageDefault())
							: null,
					new Sorting(sortType, sortField), hotelId,keywordSearch, filter);
			// validate param success then return list customer
			return new ResponseEntity<DataRespone>(
					new DataRespone(pageNumber, page.getTotalPages(), page.getContent().size(), page.getContent()),
					HttpStatus.OK);
		}
	  @GetMapping(value = Constants.Url.CRUD.GET_BY_ID)
		public ResponseEntity<?> getAccountById(@RequestParam(name = Constants.Param.ID) int id) {

			// validate param id
			if (!roomService.isExist(id)) {
				// return message error param id not valid
				return new ResponseEntity<ApiMessage>(
						new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

			// validate param success then return account by id param
			return new ResponseEntity<RoomDTO>(roomService.getRoomById(id), HttpStatus.OK);
		}
	
}
