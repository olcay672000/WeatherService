package org.csystem.app.service.weather.daily.service;

import org.csystem.app.service.weather.daily.dto.WeatherObservations;
import org.csystem.app.service.weather.daily.mapper.IWeatherInfoMapper;
import org.csystem.app.weather.repository.data.dal.WeatherInfoAppHelper;
import org.csystem.app.weather.repository.data.entity.SimpleView;
import org.csystem.app.weather.repository.data.entity.WeatherInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WeatherInfoDailyServiceAppService {
    private final WeatherInfoAppHelper m_weatherInfoAppHelper;
    private final IWeatherInfoMapper m_weatherInfoMapper;

    public WeatherInfoDailyServiceAppService(WeatherInfoAppHelper weatherInfoAppHelper, IWeatherInfoMapper weatherInfoMapper)
    {
        m_weatherInfoAppHelper = weatherInfoAppHelper;
        m_weatherInfoMapper = weatherInfoMapper;
    }

    public WeatherObservations findWeatherInfoByPlaceName(String placeName)
    {
        var wis = StreamSupport.stream(m_weatherInfoAppHelper.findByPlaceName(placeName).spliterator(), false)
                .map(m_weatherInfoMapper::toWeatherObservation).collect(Collectors.toList());

        return m_weatherInfoMapper.toWeatherObservations(wis);
    }

    public WeatherObservations findWeatherInfoByPlaceNameAndHourAndMinute(String placeName, int hour, int minute)
    {
        var wis = StreamSupport.stream(m_weatherInfoAppHelper.findByPlaceNameAndHourAndMinute(placeName, hour, minute).spliterator(), false)
                .map(m_weatherInfoMapper::toWeatherObservation).collect(Collectors.toList());

        return m_weatherInfoMapper.toWeatherObservations(wis);
    }

}
