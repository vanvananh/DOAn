package com.ptit.dto;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.web.util.UriUtils;

import com.ptit.entity.Customer;

public class CustomerDTO {
	
	private String fullname;
	private String phoneNumer;
	private String email;
	private String username;
	private String password;
	private String imagePath;
	private Date joinDate;
	
	
	
	public CustomerDTO() {
		super();
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getPhoneNumer() {
		return phoneNumer;
	}


	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	


	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	public String getImagePath() {
		   try {
			      return UriUtils.decode(imagePath, "UTF-8");
			    } catch (UnsupportedEncodingException e) {
			      e.printStackTrace();
			    }
			    return null;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public CustomerDTO(String fullname, String phoneNumer, String email, String username, String password,
			String imagePath, Date joinDate) {
		super();
		this.fullname = fullname;
		this.phoneNumer = phoneNumer;
		this.email = email;
		this.username = username;
		this.password = password;
		this.imagePath = imagePath;
		this.joinDate = joinDate;
	}
	
	public CustomerDTO(Customer customer) {
		this.fullname = customer.getFullname();
		this.email = customer.getEmail();
		this.phoneNumer = customer.getPhoneNumer();
		this.password = customer.getPassword();
		this.imagePath = customer.getImagePath();
		this.username = customer.getUsername();
		this.joinDate = customer.getJoinDate();
	}
	
	public Customer toEntity() {
		return new Customer(this.fullname, this.phoneNumer, this.email, this.username, this.password, this.imagePath, this.joinDate);

}
}
