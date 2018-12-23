package com.ptit.util.filter;

public class FilterHotel {
	private Integer hotelTypeId;

	public FilterHotel(Integer hotelTypeId) {
		super();
		this.hotelTypeId = hotelTypeId;
	}

	public FilterHotel() {
		super();
	}

	public Integer getHotelTypeId() {
		return hotelTypeId;
	}

	public void setHotelTypeId(Integer hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
	}
	
	

}
