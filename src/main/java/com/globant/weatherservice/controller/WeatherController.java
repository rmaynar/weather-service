package com.globant.weatherservice.controller;

import com.globant.weatherservice.model.WeatherData;
import com.globant.weatherservice.service.WeatherDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    @PostMapping("/weather")
    public WeatherData saveWeatherData(@RequestBody WeatherData weatherData, HttpServletResponse response){

        //Check if previously exists
        log.info("Checking for previous data");
        if(weatherData.getId() != 0 && weatherDataService.findById(weatherData.getId()).isPresent()){
            log.error("Weather data already exists!");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        response.setStatus(HttpStatus.CREATED.value());
        return weatherDataService.save(weatherData);
    }

    @GetMapping("/weather")
    public List<WeatherData> getAllWeatherData(@RequestParam(required = false) String date){
        log.info("Returning all weather data");
        return weatherDataService.findAll();
    }

    /*@GetMapping("/weather")
    public List<WeatherData> getWeatherDataByDate(@RequestParam String date){
        log.info("Returning all weather data filtered by date: " + date);
        return Collections.emptyList();
    }*/

    @GetMapping("/weather/temp")
    public Object getMaxAndMinTemperaturePerLocation(@RequestParam String startDate, @RequestParam String endDate){
        return null;
    }

    @DeleteMapping("/eliminar")
    public void deleteAllRecords(){
        log.info("Deleting all the records");
        weatherDataService.deleteAll();
    }
}
