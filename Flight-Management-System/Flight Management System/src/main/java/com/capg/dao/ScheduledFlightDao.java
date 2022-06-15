package com.capg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledFlightDao extends CrudRepository<com.capg.model.ScheduledFlight, Integer> {

}
