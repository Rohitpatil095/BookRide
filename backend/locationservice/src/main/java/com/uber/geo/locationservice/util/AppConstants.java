package com.uber.geo.locationservice.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class AppConstants {

    public static  String APP_GEO_NAME="drivers";
    public  static int SEARCH_DISTANCE=50;

}
