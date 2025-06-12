package com.uber.geo.locationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SaveDriverLocationRequestDto {

    private String driverId;
    private Double longitude;
    private Double latitude;

    SaveDriverLocationRequestDto(Builder builder){
        this.driverId=builder.driverId;
        this.latitude=builder.latitude;
        this.longitude=builder.longitude;
    }


    // builder pattern
    public static class Builder{
        private String driverId;
        private Double longitude;
        private Double latitude;

        Builder(String driverId,Double longitude,Double latitude){
            this.driverId=driverId;
            this.latitude=latitude;
            this.longitude=longitude;
        }

        public Builder setDriverId(String driverId){
            this.driverId=driverId;
            return this;
        }
        public Builder setLongitude(Double longitude){
            this.longitude=longitude;
            return this;
        }
        public Builder setLatitude(Double latitude){
            this.latitude=latitude;
            return this;
        }

        public SaveDriverLocationRequestDto build(){
            return new SaveDriverLocationRequestDto(this);
        }
    }


}
