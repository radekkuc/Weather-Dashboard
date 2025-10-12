package com.example.WeatherService.weather;

import com.example.WeatherService.weather.exception.CityNotFoundException;
import com.example.WeatherService.weather.exception.GenericException;
import com.example.WeatherService.weather.weatherDto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
        try {
            return restTemplate.getForObject(url, WeatherDto.class);
        }
        catch(HttpClientErrorException.BadRequest | HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException(city);
        }
        catch(Exception e) {
            throw new GenericException();
        }
    }
}
