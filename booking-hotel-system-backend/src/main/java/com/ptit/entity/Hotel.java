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
@Table(name = "hotel")
public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "hotel_id", unique = true, nullable = false)
	private int hotelId;

	@Basic(optional = false)
	@Column(name = "hotel_name", nullable = false, length = 500)
	private String hotelName;

	@JoinColumn(name = "ward_id", referencedColumnName = "ward_id", nullable = false)
	@ManyToOne(optional = false)
	private Ward wardId;
	
	@JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
	@ManyToOne(optional = false)
	private Location locationId;
	
	
	@Basic(optional = false)
	@Column(name = "location_detail", nullable = false, length = 300)
	private String locationDetail;
	
	@JoinColumn(name = "hotel_type_id", referencedColumnName = "hotel_type_id", nullable = false)
	@ManyToOne(optional = false)
	private HotelType hotelTypeId;
	
	@JoinColumn(name = "create_staff_id", referencedColumnName = "staff_id", nullable = false)
	@ManyToOne(optional = false)
	private Staff createStaffId;
	
	@Basic(optional = false)
	@Column(name = "image_path", nullable = true, length = 400)
	private String imagePath;
	
	@Basic(optional = false)
	@Column(name = "description", nullable = true, length = 1000)
	private String description;

	public Hotel() {
		super();
	}

	public Hotel(int hotelId, String hotelName, Ward wardId, Location locationId, String locationDetail, HotelType hotelTypeId,
			String imagePath, String description, Staff staffId) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.wardId = wardId;
		this.locationDetail = locationDetail;
		this.hotelTypeId = hotelTypeId;
		this.locationId = locationId;
		this.imagePath = imagePath;
		this.description = description;
		this.createStaffId = staffId;
	}

	public Hotel(int hotelId) {
		super();
		this.hotelId = hotelId;
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

	public Ward getWardId() {
		return wardId;
	}

	public void setProvinceId(Ward wardId) {
		this.wardId = wardId;
	}

	public String getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

	public HotelType getHotelTypeId() {
		return hotelTypeId;
	}

	public void setHotelTypeId(HotelType hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
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

	public Location getLocationId() {
		return locationId;
	}

	public void setLocationId(Location locationId) {
		this.locationId = locationId;
	}

	public void setWardId(Ward wardId) {
		this.wardId = wardId;
	}

	public Staff getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(Staff createStaffId) {
		this.createStaffId = createStaffId;
	}

	

}
