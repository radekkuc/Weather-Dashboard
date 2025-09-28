package com.example.ProfileService.profile.exception;

public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException(String name) {
        super("City already exists: " + name);
    }
}
