package com.ptit.dto;


import com.ptit.entity.Hotel;

public class HotelDTO {
	private int hotelId;

	private String hotelName;

	private String wardName;
	
	private String locationDetail;
	
	private String hotelTypeName;
	
	private String imagePath;
	
	private String description;
	
	

	public HotelDTO() {
		super();
	}


	public HotelDTO(Hotel hotel) {
		this.hotelId = hotel.getHotelId();
		this.hotelName = hotel.getHotelName();
		this.wardName = hotel.getWardId().getWardName() + " " + hotel.getWardId().getDistrictId().getDistrictName() + " " + hotel.getWardId().getDistrictId().getProvinceId().getProvinceName();
		this.locationDetail = hotel.getLocationDetail();
		this.hotelTypeName = hotel.getHotelTypeId().getHotelTypeName();
		this.imagePath = hotel.getImagePath();
		this.description = hotel.getDescription();
	}
	

	public HotelDTO(int hotelId, String hotelName, String wardName, String locationDetail, String hotelTypeName,
			String imagePath, String description) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.wardName = wardName;
		this.locationDetail = locationDetail;
		this.hotelTypeName = hotelTypeName;
		this.imagePath = imagePath;
		this.description = description;
	}



	public int getHotelId() {
		return hotelId;
	}



	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}



	public String getHotelName() {
		return hotelName;
	}



	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}



	public String getWardName() {
		return wardName;
	}



	public void setWardName(String wardName) {
		this.wardName = wardName;
	}



	public String getLocationDetail() {
		return locationDetail;
	}



	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}



	public String getHotelTypeName() {
		return hotelTypeName;
	}



	public void setHotelTypeName(String hotelTypeName) {
		this.hotelTypeName = hotelTypeName;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
