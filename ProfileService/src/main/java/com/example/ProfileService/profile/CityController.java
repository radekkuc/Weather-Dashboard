package com.example.ProfileService.profile;

import com.example.ProfileService.profile.WeatherDto.WeatherDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CityController {
    private final CityService cityService;
    private final WeatherService weatherService;

    public CityController(CityService cityService, WeatherService weatherService) {
        this.cityService = cityService;
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{city}")
    ResponseEntity<WeatherDto> getCurrentWeather(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(city));
    }

    @GetMapping("/favourite/{userId}")
    public ResponseEntity<List<City>> getFavouriteCities(@PathVariable Long userId) {
        List<City> favourites = cityService.getFavouriteCities(userId);
        return ResponseEntity.ok(favourites);
    }

    @PostMapping("/{userId}/favourite")
    public ResponseEntity<City> addFavouriteCity(@PathVariable Long userId, @RequestBody String name) {
        City saved = cityService.addFavouriteCity(userId, name);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/{userId}/favourites")
    public ResponseEntity<List<City>> addFavouriteCities(@PathVariable Long userId, @RequestBody List<String> names) {
        List<City> saved = cityService.addFavouriteCities(userId, names);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{userId}/{name}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long userId, @PathVariable String name) {
        cityService.deleteCity(userId, name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteCities(@PathVariable Long userId, @RequestBody List<String> names){
        cityService.deleteCities(userId, names);
        return ResponseEntity.noContent().build();
    }
}
