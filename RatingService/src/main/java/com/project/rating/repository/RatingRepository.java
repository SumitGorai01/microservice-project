package com.project.rating.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.project.rating.entities.Rating;

@EnableMongoRepositories
public interface RatingRepository extends MongoRepository<Rating, String>{

	//custom finder method
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
