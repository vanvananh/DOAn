package com.ptit.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.datastax.driver.mapping.annotations.Table;

@Entity
@Table(name = "status")
public class Status {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "status_id", unique = false, nullable = false)
	private int statusId;
	
	@Basic(optional = false)
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	

	public Status(int statusId) {
		super();
		this.statusId = statusId;
	}

	public Status() {
		super();
	}

	public Status(int statusId, String descrition) {
		super();
		this.statusId = statusId;
		this.description = description;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
