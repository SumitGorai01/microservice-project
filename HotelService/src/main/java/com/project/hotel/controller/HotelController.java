package com.project.hotel.controller;

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

import com.project.hotel.entities.Hotel;
import com.project.hotel.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	//create hotel
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		
		Hotel hotel1 = hotelService.create(hotel); 
		
		return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);			
				
	}
	
	
	//get all hotels
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAll(){
		
		return ResponseEntity.ok(hotelService.getAllHotel());	
	}
	
	
	//get one hotel
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getOneHotel(@PathVariable String hotelId){
		
		Hotel hotel3= hotelService.getOneHotel(hotelId);
		
		return ResponseEntity.status(HttpStatus.OK).body(hotel3);
	}
}
