package org.csystem.app.service.weather.history.mapper;

import org.csystem.app.service.weather.history.dto.WeatherObservation;
import org.csystem.app.service.weather.history.dto.WeatherObservations;
import org.csystem.app.weather.repository.backup.data.entity.WeatherInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(implementationName = "WeatherInfoMapperImpl", componentModel = "spring")
public interface IWeatherInfoMapper {

    @Mapping(source = "datetime", target = "datetime", dateFormat = "yyyy-MM-dd kk:mm:ss")
    WeatherObservation toWeatherObservation(WeatherInfo weatherInfo);

    default WeatherObservations toWeatherObservations(List<WeatherObservation> weatherObservations)
    {
        if (weatherObservations == null)
            return null;

        var dto = new WeatherObservations();

        dto.weatherObservations = weatherObservations;

        return dto;
    }

}
