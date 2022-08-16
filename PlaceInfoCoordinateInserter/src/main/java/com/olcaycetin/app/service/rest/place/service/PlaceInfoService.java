package com.olcaycetin.app.service.rest.place.service;

import com.olcaycetin.app.service.rest.place.dto.PlaceInfoSaveDTO;
import com.olcaycetin.app.service.rest.place.mapper.IPlaceInfoMapper;
import org.csystem.app.weather.repository.data.dal.WeatherInfoAppHelper;
import org.springframework.stereotype.Service;



@Service
public class PlaceInfoService {
    private final IPlaceInfoMapper m_placeInfoMapper;
    private final WeatherInfoAppHelper weatherInfoAppHelper;

    public PlaceInfoService(IPlaceInfoMapper m_placeInfoMapper, WeatherInfoAppHelper weatherInfoAppHelper) {
        this.m_placeInfoMapper = m_placeInfoMapper;
        this.weatherInfoAppHelper = weatherInfoAppHelper;
    }

    public PlaceInfoSaveDTO savePlaceInfo(PlaceInfoSaveDTO placeInfoDTO)
    {
        var pi = m_placeInfoMapper.toPlaceInfo(placeInfoDTO);
        weatherInfoAppHelper.savePlaceInfo(pi);
        return  m_placeInfoMapper.toPlaceInfoDto(pi);
    }

    public void deleteByPlaceId(int id)
    {
        weatherInfoAppHelper.deletePlaceInfoById(id);
    }


}
