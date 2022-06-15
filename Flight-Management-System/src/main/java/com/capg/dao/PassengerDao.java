package com.capg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.model.Passenger;

@Repository
public interface PassengerDao extends CrudRepository<Passenger, Long>  {

}
