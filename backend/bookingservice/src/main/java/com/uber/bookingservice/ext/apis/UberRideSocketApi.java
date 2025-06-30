package com.uber.bookingservice.ext.apis;

import com.uber.bookingservice.dto.RideRequestDto;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UberRideSocketApi {
    @POST("/notify/newRide")
    Call<Boolean> sendNewRideNotificationToNearByDrivers(@Body RideRequestDto rideRequestDto);
}
