package com.example.ProfileService.profile;

import com.example.ProfileService.profile.exception.CityAlreadyExistsException;
import com.example.ProfileService.profile.exception.CityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getFavouriteCities(Long userId) {
        return cityRepository.findByUserId(userId)
                .orElse(Collections.emptyList());
    }

    public City addFavouriteCity(Long userId, String name){
        if(cityRepository.findByUserIdAndName(userId, name).isPresent()){
            throw new CityAlreadyExistsException(name);
        }
        return cityRepository.save(new City(userId, name));
    }

    // If any problem occurs during any saving then all changed are discarded
    @Transactional
    public List<City> addFavouriteCities(Long userId, List<String> names) {
        List<City> cities = new ArrayList<>();

        for(String name : names){
            cities.add(new City(userId, name));
            if(cityRepository.findByUserIdAndName(userId, name).isPresent()){
                throw new CityAlreadyExistsException(name);
            }
        }
        return cityRepository.saveAll(cities);
    }

    @Transactional
    public void deleteCity(Long userId, String name) {
        int rows_del = cityRepository.deleteByUserIdAndName(userId, name);
        if(rows_del == 0){
            throw new CityNotFoundException(name);
        }
    }

    @Transactional
    public void deleteCities(Long userId, List<String> names){
        for(String name : names){
            int rows_del = cityRepository.deleteByUserIdAndName(userId, name);

            if(rows_del == 0){
                throw new CityNotFoundException(name);
            }
        }
    }
}
