package org.csystem.app.service.rest.weather.mapper;


import org.csystem.app.weather.repository.backup.data.entity.WeatherInfo;
import org.mapstruct.Mapper;

@Mapper(implementationName = "WeatherMapperImpl", componentModel = "spring")
public interface IWeatherMapper {
    WeatherInfo toWeatherInfoBackup(org.csystem.app.weather.repository.data.entity.WeatherInfo weatherInfo);
}
