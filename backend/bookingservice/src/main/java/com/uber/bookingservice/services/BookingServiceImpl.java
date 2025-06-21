package com.uber.bookingservice.services;

import com.uber.bookingservice.common.AppConstants;
import com.uber.bookingservice.dto.*;
import com.uber.bookingservice.ext.apis.LocationServiceApi;
import com.uber.bookingservice.repository.BookingRepository;
import com.uber.bookingservice.repository.DriverRepository;
import com.uber.bookingservice.repository.PassengerReposiotry;
import com.uber.msl.common.constants.BookingStatus;
import com.uber.msl.common.entities.Booking;
import com.uber.msl.common.entities.Driver;
import com.uber.msl.common.entities.Passenger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.swing.text.html.Option;
import java.awt.print.Book;
import java.util.*;

@Service
public class BookingServiceImpl implements  BookingService{

    private final PassengerReposiotry passengerReposiotry;
    private final RestTemplate restTemplate;
    private final BookingRepository bookingRepository;
    private final LocationServiceApi locationServiceApi;
    private final DriverRepository driverRepository;

    public BookingServiceImpl(PassengerReposiotry passengerReposiotry, BookingRepository bookingRepository, LocationServiceApi locationServiceApi, DriverRepository driverRepository) {
        this.passengerReposiotry = passengerReposiotry;
        this.locationServiceApi = locationServiceApi;
        this.driverRepository = driverRepository;
        this.restTemplate=new RestTemplate();
        this.bookingRepository=bookingRepository;
    }

    @Override
    public Passenger getPassengerByUUID(UUID id) {
        return null;
    }

    @Override
    public BookingServiceResponseDto createBooking(BookingServiceRequestDto bookingServiceRequestDto) {
        //find passenger and its details
        Optional<Passenger> passenger=passengerReposiotry.findById(bookingServiceRequestDto.getPassengerId());

        System.out.println("Passenger found is "+ passenger.isPresent() + "  "+ passenger.get().toString());
        //book
        Booking booking=Booking
                .builder()
                .bookingStatus(BookingStatus.PENDING)
                .price(100)
                .startLocation(bookingServiceRequestDto.getStartLocation())
                .endLocation(bookingServiceRequestDto.getEndLocation())
                .passenger(passenger.get())
                .build();

        Booking newBooking =bookingRepository.save(booking);

        //get drivers and their location from location service

//        DriverLocationDto driverLocationDto=DriverLocationDto.builder().latitude(booking.getStartLocation().getLatitude()).longitude(booking.getStartLocation().getLongitude()).build();

//        ResponseEntity<DriverLocationDto[]> driversLocation=restTemplate.postForEntity(AppConstants.locationServiceUrl+"/api/location/nearby/drivers",driverLocationDto,DriverLocationDto[].class);
//
//        if(driversLocation.getStatusCode().is2xxSuccessful() && driversLocation.getBody()!=null){
//            List<DriverLocationDto> drivers= Arrays.asList(driversLocation.getBody());
//
//            for(DriverLocationDto location:drivers){
//                System.out.println("Driver is "+ location.getDriverId() + " with location " + location.getLatitude() + " " + location.getLongitude());
//            }
//        }

        NearbyDriverRequestDto nearbyDriverRequestDto=NearbyDriverRequestDto.builder()
                .latitude(booking.getStartLocation().getLatitude())
                .longitude(booking.getStartLocation().getLongitude())
                .build();

        processNearByDriverAsync(nearbyDriverRequestDto);
        //return
        return BookingServiceResponseDto.builder()
                .bookingId(newBooking.getId())
                .bookingStatus(newBooking.getBookingStatus())
//                .driver(Optional.of(newBooking.getDriver()))
                .build();
    }

    @Override
    public UpdateBookingResponseDto updateBooking(UpdateBookingRequestDto updateBookingRequestDto, UUID bookingId) {
        //get driver
        //update booking with driver status

        Optional<Driver> driver= driverRepository.findById(updateBookingRequestDto.getDriverId());

        bookingRepository.updateBookingStatusAndDriverById(bookingId,updateBookingRequestDto.getStatus(),driver.get());

        Optional<Booking> booking =bookingRepository.findById(bookingId);
            return UpdateBookingResponseDto.builder()
                    .bookingId(bookingId)
                    .bookingStatus(booking.get().getBookingStatus())
                    .driver(Optional.ofNullable(booking.get().getDriver()))
                    .build();
    }

    private void processNearByDriverAsync(NearbyDriverRequestDto nearbyDriverRequestDto){
        Call<DriverLocationDto[]> nearByDriversCall=locationServiceApi.getNearbyDrivers(nearbyDriverRequestDto);

        nearByDriversCall.enqueue(new Callback<DriverLocationDto[]>() {
            @Override
            public void onResponse(Call<DriverLocationDto[]> call, Response<DriverLocationDto[]> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<DriverLocationDto> drivers= Arrays.asList(response.body());

                    for(DriverLocationDto location:drivers){
                        System.out.println("Driver is "+ location.getDriverId() + " with location " + location.getLatitude() + " " + location.getLongitude());
                    }
                }else{
                    System.out.println("Request failed to retrive nearbydrivers.. ");
                }
            }

            @Override
            public void onFailure(Call<DriverLocationDto[]> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
