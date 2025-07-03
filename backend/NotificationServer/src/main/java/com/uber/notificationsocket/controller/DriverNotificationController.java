package com.uber.notificationsocket.controller;

import com.uber.notificationsocket.dto.RideRequestDto;
import com.uber.notificationsocket.dto.RideResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//@RestController("/api/notification")
@RestController
public class DriverNotificationController {

    private SimpMessagingTemplate simpMessagingTemplate;

    public DriverNotificationController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/notify/newRide")
    public ResponseEntity<Boolean> newRideRequest(@RequestBody RideRequestDto rideRequestDto){
        sendDriverNewRideLocation(rideRequestDto);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    public void sendDriverNewRideLocation(RideRequestDto requestDto){
        System.out.println("Ride location ");
        simpMessagingTemplate.convertAndSend("/topic/rideRequest",requestDto);
    }

    @MessageMapping("/rideResponse/{userId}")
    public synchronized void rideResponseHadler(@DestinationVariable String userId, RideResponseDto rideResponseDto){
        System.out.println("i am hitted..");
        System.out.println("ride response id "+ rideResponseDto.getRideResponse() + " driverId is " + rideResponseDto.getDriverId());
    }

}
