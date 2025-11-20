package com.example.profileService.profile.exception;

public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException(String name) {
        super("City already exists: " + name);
    }
}
