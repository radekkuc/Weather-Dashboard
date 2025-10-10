package com.example.ProfileService.profile;

import com.example.ProfileService.profile.weatherDto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private RestTemplate restTemplate;
    private String apiUrl;

    @Autowired
    public WeatherService(@Value("${weather.service.url}") String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    public WeatherDto getCurrentWeather(String city) {
        String url = String.format("%s/weather/%s", apiUrl, city);
        return restTemplate.getForObject(url, WeatherDto.class);
    }
}
