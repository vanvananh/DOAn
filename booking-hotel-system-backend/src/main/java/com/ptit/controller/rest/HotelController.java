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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.dto.HotelDTO;
import com.ptit.dto.HotelSearchDTO;
import com.ptit.entity.Customer;
import com.ptit.entity.Hotel;
import com.ptit.entity.HotelType;
import com.ptit.service.ConfigService;
import com.ptit.service.HotelService;
import com.ptit.service.HotelTypeService;
import com.ptit.util.ApiMessage;
import com.ptit.util.Constants;
import com.ptit.util.DataRespone;
import com.ptit.util.MessageUtil;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.RegularExpressions;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterCustomer;
import com.ptit.util.filter.FilterHotel;

/**
 * @description: .
 * @author Huy Anh
 * @create_date: Feb 26, 2018
 * @modifier: User
 * @modifier_date: Feb 26, 2018
 **/
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.HOTEL)
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	ConfigService configService;
	
	@Autowired
	HotelTypeService hotelType;

	@GetMapping(value = Constants.Url.CRUD.GET_BY_ID)
	public ResponseEntity<?> getAccountById(@RequestParam(name = Constants.Param.ID) int id) {

		// validate param id
		if (!hotelService.isExists(id)) {
			// return message error param id not valid
			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// validate param success then return account by id param
		return new ResponseEntity<HotelDTO>(hotelService.getHotelById(id), HttpStatus.OK);
	}

	@GetMapping(value = Constants.Url.HotelUrl.GET_SEARCH)
	public ResponseEntity<?> getAccountBySearch(@RequestParam(name = "locationId") Integer locationId,
			@RequestParam(name = "typeOfRoomId") Integer typeOfRoomId,
			@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) {

		HotelSearchDTO hotelSearchDto = new HotelSearchDTO(locationId);
		hotelSearchDto.setTypeOfRoomId(typeOfRoomId);

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			hotelSearchDto.setStartDate(date);
			Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			hotelSearchDto.setEndDate(edate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// validate param keywordSearch
		if (MethodUtil.isNull(hotelSearchDto)) {
			return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageUtil.Account.PARAMETER_KEYWORD_SEARCH_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// validate param success then return list account by keyword search
		return new ResponseEntity<List<Hotel>>(hotelService.getHotelBySearch(hotelSearchDto), HttpStatus.OK);
	}
	
	@GetMapping("/getByStaff")
	public ResponseEntity<?> getHotelByStaff(
			@RequestParam(name = Constants.Param.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
			@RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = "hotelId") String sortField,
			@RequestParam(name ="staffId", required = false) Integer staffId,
			@RequestParam(name ="keywordSearch", required = false) String keywordSearch,
			FilterHotel filter) {

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
		Page<Hotel> page = hotelService.getHotelByStaff(
				!MethodUtil.isNull(pageNumber) ? new Paging(pageNumber, configService.getNumberRecordPerPageDefault())
						: null,
				new Sorting(sortType, sortField), staffId,keywordSearch, filter);
		// validate param success then return list customer
		return new ResponseEntity<DataRespone>(
				new DataRespone(pageNumber, page.getTotalPages(), page.getContent().size(), page.getContent()),
				HttpStatus.OK);
	}
	
	@GetMapping("/getAllType")
	  public ResponseEntity<?> getAllType() {
	    return new ResponseEntity<List<HotelType>>(
	    		hotelType.getAllHotelType() ,HttpStatus.OK);
	  }
	
	
	

}
