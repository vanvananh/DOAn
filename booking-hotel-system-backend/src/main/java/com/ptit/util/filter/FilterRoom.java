package com.ptit.util.filter;

public class FilterRoom {
	private Integer roomTypeId;

	public FilterRoom(Integer roomTypeId) {
		super();
		this.roomTypeId = roomTypeId;
	}

	public FilterRoom() {
		super();
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
}
