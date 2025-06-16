package com.uber.bookingservice.dto;

import com.uber.msl.common.constants.BookingStatus;
import com.uber.msl.common.entities.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingServiceResponseDto {

    private UUID bookingId;
    private BookingStatus bookingStatus;
    private Optional<Driver> driver;

}
