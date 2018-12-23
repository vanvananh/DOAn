package com.ptit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "customer_id", unique = false, nullable = false)
	private int customerId;

	@Basic(optional = false)
	@Column(name = "fullname", nullable = false, length = 54)
	private String fullname;

	@Basic(optional = false)
	@Column(name = "phone_number", nullable = false, length = 14)
	private String phoneNumer;

	@Basic(optional = false)
	@Column(name = "email", nullable = true, length = 100)
	private String email;

	@Basic(optional = false)
	@Column(name = "username", nullable = false, length = 30)
	private String username;

	@Basic(optional = false)
	@Column(name = "password", nullable = false, length = 30)
	private String password;

	@Basic(optional = false)
	@Column(name = "image_path", nullable = true, length = 200)
	private String imagePath;

	@Basic(optional = false)
	@Column(name = "join_date", nullable = false, length = 10, insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date joinDate;

	@Basic(optional = false)
	@Column(name = "fullname_search", nullable = false, length = 101)
	private String fullnameSearch;

	public Customer(int customerId) {
		super();
		this.customerId = customerId;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String fullname, String phoneNumer, String email, String username, String password,
			String imagePath, Date joinDate) {
		super();
		this.fullname = fullname;
		this.phoneNumer = phoneNumer;
		this.email = email;
		this.password = password;
		this.imagePath = imagePath;
		this.username = username;
		this.joinDate = joinDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getFullnameSearch() {
		return fullnameSearch;
	}

	public void setFullnameSearch(String fullnameSearch) {
		this.fullnameSearch = fullnameSearch;
	}
	
	

}
