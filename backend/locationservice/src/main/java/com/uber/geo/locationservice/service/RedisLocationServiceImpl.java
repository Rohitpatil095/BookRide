package com.uber.geo.locationservice.service;

import com.uber.geo.locationservice.dto.DriverLocationDto;
import com.uber.geo.locationservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisLocationServiceImpl implements RedisLocationService {


    private final StringRedisTemplate stringRedisTemplate;

    public RedisLocationServiceImpl(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    @Override
    public boolean saveDrivers(String driverId, Double latitude, Double longitude) {
        try {

            GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();

            geoOperations.add(AppConstants.APP_GEO_NAME,new RedisGeoCommands.GeoLocation<>(driverId,new Point(latitude,longitude)));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<DriverLocationDto> getNearByDrivers(Double latitude, Double longitude) {
        try {
            GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
            Distance searchRadius = new Distance(AppConstants.SEARCH_DISTANCE, Metrics.KILOMETERS);

            Circle distanceCircle = new Circle(new Point(latitude, longitude), searchRadius);
            GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.radius(AppConstants.APP_GEO_NAME, distanceCircle);

            List<DriverLocationDto> nearByDrivers = new ArrayList<>();

            for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {

                Point point = geoOperations.position(AppConstants.APP_GEO_NAME, result.getContent().getName()).get(0);
                DriverLocationDto loc = DriverLocationDto
                        .builder()
                        .driverId(result.getContent().getName())
                        .latitude(point.getX())
                        .longitude(point.getY())
                        .build();
                nearByDrivers.add(loc);
            }
            return nearByDrivers;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
