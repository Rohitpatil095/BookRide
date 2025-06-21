package com.uber.bookingservice.ext.apis;

import com.uber.bookingservice.common.AppConstants;
import com.uber.bookingservice.dto.DriverLocationDto;
import com.uber.bookingservice.dto.NearbyDriverRequestDto;
import org.springframework.context.annotation.Configuration;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationServiceApi {

    @POST(AppConstants.LOCATION_SERVICE_API_ROOT+AppConstants.LOCATION_SERVICE_GET_NEARBY_DRIVERS)
    Call<DriverLocationDto[]> getNearbyDrivers(@Body NearbyDriverRequestDto nearbyDriverRequestDto);
}
