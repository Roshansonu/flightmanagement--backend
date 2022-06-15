package com.capg.service;

import java.util.List;

import com.capg.model.Passenger;

public interface PassengerService {

	public List<Passenger> getAllPasenger();

	public Passenger AddPassenger(Passenger p);
	
	public Passenger updatePassengerBypnr(Long prn, Passenger passenger);
	
	public String Deletepasenger(Long prn);
	
}
