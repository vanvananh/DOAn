package com.ptit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ptit.dto.CustomerDTO;
import com.ptit.entity.Customer;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterCustomer;

public interface CustomerService {
	public boolean isExists(int id);
	
	public CustomerDTO getById(int id);
	
	public List<Customer> getListCustomerByName(String name);
	
	public Customer createCustomer(Customer customer);
	
	public Page<Customer> getAllCustomers(Paging paging, Sorting sorting, String keywordSearch, FilterCustomer filter);
	
	public Customer getCustomerByUsername(String username);

}
