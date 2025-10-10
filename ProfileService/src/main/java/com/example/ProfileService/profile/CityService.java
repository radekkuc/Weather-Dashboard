package com.example.ProfileService.profile;

import com.example.ProfileService.profile.weatherDto.WeatherDto;
import com.example.ProfileService.profile.exception.CityAlreadyExistsException;
import com.example.ProfileService.profile.exception.CityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final WeatherService weatherService;

    @Autowired
    public CityService(CityRepository cityRepository, WeatherService weatherService) {
        this.cityRepository = cityRepository;
        this.weatherService = weatherService;
    }

    public List<City> getFavouriteCities(Long userId) {
        return cityRepository.findByUserId(userId);
    }

    @Transactional
    public City addFavouriteCity(Long userId, String name){
        if(cityRepository.findByUserIdAndName(userId, name).isPresent()){
            throw new CityAlreadyExistsException(name);
        }

        WeatherDto dto = weatherService.getCurrentWeather(name);
        City city = Mapper.dtoToCity(userId, dto);
        return cityRepository.save(city);
    }

    // If any problem occurs during any saving then all changed are discarded
    @Transactional
    public List<City> addFavouriteCities(Long userId, List<String> names) {
        List<City> cities = new ArrayList<>();

        for(String name : names){
            if(cityRepository.findByUserIdAndName(userId, name).isPresent()){
                throw new CityAlreadyExistsException(name);
            }
            WeatherDto dto = weatherService.getCurrentWeather(name);
            City city = Mapper.dtoToCity(userId, dto);
            cities.add(city);
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
