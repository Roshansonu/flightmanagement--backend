package com.capg.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dto.LoginDto;
import com.capg.dto.UsersDto;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.model.Users;
import com.capg.service.UserService;

@ComponentScan(basePackages = "com")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/createUser")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public void addUser(@RequestBody Users newUser) {

		userService.createUser(newUser);
	}

	@GetMapping("/readAllUsers")
	public Iterable<Users> readAllUsers() {

		return userService.displayAllUser();
	}

	@PutMapping("/updateUser")
	@ExceptionHandler(RecordNotFoundException.class)
	public void updateUser(@RequestBody Users updateUser) {

		userService.updateUser(updateUser);
	}

	@GetMapping("/searchUser/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchUserByID(@PathVariable("id") BigInteger userId) {

		return userService.findUserById(userId);
	}

	@DeleteMapping("/deleteUser/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public void deleteBookingByID(@PathVariable("id") BigInteger userId) {

		userService.deleteUser(userId);
	}
	
	@GetMapping("/ping")
	public String test() {
		return "Pong";
//		userService.deleteUser(userId);
	}

	// @CrossOrigin(origins = "http://localhost:4200")
	// @GetMapping("/login/{username}/{password}")
	// public ResponseEntity<UsersDto> login(@PathVariable("username") String username, @PathVariable("password") String password) {
		// return userService.login(username, password);
	// }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<UsersDto> doLogin(@RequestBody LoginDto loginObj, HttpServletRequest req) {
		String username = loginObj.getUsername();
		String password = loginObj.getPassword();
		
		if (username != null && password != null) {
			UsersDto userDto = userService.login(username, password);
			String userType = userDto.getUserType();
			
			if (userType != null) {
				HttpSession session = req.getSession(true);
				
				session.setAttribute("userType", userType);
				session.setAttribute("username", username);
				
				return new ResponseEntity<UsersDto>(userDto, HttpStatus.OK);
			} else {
				// Throw Invalid User Exception
				return new ResponseEntity<UsersDto>(userDto, HttpStatus.NOT_FOUND);
			}
		} else {
			// Throw Invalid User Exception
			throw new RuntimeException("Invalid User");
		}
	}
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/logout")
	public boolean doLogout(HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		
		if(session != null)
		{
			session.invalidate(); // to logout
			return true;
		}
		else return false;
	}
	
	
	
}
