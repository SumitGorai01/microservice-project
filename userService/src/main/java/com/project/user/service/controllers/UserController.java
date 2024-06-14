package com.project.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	//create
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
	
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	//get one user
	@GetMapping("/{userId}")
	public ResponseEntity<User> getOneUser(@PathVariable("userId") String userId){
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
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
