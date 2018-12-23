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
@Table(name = "room_type")
public class RoomType implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "room_type_id", unique = true,nullable = false)
	private int roomTypeId;
	
	@Basic(optional = false)
	@Column(name = "room_type_name", nullable = false, length = 150)
	private String roomTypeName;
	
	
	
	public RoomType() {
		super();
	}



	public RoomType(int roomTypeId, String roomTypeName) {
		super();
		this.roomTypeId = roomTypeId;
		this.roomTypeName = roomTypeName;
	}



	public int getRoomTypeId() {
		return roomTypeId;
	}



	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}



	public String getRoomTypeName() {
		return roomTypeName;
	}



	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}


	
}
