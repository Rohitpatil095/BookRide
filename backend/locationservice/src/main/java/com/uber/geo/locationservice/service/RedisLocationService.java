package com.uber.geo.locationservice.service;

import com.uber.geo.locationservice.dto.DriverLocationDto;

import java.util.List;

public interface RedisLocationService {
    public boolean saveDrivers(String driverId, Double latitude, Double longitude);
    public List<DriverLocationDto> getNearByDrivers(Double latitude, Double longitude);
}
