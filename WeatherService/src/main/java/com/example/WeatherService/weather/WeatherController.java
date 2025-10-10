package com.example.WeatherService.weather;

import com.example.WeatherService.weather.weatherDto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping("/weather/{city}")
    ResponseEntity<WeatherDto> getCurrentWeather(@PathVariable String city) {
        WeatherDto weatherDto = weatherService.getCurrentWeather(city);
        return ResponseEntity.ok(weatherDto);
    }
}
