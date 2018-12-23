package com.ptit.dto;

import java.util.Date;

public class HotelSearchDTO {
	
	private Integer locationId;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer typeOfRoomId;
	
	

	public HotelSearchDTO(Integer locationId) {
		super();
		this.locationId = locationId;
	}

	public HotelSearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelSearchDTO(Integer wardId, Date startDate, Date endDate, Integer typeOfRoomId) {
		super();
		this.locationId = wardId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeOfRoomId = typeOfRoomId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getTypeOfRoomId() {
		return typeOfRoomId;
	}

	public void setTypeOfRoomId(Integer typeOfRoomId) {
		this.typeOfRoomId = typeOfRoomId;
	}
	
	

}
