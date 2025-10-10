package com.example.WeatherService.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ApiError> handleCityNotFound(CityNotFoundException ex) {
        ApiError error = new ApiError(ex.getMessage(), 400, "Bad Request");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiError> handleGeneric(GenericException ex) {
        ApiError error = new ApiError(ex.getMessage(), 502, "External error");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }
}
