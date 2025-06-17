package com.uber.bookingservice.dto;

import com.uber.msl.common.constants.BookingStatus;
import com.uber.msl.common.entities.Driver;
import lombok.*;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookingResponseDto {
    private BookingStatus bookingStatus;
    private UUID bookingId;
    private Optional<Driver> driver;
}
