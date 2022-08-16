package com.olcaycetin.app.service.rest.place.controller;



import com.olcaycetin.app.service.rest.place.dto.PlaceInfoSaveDTO;
import com.olcaycetin.app.service.rest.place.service.PlaceInfoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/weather/info")
public class PlaceInfoInserterController {
    public final PlaceInfoService m_placeInfoService;

    public PlaceInfoInserterController(PlaceInfoService placeInfoService) {
        m_placeInfoService = placeInfoService;
    }


    @PostMapping("save")
    public PlaceInfoSaveDTO savePlaceInfo(@RequestBody PlaceInfoSaveDTO placeInfoSaveDTO)
    {
        return m_placeInfoService.savePlaceInfo(placeInfoSaveDTO);
    }

    @PostMapping("delete")
    public void deleteByPlaceId(@RequestParam(name = "id") int id)
    {
        m_placeInfoService.deleteByPlaceId(id);
    }



}
