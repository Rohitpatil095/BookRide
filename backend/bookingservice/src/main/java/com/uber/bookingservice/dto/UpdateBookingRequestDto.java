package com.uber.bookingservice.dto;

import com.uber.msl.common.constants.BookingStatus;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateBookingRequestDto {

    private BookingStatus status;
    private UUID driverId;

}
