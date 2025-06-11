package com.uber.msl.common.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExactLocation extends  BaseModel{

    private Double latitude;
    private Double longitude;
}
