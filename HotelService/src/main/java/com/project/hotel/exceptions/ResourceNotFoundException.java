package com.project.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	
	public ResourceNotFoundException (String s) {
		super(s);
	}
	public ResourceNotFoundException() {
		super("Resources not found");
	}
	
}
