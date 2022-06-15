package com.capg.dao;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.model.Booking;


@Repository
public interface BookingDao extends CrudRepository<Booking, BigInteger> {

}
