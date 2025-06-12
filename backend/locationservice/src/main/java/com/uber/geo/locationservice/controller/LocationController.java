package com.uber.geo.locationservice.controller;

import com.uber.geo.locationservice.dto.DriverLocationDto;
import com.uber.geo.locationservice.dto.NearbyDriverRequestDto;
import com.uber.geo.locationservice.dto.SaveDriverLocationRequestDto;
import com.uber.geo.locationservice.service.RedisLocationService;
import com.uber.geo.locationservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private RedisLocationService locationService;

    @PostMapping("/driver")
    public ResponseEntity<Boolean> saveDriverLocation(@RequestBody SaveDriverLocationRequestDto saveDriverLocationRequestDto) {
        try {
            Boolean response=locationService.saveDrivers(saveDriverLocationRequestDto.getDriverId(), saveDriverLocationRequestDto.getLatitude(), saveDriverLocationRequestDto.getLongitude());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nearby/drivers")
    public ResponseEntity<List<DriverLocationDto>> getNearbyDrivers(@RequestBody NearbyDriverRequestDto nearbyDriverRequestDto){
        try{
            List<DriverLocationDto> driverLocationDto=locationService.getNearByDrivers(nearbyDriverRequestDto.getLatitude(), nearbyDriverRequestDto.getLongitude());
            return new ResponseEntity<>(driverLocationDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
