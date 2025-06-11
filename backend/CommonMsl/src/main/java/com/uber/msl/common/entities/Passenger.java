package com.uber.msl.common.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uber.msl.common.constants.ServiceAvailableLocations;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"password","hibernateLazyInitializer","handler","photo","bookings"})
public class Passenger extends BaseModel{

    @Column(nullable = false)
    private String userName;

    private String email;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String password;

    private String photo;

    @Column(nullable = false)
    private ServiceAvailableLocations primaryLocation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "passenger")
    private Set<Booking> bookings;

}
