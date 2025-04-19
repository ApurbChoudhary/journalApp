package com.prince.journalApp.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WeatherResponse {

    private Current current;
    @Data
    public class Current {
        private int temperature;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        private int feelslike;
        @JsonProperty("uv_index")
        private int uvIndex;
        private int visibility;
        @JsonProperty("is_day")
        private String isDay;
    }

}
