package com.uber.msl.common.entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Random;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OTP extends BaseModel{

    private String sendToNumber;
    private String code;

//    private static OTP make(String mobileNumber){
//        int randomNum= new Random().nextInt(9000)+1000;
//        return OTP.builder().sendToNumber(mobileNumber).code(Integer.toString(randomNum)).build();
//    }
}
