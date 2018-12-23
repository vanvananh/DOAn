package com.ptit.entity;

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
@Table(name = "ward")
public class Ward {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ward_id", unique = true, nullable = false)
	private int wardId;

	@Basic(optional = false)
	@Column(name = "ward_name", nullable = false, length = 500)
	private String wardName;
	
	@JoinColumn(name = "district_id", referencedColumnName = "district_id", nullable = false)
	@ManyToOne(optional = false)
	private District dictrictId;

	public Ward(int wardId, String wardName) {
		super();
		this.wardId = wardId;
		this.wardName = wardName;
	}

	public Ward(int wardId, String wardName, District dictrictId) {
		super();
		this.wardId = wardId;
		this.wardName = wardName;
		this.dictrictId = dictrictId;
	}


	public Ward() {
		super();
	}

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public District getDistrictId() {
		return dictrictId;
	}

	public void setDistrictId(District districtId) {
		this.dictrictId = districtId;
	}

	
	
}
