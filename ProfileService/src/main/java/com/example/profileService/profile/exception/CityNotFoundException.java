package com.example.profileService.profile.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String name) {
        super("City could not be found: " + name);
    }
}
