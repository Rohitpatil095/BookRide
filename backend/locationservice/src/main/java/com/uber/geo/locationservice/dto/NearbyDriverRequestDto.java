package com.uber.geo.locationservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearbyDriverRequestDto {
    private Double longitude;
    private Double latitude;
}
