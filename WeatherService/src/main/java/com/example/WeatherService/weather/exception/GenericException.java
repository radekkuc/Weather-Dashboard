package com.example.WeatherService.weather.exception;

public class GenericException extends RuntimeException {
    public GenericException() {
        super("Something went wrong");
    }
}
