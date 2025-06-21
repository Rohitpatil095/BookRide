package com.uber.bookingservice.dto;

import com.uber.msl.common.entities.ExactLocation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingServiceRequestDto {

    private UUID passengerId;
    private ExactLocation startLocation;
    private ExactLocation endLocation;
}
