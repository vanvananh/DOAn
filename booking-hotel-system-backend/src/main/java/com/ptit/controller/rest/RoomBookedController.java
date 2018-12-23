package com.ptit.controller.rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.dto.RoomBookedDTO;
import com.ptit.entity.Customer;
import com.ptit.entity.Room;
import com.ptit.entity.RoomBooked;
import com.ptit.service.ConfigService;
import com.ptit.service.RoomBookedService;
import com.ptit.service.RoomService;
import com.ptit.util.ApiMessage;
import com.ptit.util.Constants;
import com.ptit.util.DataRespone;
import com.ptit.util.MessageUtil;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.RegularExpressions;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterBooking;
import com.ptit.util.filter.FilterCustomer;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.BOOKING)
public class RoomBookedController {
	
	@Autowired
	RoomBookedService bookingService;
	
	@Autowired
	ConfigService configService;
	
	@Autowired
	RoomService roomService;
	
	@PostMapping(value = Constants.Url.CRUD.CREATE)
	public ResponseEntity<?> createCustomer(@RequestBody RoomBookedDTO booking) throws IOException, ParseException {
		System.out.println(booking.getEndDate());
		RoomBooked r = booking.toEntity();
		
		List<Room> lst = roomService.checkEmpty(r.getRoomId().getRoomId(), r.getStartDate(),
				r.getEndDate());
		if (lst.size() > 0) {
			// return message error param id not valid
			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.ROOM_NO_EMPTY),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<RoomBooked>(bookingService.createBooking(booking.toEntity()), HttpStatus.OK);
	}
	
	@GetMapping(value = Constants.Url.CRUD.GET_BY_ID)
	public ResponseEntity<?> getById(@RequestParam(name = Constants.Param.ID) int id) {

		// validate param id
		if (!bookingService.isExists(id)) {
			// return message error param id not valid
			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// validate param success then return account by id param
		return new ResponseEntity<RoomBooked>(bookingService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = Constants.Url.RoomUrl.GET_LIST_BOOKING)
	  public ResponseEntity<?> getListRoomOfHotel(
	      @RequestParam(name = Constants.Param.ID, defaultValue = Constants.Common.EMPTY_STRING) int id) {


	    return new ResponseEntity<List<RoomBooked>>(bookingService.getBookingByCustomerId(id),
	        HttpStatus.OK);
	  }
	
	@GetMapping(value = Constants.Url.CRUD.GET_ALL)
	public ResponseEntity<?> getAllCustomer(
			@RequestParam(name = Constants.Param.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
			@RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_ACCOUNT_DEFAULT) String sortField,
			FilterBooking filter) {

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
		Page<RoomBooked> page = bookingService.getAllRoomBooked(
				!MethodUtil.isNull(pageNumber) ? new Paging(pageNumber, configService.getNumberRecordPerPageDefault())
						: null,
				new Sorting(sortType, sortField), filter);
		// validate param success then return list customer
		return new ResponseEntity<DataRespone>(
				new DataRespone(pageNumber, page.getTotalPages(), page.getContent().size(), page.getContent()),
				HttpStatus.OK);
	}

}
