package com.uber.bookingservice.repository;

import com.uber.msl.common.constants.BookingStatus;
import com.uber.msl.common.entities.Booking;
import com.uber.msl.common.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;


@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("Update booking b set b.bookingStatus= :status, b.driver= :driver where b.id= :id ")
    public void updateBookingStatusAndDriverById(@Param("id") UUID id, @Param("status") BookingStatus status, @Param("driver")Driver driver);
}
