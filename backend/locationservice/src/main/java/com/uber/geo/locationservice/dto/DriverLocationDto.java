package com.uber.geo.locationservice.dto;

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
