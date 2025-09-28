package com.example.ProfileService.profile.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String name) {
        super("City could not be found: " + name);
    }
}
