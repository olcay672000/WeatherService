package org.csystem.app.weather.repository.backup.data.dal;

import org.csystem.app.weather.repository.backup.data.entity.WeatherInfo;
import org.csystem.app.weather.repository.backup.data.repository.IPlaceInfoRepository;
import org.csystem.app.weather.repository.backup.data.repository.IWeatherInfoRepository;
import org.csystem.app.weather.repository.backup.data.entity.PlaceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherInfoBackupAppHelper {

    private final IPlaceInfoRepository m_placeInfoRepository;
    private final IWeatherInfoRepository m_weatherInfoRepository;

    public WeatherInfoBackupAppHelper(IPlaceInfoRepository placeInfoRepository, IWeatherInfoRepository weatherInfoRepository)
    {
        m_placeInfoRepository = placeInfoRepository;
        m_weatherInfoRepository = weatherInfoRepository;
    }


    public Iterable<PlaceInfo> findAllPlaces()
    {
        return m_placeInfoRepository.findAll();
    }

    public void saveWeatherInfo(WeatherInfo weatherInfo)
    {
        m_weatherInfoRepository.save(weatherInfo);
    }

    public void saveAllWeatherInfo(Iterable<WeatherInfo> weatherInfos)
    {
        m_weatherInfoRepository.saveAll(weatherInfos);
    }

    public Iterable<WeatherInfo> findByPlaceNameAndYearAndMonth(String placeName, int year, int month)
    {
        return m_weatherInfoRepository.findByPlaceNameAndYearAndMonth(placeName, year, month);
    }

    public Iterable<WeatherInfo> findByPlaceNameAndMonth(String placeName, int month)
    {
        return m_weatherInfoRepository.findByPlaceNameAndMonth(placeName, month);
    }

}
