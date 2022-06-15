package com.capg.service;

import org.springframework.http.ResponseEntity;

import com.capg.model.Airport;

public interface AirportService {
	
	public Iterable<Airport> viewAllAirport();
	
	public Airport viewAirport(String airportCode);
	
	public Airport addAirport(Airport airport);
	
     public Airport updateAirport(Airport airport);
      
    public String deleteAirport(String airportCode);
	
}
