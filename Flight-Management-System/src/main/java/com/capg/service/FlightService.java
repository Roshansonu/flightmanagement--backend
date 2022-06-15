package com.capg.service;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;

import com.capg.model.Flight;

public interface FlightService {
	public Flight addFlight(Flight flight);

	public Iterable<Flight> viewAllFlight();

	public Flight viewFlight(int i);

	public Flight modifyFlight(Flight flight);

	public String removeFlight(BigInteger flightNumber);

}
