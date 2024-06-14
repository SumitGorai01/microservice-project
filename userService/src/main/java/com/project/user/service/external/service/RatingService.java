package com.project.user.service.external.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.project.user.service.entities.Rating;

@FeignClient(name = "RATINGSERVICE")
@Service
public interface RatingService {

	//get
	
	//post
	@PostMapping("/ratings/")
	Rating createRating(Rating rating); //If we know the entity name
//	Rating createRating(Map<String, Object> rating); //If we don't know the entity name use Map

	//put
	@PutMapping("/ratings/{ratingId}")
	Rating updateRating(@PathVariable String ratingId, Rating rating);
	
	//delete
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
	
}
