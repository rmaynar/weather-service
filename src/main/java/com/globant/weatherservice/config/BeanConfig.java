package com.globant.weatherservice.config;

import com.globant.weatherservice.service.WeatherDataService;
import com.globant.weatherservice.service.WeatherDataServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public WeatherDataService getWeatherDataService(){
        return new WeatherDataServiceImpl();
    }
}
