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

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "staff")
public class Staff {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "staff_id", unique = false, nullable = false)
	private int staffId;

	@Basic(optional = false)
	@Column(name = "fullname", nullable = false, length = 54)
	private String fullname;

	@Basic(optional = false)
	@Column(name = "phone_number", nullable = false, length = 14)
	private String phoneNumer;

	@Basic(optional = false)
	@Column(name = "email", nullable = true, length = 100)
	private String email;

	@Basic(optional = false)
	@Column(name = "username", nullable = false, length = 30)
	private String username;

	@Basic(optional = false)
	@Column(name = "password", nullable = false, length = 30)
	private String password;

	@JoinColumn(name = "staff_role_id", referencedColumnName = "role_id", nullable = false)
	@ManyToOne(optional = false)
	private Role staffRoleId;

	@Basic(optional = false)
	@Column(name = "image_path", nullable = true, length = 200)
	private String imagePath;

	@Basic(optional = false)
	@Column(name = "fullname_search", nullable = false, length = 101)
	private String fullnameSearch;

	@Basic(optional = false)
	@Column(name = "is_deleted")
	@ColumnDefault("0")
	private boolean isDeleted;

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(String fullname, String phoneNumer, String email, String username, String password, int roleId,
			String imagePath) {
		super();
		this.fullname = fullname;
		this.phoneNumer = phoneNumer;
		this.email = email;
		this.username = username;
		this.password = password;
		this.staffRoleId = new Role(roleId);
		this.imagePath = imagePath;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneNumer() {
		return phoneNumer;
	}

	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getStaffRoleId() {
		return staffRoleId;
	}

	public void setStaffRoleId(Role staffRoleId) {
		this.staffRoleId = staffRoleId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getFullnameSearch() {
		return fullnameSearch;
	}

	public void setFullnameSearch(String fullnameSearch) {
		this.fullnameSearch = fullnameSearch;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

}
