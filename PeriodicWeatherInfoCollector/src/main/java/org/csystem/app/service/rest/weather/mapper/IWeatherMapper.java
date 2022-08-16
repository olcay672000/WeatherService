package org.csystem.app.service.rest.weather.mapper;


import org.csystem.app.service.rest.weather.geonames.WeatherObservation;
import org.csystem.app.weather.repository.data.entity.WeatherInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "PlaceInfoMapperImpl", componentModel = "spring")
public interface IWeatherMapper {
    @Mapping(source = "datetime", target = "datetime", dateFormat = "yyyy-MM-dd kk:mm:ss")
    WeatherInfo toWeatherInfo(WeatherObservation weatherObservation);

}
