package com.example.ProfileService.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    private final CityService cityService;

    CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/favourite/{userId}")
    ResponseEntity<List<City>> getFavouriteCities(@PathVariable Long userId) {
        List<City> favourites = cityService.getFavouriteCities(userId);

        if(favourites.isEmpty()){
            // Build needed here, without it it is not a full response entity
           return ResponseEntity.noContent().build();
        }
        else {
            // There we do not need build because we return favourites
            return ResponseEntity.ok(favourites);
        }
    }

    @PostMapping("/favourite")
    ResponseEntity<City> addFavouriteCity(@RequestBody City city) {
        City saved = cityService.addFavouriteCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }



}
