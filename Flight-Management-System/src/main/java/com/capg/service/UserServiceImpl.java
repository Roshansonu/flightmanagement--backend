package com.capg.service;

import java.math.BigInteger;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.UserDao;
import com.capg.dto.UsersDto;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.model.Users;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public ResponseEntity<Users> createUser(Users newUser) {
		BigInteger userId = newUser.getUserId();
		
		if (userId == null) {
			userDao.save(newUser);
			return new ResponseEntity<Users>(newUser, HttpStatus.OK);
		}
	
		Optional<Users> findUserById = userDao.findById(newUser.getUserId());
		try {
			if (!findUserById.isPresent()) {
				userDao.save(newUser);
				return new ResponseEntity<Users>(newUser, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"User with Id: " + newUser.getUserId() + " already exists!!");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Users updateUser(Users updateUser) {
	
		Optional<Users> findUserById = userDao.findById(updateUser.getUserId());
		if (findUserById.isPresent()) {
			userDao.save(updateUser);
		} else
			throw new RecordNotFoundException(
					"User with Id: " + updateUser.getUserId() + " not exists!!");
		return updateUser;
	}

	@Override
	public String deleteUser(BigInteger UserId) {
	
		Optional<Users> findBookingById = userDao.findById(UserId);
		if (findBookingById.isPresent()) {
			userDao.deleteById(UserId);
			return "User Deleted!!";
		} else
			throw new RecordNotFoundException("User not found for the entered UserID");
	}

	@Override
	public Iterable<Users> displayAllUser() {
		
		return userDao.findAll();
	}

	@Override
	public ResponseEntity<Users> findUserById(BigInteger userId) {
		
		Optional<Users> findById = userDao.findById(userId);
	
			if (findById.isPresent()) {
				Users findUser = findById.get();
				return new ResponseEntity<Users>(findUser, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + userId);
		}
	@Override
	public UsersDto login(String username, String password) {
		Optional<Users> findByUserNameAndUserPassword = userDao.findByUserNameAndUserPassword(username, password);
		
		try {
			if (findByUserNameAndUserPassword.isPresent()) {
				Users user = findByUserNameAndUserPassword.get();
				UsersDto usersDto = new UsersDto();
				usersDto.setUserId(user.getUserId());
				usersDto.setUserName(user.getUserName());
				usersDto.setUserType(user.getUserType());
				return usersDto;
			} else {
				// return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
				throw new RuntimeException("User not found");
			}
		} catch (RecordAlreadyPresentException e) {
			throw new RuntimeException("User not found");
		}
	}
	
		
	
	/*Optional<Users> findById=userDao.findById(userId);
	{
		
		String role = UserRepository.verifyUser(username, password);
		return role;
	}*/

	
	}

	/*@Override
	public void login(String username, String password) {
		
	}*/
 

 
