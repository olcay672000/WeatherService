package org.csystem.app.service.rest.weather.geonames.scheduler;


import org.csystem.app.service.rest.weather.geonames.WeatherObservations;
import org.csystem.app.service.rest.weather.mapper.IWeatherMapper;
import org.csystem.app.weather.repository.data.dal.WeatherInfoAppHelper;
import org.csystem.app.weather.repository.data.entity.PlaceInfo;
import org.csystem.app.weather.repository.data.entity.WeatherInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class WeatherInfoScheduler {
    private final WeatherInfoAppHelper m_periodicWeatherInfoHelper;
    private final RestTemplate m_restTemplate;
    private final IWeatherMapper m_weatherMapper;

    @Value("${geonames.url}")
    private String m_urlTemplate;

    public WeatherInfoScheduler(WeatherInfoAppHelper periodicWeatherInfoHelper, RestTemplate restTemplate, IWeatherMapper weatherMapper)
    {
        m_periodicWeatherInfoHelper = periodicWeatherInfoHelper;
        m_restTemplate = restTemplate;
        m_weatherMapper = weatherMapper;
    }

    @Scheduled(cron = "*/59 * * * * *")
    public void scheulerCallBack()
    {
        System.out.println("scheulerCallBack");
        var places = m_periodicWeatherInfoHelper.findAllPlaces();

        places.forEach(this::schedulerCallback);


    }

    private void schedulerCallback(PlaceInfo placeInfo)
    {

        var url = String.format(m_urlTemplate, placeInfo.north + 0.7, placeInfo.south - 0.7, placeInfo.east + 0.7, placeInfo.west - 0.7);
        var wis = m_restTemplate.getForObject(url, WeatherObservations.class);


        wis.weatherObservations.stream().map(m_weatherMapper::toWeatherInfo).forEach(wi -> weatherInfoCallback(wi, placeInfo));

    }

    private void weatherInfoCallback(WeatherInfo wi, PlaceInfo pi)
    {
        //var wi = m_weatherMapper.toWeatherInfo(wo);

        wi.placeInfo = pi;

        if (m_periodicWeatherInfoHelper.existsWeatherInfoByPlaceInfoAndDatetime(pi, wi.datetime, wi.stationName))
            return;

      //  pi.weatherInfos.add(wi);

        m_periodicWeatherInfoHelper.saveWeatherInfo(wi);
    }
}
