package com.example.profileService.profile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ApiError> handleCityNotFound(CityNotFoundException ex) {
        ApiError error = new ApiError(404, "City not found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleCityAlreadyExists(CityAlreadyExistsException ex) {
        ApiError error = new ApiError(409, "Conflict", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
