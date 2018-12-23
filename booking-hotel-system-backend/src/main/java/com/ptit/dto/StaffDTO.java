package com.ptit.dto;

import com.ptit.entity.Staff;

public class StaffDTO {

	private String fullname;
	private String phoneNumer;
	private String email;
	private String username;
	private String password;
	private String imagePath;
	private String roleName;
	private int roleId;
	public StaffDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StaffDTO(String fullname, String phoneNumer, String email, String username, String password,
			String imagePath, String roleName) {
		super();
		this.fullname = fullname;
		this.phoneNumer = phoneNumer;
		this.email = email;
		this.username = username;
		this.password = password;
		this.imagePath = imagePath;
		this.roleName = roleName;
	}
	
	public StaffDTO(Staff staff) {
		this.fullname = staff.getFullname();
		this.phoneNumer = staff.getPhoneNumer();
		this.email = staff.getEmail();
		this.username = staff.getUsername();
		this.password = staff.getPassword();
		this.roleName = staff.getStaffRoleId().getRoleName();
		this.imagePath = staff.getImagePath();
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public Staff toEntity() {
		Staff st = new Staff(this.fullname, this.phoneNumer, this.email, this.username,this.password,this.roleId,this.imagePath);
		
		return st;
	}
	
	
}
