package com.uber.bookingservice.services;

import com.uber.bookingservice.dto.BookingServiceRequestDto;
import com.uber.bookingservice.dto.BookingServiceResponseDto;
import com.uber.bookingservice.dto.UpdateBookingRequestDto;
import com.uber.bookingservice.dto.UpdateBookingResponseDto;
import com.uber.bookingservice.repository.PassengerReposiotry;
import com.uber.msl.common.entities.Passenger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public interface BookingService {
    public Passenger getPassengerByUUID(UUID id);
    public BookingServiceResponseDto createBooking(BookingServiceRequestDto bookingServiceRequestDto);
    public UpdateBookingResponseDto updateBooking(UpdateBookingRequestDto updateBookingRequestDto, UUID id);
}
