package com.project.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.user.service.entities.Rating;
import com.project.user.service.external.service.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(10).userId("98b1848e-e35d-44c1-9508-9ae03f3a3a42").hotelId("189361f1-3169-4327-9243-3a9bf919b7dd").feedback("This is created by using feign client.").build();
		Rating savedRating = ratingService.createRating(rating);
		
		System.out.println("Saved Rating : "+savedRating);
	}

}
