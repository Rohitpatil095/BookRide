package com.uber.bookingservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverLocationDto {
    private String driverId;
    private Double longitude;
    private Double latitude;
}
