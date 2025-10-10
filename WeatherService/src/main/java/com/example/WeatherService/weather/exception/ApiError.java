package com.example.WeatherService.weather.exception;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ApiError {
    private String message;
    private int status;
    private String error;
    private LocalDateTime time;

    public ApiError(String message, int status, String error) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.time = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
