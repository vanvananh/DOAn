package com.ptit.controller.rest;

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

import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.RegularExpressions;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterCustomer;
import com.ptit.dto.CustomerDTO;
import com.ptit.entity.Customer;
import com.ptit.service.ConfigService;
import com.ptit.service.CustomerService;
import com.ptit.util.ApiMessage;
import com.ptit.util.Constants;
import com.ptit.util.DataRespone;
import com.ptit.util.MessageUtil;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.CUSTOMER)
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ConfigService configService;

	@GetMapping(value = Constants.Url.CRUD.GET_BY_ID)
	public ResponseEntity<?> getCustomerById(@RequestParam(name = Constants.Param.ID) int id) {

		// validate param id
		if (!customerService.isExists(id)) {
			// return message error param id not valid
			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// validate param success then return account by id param
		return new ResponseEntity<CustomerDTO>(customerService.getById(id), HttpStatus.OK);
	}
	
	
	@GetMapping(value = Constants.Url.CustomerUrl.LOGIN)
	public ResponseEntity<?> getCustomerByUsername(@RequestParam("username") String username) {


		// validate param success then return account by id param
		return new ResponseEntity<Customer>(customerService.getCustomerByUsername(username), HttpStatus.OK);
	}

	@PostMapping(value = Constants.Url.CRUD.CREATE)
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customer) {
		return new ResponseEntity<Customer>(customerService.createCustomer(customer.toEntity()), HttpStatus.OK);
	}

	@GetMapping(value = Constants.Url.AccountUrl.GET_SEARCH)
	public ResponseEntity<?> getAccountBySearch(
			@RequestParam(name = Constants.Param.KEYWORD_SEARCH, defaultValue = Constants.Common.EMPTY_STRING) String keywordSearch) {

		// validate param keywordSearch
		if (MethodUtil.isNull(keywordSearch)
				|| !MethodUtil.validateStringlength(keywordSearch, Constants.Common.FIFTY_INT)) {
			return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageUtil.Account.PARAMETER_KEYWORD_SEARCH_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// validate param success then return list account by keyword search
		return new ResponseEntity<List<Customer>>(
				customerService.getListCustomerByName(MethodUtil.formatKeySearch(keywordSearch.trim())), HttpStatus.OK);
	}

	@GetMapping(value = Constants.Url.CRUD.GET_ALL)
	public ResponseEntity<?> getAllCustomer(
			@RequestParam(name = Constants.Param.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
			@RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_ACCOUNT_DEFAULT) String sortField,
			@RequestParam(name = Constants.Param.KEYWORD_SEARCH, required = false) String keywordSearch,
			FilterCustomer filter) {

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
		Page<Customer> page = customerService.getAllCustomers(
				!MethodUtil.isNull(pageNumber) ? new Paging(pageNumber, configService.getNumberRecordPerPageDefault())
						: null,
				new Sorting(sortType, sortField), keywordSearch, filter);
		// validate param success then return list customer
		return new ResponseEntity<DataRespone>(
				new DataRespone(pageNumber, page.getTotalPages(), page.getContent().size(), page.getContent()),
				HttpStatus.OK);
	}
	
}
