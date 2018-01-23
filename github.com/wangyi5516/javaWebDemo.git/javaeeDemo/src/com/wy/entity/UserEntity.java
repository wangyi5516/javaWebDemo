package com.wy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity 
public class UserEntity {	
	 
	@Column(name="userId")
	private String userId;
	@Column(name="password")
	private String password;
	@Column(name="id")
	private Integer id;
	@Column(name="sex")
	private String sex;
	@Column(name="phone")
	private String phone;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", password=" + password + ", id=" + id + ", sex=" + sex + ", phone="
				+ phone + "]";
	}
	
}
