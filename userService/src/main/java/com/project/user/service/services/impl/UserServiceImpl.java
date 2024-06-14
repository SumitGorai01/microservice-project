package com.project.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.user.service.entities.Hotel;
import com.project.user.service.entities.Rating;
import com.project.user.service.entities.User;
import com.project.user.service.exceptions.ResourceNotFoundException;
import com.project.user.service.external.service.HotelService;
import com.project.user.service.repositories.UserRepository;
import com.project.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {

		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		List<User> allUsers = userRepository.findAll();

		
		// fetch rating of the above user from Rating Service
		// http://localhost:9094/ratings/users/
//
//		List<Rating> ratingOfUsers = restTemplate
//				.getForObject("http://localhost:9094/ratings/" , List.class);
//		logger.info("{}", ratingOfUsers);
//
//		((User) allUsers).setRatings(ratingOfUsers);

		return allUsers;
	}

	@Override
	public User getUser(String userId) {

		// get user from database with the help of user repository
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("user with given id is not found on server !!" + userId));

		// fetch rating of the above user from Rating Service
		// http://localhost:9094/ratings/users/98b1848e-e35d-44c1-9508-9ae03f3a3a42

		Rating[] ratingOfUser = restTemplate
				.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		logger.info("{}", ratingOfUser);

		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating->{
			//api call to hotel service to get the hotel
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			//Hotel hotel = forEntity.getBody();	
			Hotel hotel = hotelService.getHotel(rating.getHotelId());	

			//logger.info("response status code : {} ",forEntity.getStatusCode());
			
			//set the hotel to rating 
			rating .setHotel(hotel);
			
			//return the rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	}

	@Override
	public void deleteUserById(String userId) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(userId);
		
	}

	@Override
	public User updateUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
