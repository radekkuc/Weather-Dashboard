package com.example.profileService.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class CityController {
    private final CityService cityService;
    private final WeatherService weatherService;

    public CityController(CityService cityService, WeatherService weatherService) {
        this.cityService = cityService;
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{city}")
    ResponseEntity<City> getCurrentWeather(@PathVariable String city) {
        return ResponseEntity.ok(Mapper.dtoToCity(null, weatherService.getCurrentWeather(city)));
    }

    @GetMapping("/favourite")
    public ResponseEntity<List<City>> getFavouriteCities(Authentication auth) {
        long userId = Mapper.safeStringToLong(auth.getName());
        List<City> favourites = cityService.getFavouriteCities(userId);
        return ResponseEntity.ok(favourites);
    }

    @PostMapping("/favourite")
    public ResponseEntity<City> addFavouriteCity(Authentication auth, @RequestBody CityRequest request) {
        String name = request.getName();
        Long userId = Mapper.safeStringToLong(auth.getName());
        City saved = cityService.addFavouriteCity(userId, name);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/favourites")
    public ResponseEntity<List<City>> addFavouriteCities(Authentication auth,
                                                         @RequestBody List<String> names) {
        Long userId = Mapper.safeStringToLong(auth.getName());
        List<City> saved = cityService.addFavouriteCities(userId, names);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteCity(Authentication auth, @PathVariable String name) {
        Long userId = Mapper.safeStringToLong(auth.getName());
        cityService.deleteCity(userId, name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCities(Authentication auth, @RequestBody List<String> names){
        Long userId = Mapper.safeStringToLong(auth.getName());
        cityService.deleteCities(userId, names);
        return ResponseEntity.noContent().build();
    }
}
