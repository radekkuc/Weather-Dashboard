package com.example.profileService.profile;

import com.example.profileService.profile.weatherDto.WeatherDto;

public class Mapper {

    public static City dtoToCity(Long userId, WeatherDto dto) {
        City city = new City();
        city.setUserId(userId);
        city.setName(dto.getLocation().getName());
        city.setCountry(dto.getLocation().getCountry());
        city.setLast_updated(dto.getCurrent().getLast_updated());
        city.setTemp_c(dto.getCurrent().getTemp_c());
        city.setTemp_f(dto.getCurrent().getTemp_f());
        city.setCondition(dto.getCurrent().getCondition().getText());
        city.setPressure_mb(dto.getCurrent().getPressure_mb());
        return city;
    }

    public static void updateCityFields(City city, WeatherDto dto) {
        city.setLast_updated(dto.getCurrent().getLast_updated());
        city.setTemp_c(dto.getCurrent().getTemp_c());
        city.setTemp_f(dto.getCurrent().getTemp_f());
        city.setCondition(dto.getCurrent().getCondition().getText());
        city.setPressure_mb(dto.getCurrent().getPressure_mb());
    }
}
