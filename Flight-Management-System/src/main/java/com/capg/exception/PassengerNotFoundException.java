package com.capg.exception;

public class PassengerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1518142165487282790L;

	public PassengerNotFoundException(String msg){
		super(msg);
	}
	
}
