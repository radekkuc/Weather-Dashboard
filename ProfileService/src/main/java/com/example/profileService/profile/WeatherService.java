package com.example.profileService.profile;

import com.example.profileService.profile.exception.CityNotFoundException;
import com.example.profileService.profile.weatherDto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    @Autowired
    public WeatherService(@Value("${weather.service.url}") String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    public WeatherDto getCurrentWeather(String city) {
        String url = String.format("%s/weather/%s", apiUrl, city);
        try {
            return restTemplate.getForObject(url, WeatherDto.class);
        }
        catch(HttpClientErrorException.BadRequest | HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException(city);
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
