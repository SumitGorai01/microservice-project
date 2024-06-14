package com.project.rating.service;

import java.util.List;

import com.project.rating.entities.Rating;

public interface RatingService {

	//create
	Rating create(Rating rating);
	
	//get all rating
	List<Rating> getAllRatings();
	
	//get all by user UserId
	List<Rating> getRatingByUserId(String userId);
	
	//get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);
}
