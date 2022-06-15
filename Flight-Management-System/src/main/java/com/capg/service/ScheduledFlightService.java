package com.capg.service;

import java.math.BigInteger;

import com.capg.exception.RecordNotFoundException;
import com.capg.model.ScheduledFlight;

import exceptions.ScheduledFlightNotFoundException;

public interface ScheduledFlightService {

	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);
	
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight);
	
	public String removeScheduledFlight(BigInteger flightId) throws RecordNotFoundException;
	
	public Iterable<ScheduledFlight> viewAllScheduledFlights();
	
	public ScheduledFlight viewScheduledFlight(BigInteger id) throws ScheduledFlightNotFoundException;
}
