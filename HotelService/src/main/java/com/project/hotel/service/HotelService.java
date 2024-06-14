package com.project.hotel.service;

import java.util.List;

import com.project.hotel.entities.Hotel;

public interface HotelService {

	//create
	Hotel create(Hotel hotel);
	
	//get all
	List<Hotel> getAllHotel();
	
	//get single
	Hotel getOneHotel(String id);	
	
}
