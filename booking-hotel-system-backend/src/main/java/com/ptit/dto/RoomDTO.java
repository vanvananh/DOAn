package com.ptit.dto;

import com.ptit.entity.Room;

public class RoomDTO {

	private Integer roomId;
	private String roomName;
	private String hotelName;
	private String roomTypeName;
	private String description;
	private Integer acreage;
	private Double price;
	private String imagePath;
	
	public RoomDTO(Room room) {
		this.roomId = room.getRoomId();
		this.roomName = room.getRoomName();
		this.roomTypeName = room.getRoomTypeId().getRoomTypeName();
		this.hotelName = room.getHotelId().getHotelName();
		this.description = room.getDescription();
		this.price = room.getPrice();
		this.acreage = room.getAcreage();
		this.imagePath = room.getImagePath();
	}
	
	public RoomDTO() {
		super();
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAcreage() {
		return acreage;
	}

	public void setAcreage(Integer acreage) {
		this.acreage = acreage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
