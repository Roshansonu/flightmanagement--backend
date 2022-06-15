package com.capg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.AirportDao;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.model.Airport;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	   AirportDao airportRepository;

		@Override
		public Airport addAirport(Airport airport) {
	
			Airport saveInfo =airportRepository.save(airport);
			 return saveInfo;
			
			}
		
	

		@Override
		public Iterable<Airport> viewAllAirport() {

			return airportRepository.findAll();
		}

		@Override
		public Airport viewAirport(String airportCode) {
			Optional<Airport> findById = airportRepository.findById(airportCode);
			
			if(findById.isPresent()) {
				
				return findById.get();
			}
			else
			
				throw new RecordNotFoundException("Airport with airport code : " + airportCode  +"not exists");
			
		}

		@Override
		public Airport updateAirport(Airport airport) {
			Optional<Airport> findById = airportRepository.findById(airport.getAirportCode());
			if(findById.isPresent()) {
				airportRepository.save(airport);
			}
			else
				throw new RecordNotFoundException("Airport with code : " + airport.getAirportCode() + "not exists");
			
			return airport;
		}

		@Override
		public String deleteAirport(String airportCode) {
			Optional<Airport> findById = airportRepository.findById(airportCode);
			if(findById.isPresent()) {
				
				airportRepository.deleteById(airportCode);
				
			return "Airport Deleted";
			}
			else 
				
				throw new RecordNotFoundException("Airport with code : " + airportCode + "not exists");
				
		}

}
