package com.uber.bookingservice.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RideRequestDto {
    private UUID passengetId;
//    private ExactLocation startLocation;
//    private ExactLocation endLocation;
    private List<UUID> driversIds;
}
