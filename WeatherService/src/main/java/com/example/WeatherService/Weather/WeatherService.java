package com.example.WeatherService.Weather;

import com.example.WeatherService.Weather.WeatherDto.WeatherDto;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private String baseUrl;
    private String apiKey;
    private RestTemplate restTemplate;

    @Autowired
    public WeatherService(@Value("${weather.api.url}") String baseUrl,
                          @Value("${weather.api.key}") String apiKey,
                          RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    };

    public WeatherDto getCurrentWeather(String city) {
        String url = String.format("%s/current.json?key=%s&q=%s&aqi=no", baseUrl, apiKey, city);

        return restTemplate.getForObject(url, WeatherDto.class);
    }
}
