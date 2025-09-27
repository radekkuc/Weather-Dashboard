package com.example.ProfileService.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    //@GerMapping
    // Add a general one for looking for a single city without need of saving it

    @GetMapping("/favourite/{userId}")
    public ResponseEntity<List<City>> getFavouriteCities(@PathVariable Long userId) {
        List<City> favourites = cityService.getFavouriteCities(userId);

        if(favourites.isEmpty()){
            // Build needed here, without it, it is not a full response entity
           return ResponseEntity.noContent().build();
        }
        else {
            // There we do not need build because we return favourites
            return ResponseEntity.ok(favourites);
        }
    }

    @PostMapping("/{userId}/favourite")
    public ResponseEntity<City> addFavouriteCity(@PathVariable Long userId, @RequestBody String name) {
        City saved = cityService.addFavouriteCity(new City(userId, name));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/{userId}/favourites")
    public ResponseEntity<List<City>> addFavouriteCities(@PathVariable Long userId, @RequestBody List<String> names) {
        List<City> saved = cityService.addFavouriteCities(userId, names);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{userId}/{name}")
    public void deleteCity(@PathVariable Long userId, @PathVariable String name) {
        cityService.deleteCity(userId, name);
    }

    @DeleteMapping("/{userId}")
    public void massDeleteCities(@PathVariable Long userId, @RequestBody List<String> names){
        
    }


}
