package com.uber.msl.common.entities;

import com.uber.msl.common.constants.ServiceAvailableLocations;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Driver extends  BaseModel{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mobile;

    private String email;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceAvailableLocations primaryLocation;

    @OneToMany(mappedBy = "driver")
    @Fetch(FetchMode.SUBSELECT)
    private Set<Booking> bookings;

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    private  Car car;
}
