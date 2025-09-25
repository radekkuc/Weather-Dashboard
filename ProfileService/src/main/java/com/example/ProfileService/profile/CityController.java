package com.example.ProfileService.profile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @GetMapping("/favourite")
    ResponseEntity<List<City>> getFavouriteCities(City city){

    }



}
