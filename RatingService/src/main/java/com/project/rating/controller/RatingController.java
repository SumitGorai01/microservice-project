package com.project.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rating.entities.Rating;
import com.project.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		
		Rating rating2 = ratingService.create(rating);		
		return ResponseEntity.status(HttpStatus.CREATED).body(rating2);
	}
	
	
	//get all rating
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAll(){
		
		return ResponseEntity.ok(ratingService.getAllRatings());
	}
	
	//get rating by userId
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId( @PathVariable String userId){
		return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
	}
	
	//getRating by hotelId
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId( @PathVariable String hotelId){
		return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
	}

}
