package com.project.user.service.services;

import java.util.List;

import com.project.user.service.entities.User;

public interface UserService {

	
	//user operations
	
	//create
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();

	//get single user of given userId
	User getUser(String userId);
	
	//delete
	void deleteUserById(String userId);
	
	//update
	User updateUser(String userId);
}
