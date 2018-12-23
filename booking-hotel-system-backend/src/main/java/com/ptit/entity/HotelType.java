package com.ptit.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotel_type")
public class HotelType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "hotel_type_id", unique = true, nullable = false)
	private int hotelTypeId;
	
	@Basic(optional = false)
	@Column(name = "hotel_type_name", nullable = true, length = 100)
	private String hotelTypeName;

	public HotelType(int hotelTypeId) {
		super();
		this.hotelTypeId = hotelTypeId;
	}

	public HotelType(int hotelTypeId, String hotelTypeName) {
		super();
		this.hotelTypeId = hotelTypeId;
		this.hotelTypeName = hotelTypeName;
	}

	public HotelType() {
		super();
	}

	public int getHotelTypeId() {
		return hotelTypeId;
	}

	public void setHotelTypeId(int hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
	}

	public String getHotelTypeName() {
		return hotelTypeName;
	}

	public void setHotelTypeName(String hotelTypeName) {
		this.hotelTypeName = hotelTypeName;
	}

}
