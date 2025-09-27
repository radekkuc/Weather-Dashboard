package com.example.ProfileService.profile;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                .orElseThrow(() -> new RuntimeException("Error with getFavouriteCities"));
    }

    public City addFavouriteCity(City city){
        if(cityRepository.findByUserId(city.getId()).isPresent()){
            throw new RuntimeException("Error with addFavouriteCity");
        }
        return cityRepository.save(city);
    }

    // If any problem occurs during any saving then all changed are discarded
    @Transactional
    public List<City> addFavouriteCities(Long userId, List<String> names) {
        List<City> cities = new ArrayList<>();

        for(String name : names){
            cities.add(new City(userId, name));
            if(cityRepository.findByUserIdAndName(userId, name).isPresent()){
                throw new RuntimeException("Error with addFavouriteCities");
            }
        }
        return cityRepository.saveAll(cities);
    }

    @Transactional
    public void deleteCity(Long userId, String name) {
        int rows_del = cityRepository.deleteByUserIdAndName(userId, name);
        if(rows_del == 0){
            throw new RuntimeException("Error with deleteCity");
        }
    }

    @Transactional
    public void deleteCities(Long userId, List<String> names){
        for(String name : names){
            int rows_del = cityRepository.deleteByUserIdAndName(userId, name);

            if(rows_del == 0){
                throw new RuntimeException("Error with deleteCities");
            }
        }
    }
}
