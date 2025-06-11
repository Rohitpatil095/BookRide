package com.uber.msl.common.entities;

import com.uber.msl.common.constants.CarType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Car extends  BaseModel{

    private String carNumberPlate;
    private String passing;
    private boolean verified;
    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    private CarType cartype;

    @ManyToOne
    private Colour color;

    @OneToOne
    private Driver driver;

}
