package com.ptit.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "room_id",unique = false, nullable = false)
	private int roomId;
	
	
	@JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id", nullable = false)
	@ManyToOne(optional = false)
	private Hotel hotelId;
	
	@Basic(optional = false)
	@Column(name = "room_name", unique = false, nullable = false)
	private String roomName;
	
	@JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id", nullable = false)
	@ManyToOne(optional = false)
	private RoomType roomTypeId;
	
	@Basic(optional = false)
	@Column(name = "price", unique = false, nullable = false)
	private Double price;
	
	@Basic(optional = false)
	@Column(name = "acreage", unique = false, nullable = false)
	private int acreage;
	
	@Basic(optional = false)
	@Column(name = "description", nullable = true, length = 1000)
	private String description;
	
	@Basic(optional = false)
	@Column(name = "image_path", nullable = true, length = 1000)
	private String imagePath;
	
	public Room() {
		super();
	}
	

	public Room(int roomId) {
		super();
		this.roomId = roomId;
	}






	public Room(Hotel hotelId, String roomName, RoomType roomTypeId, Double price, int acreage, String description,
			String imagePath) {
		super();
		this.hotelId = hotelId;
		this.roomName = roomName;
		this.roomTypeId = roomTypeId;
		this.price = price;
		this.acreage = acreage;
		this.description = description;
		this.imagePath = imagePath;
	}






	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public RoomType getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(RoomType roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Hotel getHotelId() {
		return hotelId;
	}

	public void setHotelId(Hotel hotelId) {
		this.hotelId = hotelId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}



	public int getAcreage() {
		return acreage;
	}



	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}






	public String getImagePath() {
		return imagePath;
	}






	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	
	
}
