package com.uber.msl.common.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NamedLocation extends  BaseModel{

    @OneToOne
    private ExactLocation exactLocation;
    private  String city;
    private int cityCode;
    private String name;
    private  String country;
    private String state;
}
