package com.capg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.model.Schedule;

@Repository
public interface ScheduleDao extends CrudRepository<Schedule, Integer> {



}
