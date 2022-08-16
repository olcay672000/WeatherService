package org.csystem.app.service.rest.weather.scheduler;


import org.csystem.app.service.rest.weather.mapper.IWeatherMapper;
import org.csystem.app.weather.repository.backup.data.dal.WeatherInfoBackupAppHelper;
import org.csystem.app.weather.repository.backup.data.entity.PlaceInfo;
import org.csystem.app.weather.repository.data.dal.WeatherInfoAppHelper;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
@EnableScheduling
public class  WeatherInfoScheduler {
    private final WeatherInfoAppHelper m_weatherInfoAppHelper;
    private final WeatherInfoBackupAppHelper m_weatherInfoBackupAppHelper;
    private final IWeatherMapper m_weatherMapper;

    public WeatherInfoScheduler(WeatherInfoAppHelper weatherInfoAppHelper, WeatherInfoBackupAppHelper weatherInfoBackupAppHelper, IWeatherMapper weatherMapper)
    {
        m_weatherInfoAppHelper = weatherInfoAppHelper;
        m_weatherInfoBackupAppHelper = weatherInfoBackupAppHelper;
        m_weatherMapper = weatherMapper;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void scheulerCallBack()
    {
        var places = m_weatherInfoBackupAppHelper.findAllPlaces();

        places.forEach(this::schedulerCallback);

        m_weatherInfoAppHelper.deleteAllWeatherInfo();
    }

    private void schedulerCallback(PlaceInfo pi)
    {
        var wis = m_weatherInfoAppHelper.findByPlaceName(pi.placeName);

        var wil = StreamSupport.stream(wis.spliterator(), false).map(m_weatherMapper::toWeatherInfoBackup) // böyle save yaparsan wi'nin place info'su null olur.
                                                             // bunun yerine peek kullandık.// map(wi -> {wi.placeInfo = pi; return wi;}) // burada return'u yazmaksak placeInfo döner.
                                                              .peek(wi -> wi.placeInfo = pi)
                                                              .collect(Collectors.toList());

        m_weatherInfoBackupAppHelper.saveAllWeatherInfo(wil);
    }


}
