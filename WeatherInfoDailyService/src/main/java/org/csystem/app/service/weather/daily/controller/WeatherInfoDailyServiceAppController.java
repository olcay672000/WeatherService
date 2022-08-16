package org.csystem.app.service.weather.daily.controller;

import org.csystem.app.service.weather.daily.dto.WeatherObservations;
import org.csystem.app.service.weather.daily.service.WeatherInfoDailyServiceAppService;
import org.csystem.app.weather.repository.data.entity.SimpleView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/weather/info")
public class WeatherInfoDailyServiceAppController {
    private final WeatherInfoDailyServiceAppService m_weatherInfoDailyServiceAppService;

    public WeatherInfoDailyServiceAppController(WeatherInfoDailyServiceAppService weatherInfoDailyServiceAppService)
    {
        m_weatherInfoDailyServiceAppService = weatherInfoDailyServiceAppService;
    }

    @GetMapping("place")
    public WeatherObservations findByPlaceName(@RequestParam(name = "p") String placeName)
    {
        return m_weatherInfoDailyServiceAppService.findWeatherInfoByPlaceName(placeName);
    }

    @GetMapping("place/hm")
    public WeatherObservations findByPlaceNameAndHourAndMinute(@RequestParam(name = "p") String placeName,
                                                               @RequestParam(name = "h") int hour,
                                                               @RequestParam(name = "m") int minute)
    {
        return m_weatherInfoDailyServiceAppService.findWeatherInfoByPlaceNameAndHourAndMinute(placeName, hour, minute);
    }

}
