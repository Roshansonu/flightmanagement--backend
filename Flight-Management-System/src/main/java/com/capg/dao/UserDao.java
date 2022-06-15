package com.capg.dao;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capg.model.Users;

public interface UserDao  extends CrudRepository<Users, BigInteger>{
	Optional<Users> findByUserNameAndUserPassword(String userName, String userPassword);
}
