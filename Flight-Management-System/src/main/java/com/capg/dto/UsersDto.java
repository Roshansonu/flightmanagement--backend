package com.capg.dto;

import java.math.BigInteger;


public class UsersDto {
	private String userType;
	private BigInteger userId;
	private String userName;
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UsersDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
