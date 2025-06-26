package com.uber.notificationsocket.dto;

import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RideResponseDto {
    private Boolean rideResponse;
    private UUID driverId;
}
