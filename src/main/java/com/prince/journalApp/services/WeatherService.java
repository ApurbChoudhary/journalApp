package com.prince.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prince.journalApp.entities.WeatherResponse;

@Service
public class WeatherService {

    private static final String api_key = "1fc2e7e1b693cf9a64a2363b148e287e";

    private static final String api_url = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY_NAME";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String url = api_url.replace("API_KEY", api_key).replace("CITY_NAME", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }

}
