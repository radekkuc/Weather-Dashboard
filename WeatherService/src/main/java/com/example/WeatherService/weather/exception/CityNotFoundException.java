package com.example.WeatherService.weather.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String city) {
        super("Could not find city with given name: " + city);
    }
}
