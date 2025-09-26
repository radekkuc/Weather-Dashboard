package com.example.ProfileService.profile;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getFavouriteCities(Long userId) {
        return cityRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Error with getFavouriteCities"));
    }

    public City addFavouriteCity(City city){
        return cityRepository.save(city);
    }

    // If any problem occurs during any saving then all changed are discarded
    @Transactional
    public List<City> addFavouriteCities(List<City> cities) {
        return cityRepository.saveAll(cities);
    }
}
