package com.uber.bookingservice.controller;

import com.uber.bookingservice.dto.BookingServiceRequestDto;
import com.uber.bookingservice.dto.BookingServiceResponseDto;
import com.uber.bookingservice.dto.UpdateBookingRequestDto;
import com.uber.bookingservice.dto.UpdateBookingResponseDto;
import com.uber.bookingservice.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService service){
        this.bookingService=service;
    }


    @GetMapping("/createBooking")
    public void createBookingSimple(){
        System.out.println("sd");
    }


    @PostMapping("/createBooking")
    public ResponseEntity<BookingServiceResponseDto> createBooking(@RequestBody BookingServiceRequestDto bookingServiceRequestDto){
        return new ResponseEntity<>(bookingService.createBooking(bookingServiceRequestDto),HttpStatus.CREATED);
    }

    @PatchMapping("{bookingId}")
    public ResponseEntity<UpdateBookingResponseDto> updateBooking(@RequestBody UpdateBookingRequestDto updateBookingRequestDto, @PathVariable UUID bookingId){
        return new ResponseEntity<>(bookingService.updateBooking(updateBookingRequestDto,bookingId),HttpStatus.OK);

    }

}
