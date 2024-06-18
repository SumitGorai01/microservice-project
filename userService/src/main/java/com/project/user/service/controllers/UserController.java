package com.project.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties.OkHttp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.user.service.entities.User;
import com.project.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//create
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
	
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	int retryCount=1;

	//get one user
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getOneUser(@PathVariable("userId") String userId){
		logger.info("Get Single user Handler : UserController");
		logger.info("Retry Count :{}",retryCount);
		retryCount++;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//create circuit breaker for fallback
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		logger.info("fallback is executed because Service is down : "+ex.getMessage());
		
		User user = User.builder()
					.email("dummy@gmail.com")
					.name("dummy")
					.about("This user is created because some service is down.")
					.userId("1234567")
					.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	//get all user
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUsers = userService.getAllUser();
	
		return ResponseEntity.ok(allUsers);
		
	}
	
	//delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId){
		userService.deleteUserById(userId);
		
		return ResponseEntity.ok().build();
				
	}
	//update user
}
