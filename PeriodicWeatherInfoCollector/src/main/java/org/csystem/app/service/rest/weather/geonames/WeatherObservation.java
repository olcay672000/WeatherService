package org.csystem.app.service.rest.weather.geonames;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class WeatherObservation{
    public double lng;
    public String observation;
    @JsonProperty("ICAO")
    public String iCAO;
    public String clouds;
    public String dewPoint;
    public String datetime;
    public String temperature;
    public int humidity;
    public String stationName;
    public String weatherCondition;
    public int windDirection;
    public String windSpeed;
    public double lat;
    public String cloudsCode;
    public int seaLevelPressure;

    @Override
    public String toString() {
        return String.format("%s, %s %s, %s", temperature, datetime, stationName, weatherCondition);
    }
}
