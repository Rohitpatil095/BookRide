package com.uber.bookingservice.repository;

import com.uber.msl.common.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassengerReposiotry extends JpaRepository<Passenger,UUID> {
}
