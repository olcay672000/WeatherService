package org.csystem.app.weather.repository.backup.data.repository;

import org.csystem.app.weather.repository.backup.data.entity.WeatherInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("weatherInfoBackupRepository")
public interface IWeatherInfoRepository extends CrudRepository<WeatherInfo, Long> {

    @Query(value = "select place_info_id, weather_information_id, lng, observation, iCAO, clouds, dew_point, clouds_code, " +
            "date_time, temperature, humidity, station_name, weather_condition, wind_direction, wind_speed, " +
            "lat, weather_condition_code, sea_level_pressure, hecto_pasc_alimeter " +
            "from weather_information inner join place_info USING(place_info_id) " +
            "where place_name = :placeName and YEAR(weather_information.date_time) = :year " +
            "and MONTH(weather_information.date_time) = :month",
            nativeQuery = true)
    Iterable<WeatherInfo> findByPlaceNameAndYearAndMonth(@Param("placeName") String placeName,
                                                          @Param("year") int year,
                                                          @Param("month") int month);

    @Query(value = "select place_info_id, weather_information_id, lng, observation, iCAO, clouds, dew_point, clouds_code, " +
            "date_time, temperature, humidity, station_name, weather_condition, wind_direction, wind_speed, " +
            "lat, weather_condition_code, sea_level_pressure, hecto_pasc_alimeter " +
            "from weather_information inner join place_info USING(place_info_id) " +
            "where place_name = :placeName and MONTH(weather_information.date_time) = :month", nativeQuery = true)
    Iterable<WeatherInfo> findByPlaceNameAndMonth(@Param("placeName") String placeName,
                                                  @Param("month") int month);
}
