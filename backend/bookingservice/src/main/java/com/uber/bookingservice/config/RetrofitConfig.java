package com.uber.bookingservice.config;

import com.netflix.discovery.EurekaClient;
import com.uber.bookingservice.common.AppConstants;
import com.uber.bookingservice.ext.apis.LocationServiceApi;
import com.uber.bookingservice.ext.apis.UberRideSocketApi;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    private EurekaClient eurekaClient;

    public RetrofitConfig(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    private String getServiceURL(String serviceName){
        return eurekaClient.getNextServerFromEureka(serviceName,false).getHomePageUrl();
    }

    @Singleton
    public Retrofit retrofit(){
        return new Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Bean
    public LocationServiceApi locationServiceApi(){
        return new Retrofit.Builder()
                .baseUrl(getServiceURL(AppConstants.LOCATION_SERVICE_NAME))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder().build())
                .build()
                .create(LocationServiceApi.class);
    }

    @Bean
    public UberRideSocketApi uberRideSocketApi(){
        return new Retrofit.Builder()
                .baseUrl(getServiceURL(AppConstants.NOTIFICATION_SOCKET_SERVER))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder().build())
                .build()
                .create(UberRideSocketApi.class);
    }

}
