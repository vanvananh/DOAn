package com.ptit.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.datastax.driver.mapping.annotations.Table;

@Entity
@Table(name = "location")
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "location_id", unique = true, nullable = false)
	private Integer locationId;

	@Basic(optional = false)
	@Column(name = "location_name", nullable = false, length = 500)
	private String locationName;
	
	@Basic(optional = false)
	@Column(name = "popular", unique = false,nullable = false)
	private boolean popular;
	
	@Basic(optional = false)
	@Column(name = "image_path", nullable = true, length = 400)
	private String imagePath;
	
	

	public Location() {
		super();
	}
	
	

	public Location(Integer locationId) {
		super();
		this.locationId = locationId;
	}



	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public boolean isPopular() {
		return popular;
	}

	public void setPopular(boolean popular) {
		this.popular = popular;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	

}
